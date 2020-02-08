package team47pack.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.NextProcedure;
import team47pack.models.Room;
import team47pack.models.RoomArrange;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.NextProcedureRepo;
import team47pack.repository.RoomArrangeRepo;
import team47pack.repository.RoomRepo;

@Service
public class NextExaminationService {

	@Autowired
	private ClinicAdminRepo caRepo;

	@Autowired
	private ClinicRepo clinicRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private NextProcedureRepo nextProcedureRepo;

	@Autowired
	private RoomRepo roomRepo;

	@Autowired
	private RoomArrangeRepo roomArrangeRepo;

	// @------author: Jokara
	public java.util.List<NextProcedure> getNextExns(String email) {
		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<NextProcedure>();
		Long clinicId = Long.parseLong("" + ca.getClinic());
		Pageable sort = PageRequest.of(0, 15, Sort.by("date"));
		Page<NextProcedure> page = nextProcedureRepo.findByClinicAndArrangedAndPatientNotNull(clinicId, false, sort);

		return page.getContent();
	}

	//@Transactional
	public boolean arrangeExamination(String json, String email) throws JSONException, ParseException {
		JSONObject obj = new JSONObject(json);

		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return false;
		Long clinicId = Long.parseLong("" + ca.getClinic());

		Optional<Clinic> clinic = clinicRepo.findById(clinicId);
		if (!clinic.isPresent())
			return false;

		if (obj.get("idNextProcedure") == null || obj.get("idRoom") == null || obj.get("date") == null
				|| obj.get("time") == null || obj.get("idDoctorNew") == null)
			return false;

		if (obj.get("idNextProcedure").equals("") || obj.get("idRoom").equals("") || obj.get("date").equals(""))
			return false;

		Long idNextProcedure = obj.getLong("idNextProcedure");
		Long idRoom = obj.getLong("idRoom");

		Date dateConv = new SimpleDateFormat("yyyy-MM-dd").parse(obj.getString("date"));

		Optional<NextProcedure> nextP = nextProcedureRepo.findById(idNextProcedure);
		if (!nextP.isPresent() && nextP.get().isArranged())
			return false;
		Optional<Room> room = roomRepo.findById(idRoom);
		if (!room.isPresent() && room.get().getType().equals("Operation"))
			return false;

		Date dateT = new Date();
		if (dateConv.compareTo(dateT) <= 0)
			return false;

		int time = 0;
		if (!obj.get("time").equals("")) {
			time = obj.getInt("time");
		} else
			time = nextP.get().getPickedtime();

		Doctor doctor = nextP.get().getDoctor();
		if (!obj.get("idDoctorNew").equals("none")) {
			Long idDoc = obj.getLong("idDoctorNew");
			Optional<Doctor> d = doctorRepo.findById(idDoc);
			if (d.isPresent())
				doctor = d.get();
		}

		if (doctor.getOnVacation())
			return false;

		///////////////////////////////////////////// provera za shift
		Integer[] intervals = { 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 };
		HashMap<Integer, Integer> shift1 = new HashMap<>();
		HashMap<Integer, Integer> shift2 = new HashMap<>();

		for (int i = 0; i < intervals.length; i++) {
			if (i <= 7)
				shift1.put(intervals[i], i);
			else
				shift2.put(intervals[i], i);

		}

		if (doctor.getShift() == 1 && !shift1.containsKey(time))
			return false;
		else if (doctor.getShift() == 2 && !shift2.containsKey(time))
			return false;

		///////////////////////////////////////
		NextProcedure check = nextProcedureRepo.findByDateAndPickedtimeAndArranged(dateConv, time, true);
		if (check != null)
			return false;
		//////////////////////////////////////

		nextP.get().setArranged(true);
		nextP.get().setDate(dateConv);
		nextP.get().setDoctor(doctor);
		nextP.get().setPickedtime(time);
		RoomArrange ra = new RoomArrange(room.get().getId(), dateConv, time, true, nextP.get().getId(), clinicId);
		room.get().getTakenDates().add(ra);

		roomArrangeRepo.save(ra);
		nextProcedureRepo.save(nextP.get());
		roomRepo.save(room.get());

		String[] s = dateConv.toString().split(" ");
		String dateEmail = s[2] + "/" + s[1] + "/" + s[5];

		String type = nextP.get().getExaminationtype().getName();
		String bodyPatient = "Dear Sir/Madam \n \nYour examination request has been arranged for date: " + dateEmail
				+ ", time: " + time + ":00h and room: " + room.get().getName()
				+ "!\nDoctor who is going to examin you is " + doctor.getFirstName() + " " + doctor.getLastName()
				+ "!\nAll the best\n\n" + clinic.get().getName().toUpperCase();

		String bodyDoctor = "Dear Sir/Madam \n \nYou got a new examination.\nExamination has been arranged for date: "
				+ dateEmail + ", time: " + time + ":00h and in room: " + room.get().getName() + "!\nAll the best\n\n"
				+ "Admin Team";

		// izmena mail da radi
		emailService.sendSimpleMessage("mail@gmail.com", type, bodyPatient);
		emailService.sendSimpleMessage("mail@gmail.com", type, bodyDoctor);

		return true;
	}

	// metoda koja automatski dodeljuje sobe na kraju dana
	@Scheduled(cron = "59 59 11 * * ?")
	 //@Scheduled(cron = "0/5 * * * * ?")
	public void arrangeExaminationRoomAutomatic() throws ParseException {

		List<NextProcedure> nextProcedures = nextProcedureRepo.findByArrangedAndTypeAndPatientNotNull(false,
				"Examination");
		if (nextProcedures.isEmpty())
			return;
		List<Room> rooms = roomRepo.findByType("Examination");
		Integer[] intervals = { 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 };
		HashMap<Integer, Integer> shift1 = new HashMap<>();
		HashMap<Integer, Integer> shift2 = new HashMap<>();
		for (int i = 0; i < intervals.length; i++) {
			if (i <= 7)
				shift1.put(intervals[i], i);
			else
				shift2.put(intervals[i], i);

		}

		for (Room r : rooms) {

			boolean end = false;
			for (NextProcedure npe : nextProcedures) {
				if (npe.isArranged()) {
					end = true;
				} else {
					end = false;
					break;
				}
			}
			if (end) {
				System.out.println("All Examinations arranged!");
				return;
			}

			List<RoomArrange> ra = r.getTakenDates();

			if (ra == null || ra.isEmpty()) {

				if (r.getClinicId() == nextProcedures.get(0).getIdClinic() && !nextProcedures.get(0).isArranged()) {

					RoomArrange raNew = new RoomArrange(r.getId(), nextProcedures.get(0).getDate(),
							nextProcedures.get(0).getPickedtime(), true, nextProcedures.get(0).getId(),
							nextProcedures.get(0).getIdClinic());
					nextProcedures.get(0).setArranged(true);
					r.getTakenDates().add(raNew);

					roomArrangeRepo.save(raNew);
					nextProcedureRepo.save(nextProcedures.get(0));
					roomRepo.save(r);

					// ------------------------------------------------mail
					String[] s = nextProcedures.get(0).getDate().toString().split(" ");
					String[] s1 = s[0].split("-");
					String dateEmail = s1[2] + "/" + s1[1] + "/" + s1[0];

					String type = nextProcedures.get(0).getExaminationtype().getName();
					String bodyPatient = "Dear Sir/Madam \n \nYour examination request has been arranged for date: "
							+ dateEmail + ", time: " + nextProcedures.get(0).getPickedtime() + ":00h and room: "
							+ r.getName() + "!\nDoctor who is going to examin you is "
							+ nextProcedures.get(0).getDoctor().getFirstName() + " "
							+ nextProcedures.get(0).getDoctor().getLastName() + "!\nAll the best\n\n";

					String bodyDoctor = "Dear Sir/Madam \n \nYou got a new examination.\nExamination has been arranged for date: "
							+ dateEmail + ", time: " + nextProcedures.get(0).getPickedtime() + ":00h and in room: "
							+ r.getName() + "!\nAll the best\n\n" + "Admin Team";

					// izmena mail da radi
					emailService.sendSimpleMessage("mail@gmail.com", type, bodyPatient);
					emailService.sendSimpleMessage("mail@gmail.com", type, bodyDoctor);
					// ------------------------------------------------------------

				}

			} else {

				HashMap<Integer, Integer> map = new HashMap<>();
				int t = 0;

				boolean fill = false;
				for (NextProcedure np : nextProcedures) {

					if (!fill) {
						map = new HashMap<>();
						for (int i = 0; i < ra.size(); i++) {
							if (np.getDate().equals(ra.get(0).getDate()))
								map.put(ra.get(i).getTime(), i);
						}
						fill = true;
					}

					while (!np.isArranged()) {

						if (r.getClinicId() == np.getIdClinic() && !np.isArranged()) {
							if (!map.containsKey(np.getPickedtime())) {

								map.put(np.getPickedtime(), map.size() + 1);
								RoomArrange raNew = new RoomArrange(r.getId(), np.getDate(), np.getPickedtime(), true,
										np.getId(), np.getIdClinic());
								np.setArranged(true);
								r.getTakenDates().add(raNew);

								roomArrangeRepo.save(raNew);
								nextProcedureRepo.save(np);
								roomRepo.save(r);
								fill = false;
								t = 0;

								// ------------------------------------------------mail
								String[] s = np.getDate().toString().split(" ");
								String[] s1 = s[0].split("-");
								String dateEmail = s1[2] + "/" + s1[1] + "/" + s1[0];
								String type = nextProcedures.get(0).getExaminationtype().getName();
								String bodyPatient = "Dear Sir/Madam \n \nYour examination request has been arranged for date: "
										+ dateEmail + ", time: " + np.getPickedtime() + ":00h and room: " + r.getName()
										+ "!\nDoctor who is going to examin you is " + np.getDoctor().getFirstName()
										+ " " + np.getDoctor().getLastName() + "!\nAll the best\n\n";

								String bodyDoctor = "Dear Sir/Madam \n \nYou got a new examination.\nExamination has been arranged for date: "
										+ dateEmail + ", time: " + np.getPickedtime() + ":00h and in room: "
										+ r.getName() + "!\nAll the best\n\n" + "Admin Team";

								// izmena mail da radi
								emailService.sendSimpleMessage("mail@gmail.com", type, bodyPatient);
								emailService.sendSimpleMessage("mail@gmail.com", type, bodyDoctor);
								// ------------------------------------------------------------

							} else if (!map.containsKey(intervals[t])
									&& ((np.getDoctor().getShift() == 1 && shift1.containsKey(intervals[t]))
											|| (np.getDoctor().getShift() == 2 && shift2.containsKey(intervals[t])))) {

								map.put(intervals[t], map.size() + 1);
								RoomArrange raNew = new RoomArrange(r.getId(), np.getDate(), intervals[t], true,
										np.getId(), np.getIdClinic());
								np.setArranged(true);
								np.setPickedtime(intervals[t]);
								r.getTakenDates().add(raNew);

								roomArrangeRepo.save(raNew);
								nextProcedureRepo.save(np);
								roomRepo.save(r);
								fill = false;
								t = 0;

								// ------------------------------------------------mail
								String[] s = np.getDate().toString().split(" ");
								String[] s1 = s[0].split("-");
								String dateEmail = s1[2] + "/" + s1[1] + "/" + s1[0];

								String type = nextProcedures.get(0).getExaminationtype().getName();
								String bodyPatient = "Dear Sir/Madam \n \nYour examination request has been arranged for date: "
										+ dateEmail + ", time: " + np.getPickedtime() + ":00h and room: " + r.getName()
										+ "!\nDoctor who is going to examin you is " + np.getDoctor().getFirstName()
										+ " " + np.getDoctor().getLastName() + "!\nAll the best\n\n";

								String bodyDoctor = "Dear Sir/Madam \n \nYou got a new examination.\nExamination has been arranged for date: "
										+ dateEmail + ", time: " + np.getPickedtime() + ":00h and in room: "
										+ r.getName() + "!\nAll the best\n\n" + "Admin Team";

								// izmena mail da radi
								emailService.sendSimpleMessage("mail@gmail.com", type, bodyPatient);
								emailService.sendSimpleMessage("mail@gmail.com", type, bodyDoctor);
								// ------------------------------------------------------------

							} else {
								if (t < intervals.length - 1)
									++t;
							}

						}
					}
				}

			}
		}

	}

}
