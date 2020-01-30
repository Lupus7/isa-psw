package team47pack.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Page<NextProcedure> page = nextProcedureRepo.findByClinicAndArranged(clinicId, false, sort);

		return page.getContent();
	}

	public boolean arrangeExamination(JSONObject obj, String email) throws JSONException, ParseException {
		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return false;
		Long clinicId = Long.parseLong("" + ca.getClinic());
		
		Optional<Clinic> clinic = clinicRepo.findById(clinicId);
		if(!clinic.isPresent())
			return false;

		if (obj.get("idNextProcedure") == null || obj.get("idRoom") == null || obj.get("date") == null
				|| obj.get("time") == null || obj.get("idDoctorNew") == null)
			return false;

		if (obj.get("idNextProcedure").equals("") || obj.get("idRoom").equals("") || obj.get("date").equals("")
				|| obj.get("time").equals(""))
			return false;

		Long idNextProcedure = obj.getLong("idNextProcedure");
		Long idRoom = obj.getLong("idRoom");
		String time = obj.getString("time");
		Date dateConv = new SimpleDateFormat("dd/MM/yyyy").parse(obj.getString("date"));

		Optional<NextProcedure> nextP = nextProcedureRepo.findById(idNextProcedure);
		if (!nextP.isPresent())
			return false;
		Optional<Room> room = roomRepo.findById(idRoom);
		if (!room.isPresent())
			return false;

		Date dateT = new Date();
		if (dateConv.compareTo(dateT) <= 0)
			return false;

		Doctor doctor = nextP.get().getDoctor();
		if (!obj.get("idDoctorNew").equals("none")) {
			Long idDoc = obj.getLong("idDoctorNew");
			Optional<Doctor> d = doctorRepo.findById(idDoc);
			if (d.isPresent())
				doctor = d.get();
		}

		nextP.get().setArranged(true);
		nextP.get().setDate(dateConv);
		nextP.get().setDoctor(doctor);
		RoomArrange ra = new RoomArrange(room.get().getId(), dateConv, time, true, nextP.get().getId());
		room.get().getTakenDates().add(ra);

		roomArrangeRepo.save(ra);
		nextProcedureRepo.save(nextP.get());
		roomRepo.save(room.get());
		
		String[] s = dateConv.toString().split(" ");
		String dateEmail = s[2]+"/"+s[1]+"/"+s[5];

		String type = nextP.get().getExaminationtype().getName();
		String bodyPatient = "Dear Sir/Madam \n \nYour examination request has been arranged for date: "+dateEmail+
				", time: "+time + " and room: "+room.get().getName()+
				"!\nDoctor who is going to examin you is "+doctor.getFirstName()+" "+doctor.getLastName() +
				"!\nAll the best\n\n" + clinic.get().getName().toUpperCase();
		
		String bodyDoctor = "Dear Sir/Madam \n \nYou got a new examination.\nExamination has been arranged for date: "+
				dateEmail+", time: "+time + " and in room: "+room.get().getName()+ 
				"!\nAll the best\n\n" + "Admin Team";

		// izmena mail da radi
		emailService.sendSimpleMessage("mail@gmail.com", type, bodyPatient);
		emailService.sendSimpleMessage("mail@gmail.com", type, bodyDoctor);

		return true;
	}

}
