package team47pack.service;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.FastExamDto;
import team47pack.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FastExaminationService {

	@Autowired
	private ClinicAdminRepo caRepo;
	
	@Autowired
	private ExaminationTypeRepo examTypeRepo;
	
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private ClinicRepo clinicRepo;

	@Autowired
	private PatientRepo patientRepo;


	@Autowired
	private NextProcedureRepo nextProcedureRepo;

	public boolean addExamination(JSONObject obj, String email) throws JSONException, ParseException {
		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return false;

		if (obj.get("date") == null || obj.get("time") == null || obj.get("examTypeId") == null
				|| obj.get("roomId") == null || obj.get("doctorId") == null)
			return false;
		
		if (obj.get("date").equals("") || obj.get("time").equals("") || obj.get("examTypeId").equals("")
				|| obj.get("roomId").equals("") || obj.get("doctorId").equals(""))
			return false;
		
		Long examTypeId = obj.getLong("examTypeId");
		Long roomId = obj.getLong("roomId");
		Long doctorId = obj.getLong("doctorId");
		
		Optional<ExaminationType> examType = examTypeRepo.findById(examTypeId);
		if(!examType.isPresent())
			return false;
		
		Optional<Room> room = roomRepo.findById(roomId);
		if(!room.isPresent())
			return false;
		
		Optional<Doctor> doctor = doctorRepo.findById(doctorId);
		if(!doctor.isPresent() || doctor.get().getOnVacation())
			return false;
		
		int time = obj.getInt("time");

		
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

		if (doctor.get().getShift() == 1 && !shift1.containsKey(time))
			return false;
		else if (doctor.get().getShift() == 2 && !shift2.containsKey(time))
			return false;

		///////////////////////////////////////
		
		
		//sredi date
		String[] s = obj.get("date").toString().split("-");
		String ds = s[2]+"/"+s[1]+"/"+s[0];
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ds);
		// da li je vreme vec zauzeo za taj datum
		NextProcedure check = nextProcedureRepo.findByDateAndPickedtimeAndArranged(date,time,true);
		if(check != null)
			return false;
	
		
		NextProcedure np = new NextProcedure("Examination", date, null, doctor.get(), examType.get(), time);		
		nextProcedureRepo.save(np);
		
		return true;
	}

	public List<FastExamDto> convert(List<NextProcedure> procs){
		List<FastExamDto> ret = new ArrayList<>();
		for(NextProcedure n : procs){
			Long id = n.getId();
			Clinic c = clinicRepo.getOne(n.getIdClinic());
			String klinika = c.getName();
			String doctor = n.getDoctor().getFirstName() + " " + n.getDoctor().getLastName();
			String spec = n.getDoctor().getSpecialization();
			String type = n.getType();
			String ExamType = n.getExaminationtype().getName();
			Date date = n.getDate();
			FastExamDto fed = new FastExamDto(id,klinika,doctor,spec,type,ExamType,date);
			ret.add(fed);
		}
		return ret;
	}

	public List<FastExamDto> getFastExaminations(String email) {
		Pageable pageable = PageRequest.of(0, 30);
		Page<NextProcedure> page = nextProcedureRepo.findByPatientAndArranged(null,false,pageable);		
		List<FastExamDto>ret =convert(page.getContent());
		return ret;
	}

	public List<Doctor> getDoctors(String email) {
		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		Long clinicId = Long.parseLong(""+ca.getClinic());
		List<Doctor> doctors = doctorRepo.findByClinicIdAndOnVacation(clinicId,false);
		
		return doctors;
	}

	public List<Room> getRooms(String email) {
		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		List<Room> rooms = roomRepo.findByTypeAndClinicId("Examination",ca.getClinic());
		
		return rooms;
	}

	public List<ExaminationType> getExaminationTypes(String specialization, String email) {
		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		List<ExaminationType> examTypes = examTypeRepo.findByClinicAndSpecialization(Long.parseLong(""+ca.getClinic()), specialization);
	
		return examTypes;
	}

	public void appoint(Long id, String name) {
		Optional<NextProcedure> next = nextProcedureRepo.findById(id);
		if(!next.isPresent()){
			return;
		}
		Patient patient = patientRepo.findByEmail(name);
		next.get().setPatient(patient);
		nextProcedureRepo.save(next.get());
	}

	public List<FastExamDto> getPatientsExaminations(String name) {
		Pageable pageable = PageRequest.of(0, 30);
		Patient patient = patientRepo.findByEmail(name);
		Page<NextProcedure> page = nextProcedureRepo.findByPatientAndArranged(patient,false,pageable);
		List<FastExamDto> ret =convert(page.getContent());
		return ret;
	}

	public Boolean disarange(Long id, String name) {
		Optional<NextProcedure> next = nextProcedureRepo.findById(id);
		if(!next.isPresent()){
			return false;
		}
//		Instant now = Instant.now();
//		Boolean isWithinPrior24Hours =
//				( ! next.get().getDate().toInstant().isBefore( now.minus( 24 , ChronoUnit.HOURS) ) )
//						&&
//						( next.get().getDate().toInstant().isBefore( now )
//						) ;
		boolean isBeforeYesterday = new DateTime(next.get().getDate()).isBefore( DateTime.now().minusDays(1) );
		if(isBeforeYesterday)
		{
			System.out.println("CAN NOT DISARAGNE!!!!");
			return false;
		}

		Patient patient = patientRepo.findByEmail(name);
		next.get().setPatient(null);
		nextProcedureRepo.save(next.get());
		return true;
	}
}
