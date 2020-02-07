package team47pack.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.ExaminInfo;
import team47pack.models.dto.PrescriptionDTO;
import team47pack.repository.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationService {
	@Autowired
	private ExaminationRepo examinationRepo;

	@Autowired
	private MedFileRepo medFileRepo;
	@Autowired
	private MedEntryRepo medEntryRepo;
	@Autowired
	private DiagnosisRepo diagRepo;
	@Autowired
	private PrescriptionRepo presRepo;

	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private NextProcedureRepo nextProcedureRepo;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ClinicRepo clinicRepo;

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private ExaminationTypeService examinationTypeService;

	@Autowired
	private ClinicAdminRepo caRepo;

	@Autowired
	private ExaminationTypeRepo exmTypeRepo;

	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;

	public List<Examination> getByPatientId(Long id) {
		System.out.println("Ovo je iz servica : " + id);
		return examinationRepo.findByPatientId(id);
	}

	public Examination findOne(Long id){
		return examinationRepo.getOne(id);
	}

	public List<Examination> getByDoctorId(Long id) {
		return examinationRepo.findAllByDoctorId(id);
	}

	public void save(Examination e) {
		examinationRepo.save(e);
	}

	public List<Examination> getAll() {
		List<Examination> ret = examinationRepo.findAll();
		for (Examination e : ret) {
			System.out.println(e.toString());
		}
		return ret;
	}

	public boolean addExamination(ExaminInfo examinInfo, String doctor) throws ParseException {
		// @author: Lupus7 (Sinisa Canak)
		Optional<Patient> pat = Optional
				.ofNullable(patientService.getPatientbyID(examinInfo.getPatientId().toString()));
		Optional<Doctor> doc = Optional.ofNullable(doctorService.getDoctor(doctor));

		if (!pat.isPresent())
			return false;

		if (!doc.isPresent())
			return false;

		MedFileEntry medFileEntry = new MedFileEntry();
		medFileEntry.setDesc(examinInfo.getDesc());
		medFileEntry.setDate(new Date());

		Optional<Diagnosis> diag = diagRepo.findById(examinInfo.getDiag().getId());
		if (!diag.isPresent())
			return false;

		medFileEntry.setDiagnosis(diag.get());

		for (PrescriptionDTO p : examinInfo.getPrescs()) {
			Optional<Prescription> pres = presRepo.findById(p.getId());
			if (!pres.isPresent())
				return false;
			medFileEntry.getPrescriptions().add(new PrescriptionVerification(pat.get(), doc.get(), pres.get()));
		}
		
		medFileEntry.setDoctor(doc.get());
		//medEntryRepo.save(medFileEntry);

		if (pat.get().getMedicalFile() == null) {
			MedicalFile mf = new MedicalFile(pat.get().getId(), new ArrayList<>());
			mf.getEntries().add(medFileEntry);

			//medFileRepo.save(mf);

			pat.get().setMedicalFile(mf);
		}else {
			pat.get().getMedicalFile().getEntries().add(medFileEntry);
			//medFileRepo.save(pat.get().getMedicalFile());
		}

		patientService.insert(pat.get());

		// @------author: Jokara nextProcedure

		if (examinInfo.getDate() != null && examinInfo.getProcedure() != null && examinInfo.getIdType() != null) {
			if (examinInfo.getDate().toString().equals("") && !examinInfo.getProcedure().equals("")) {
				if (examinInfo.getProcedure().equals("Examination") && examinInfo.getIdType().equals(""))
					return false;
				else if (examinInfo.getProcedure().equals("Operation") && !examinInfo.getIdType().equals(""))
					return false;
			} else if (!examinInfo.getDate().toString().equals("") && examinInfo.getProcedure().equals(""))
				return false;
			else if (!examinInfo.getDate().toString().equals("") && !examinInfo.getProcedure().equals("")
					&& pat.isPresent() && examinInfo.getPickedtime() >= 6 && examinInfo.getPickedtime() <=21) {
				addNextProcedure(examinInfo.getDate(), examinInfo.getProcedure(), examinInfo.getIdType(), pat, doctor,examinInfo.getPickedtime());
			}
		}

		return true;
	}

	// @------author: Jokara
	public void addNextProcedure(Date date, String procedure, String type, Optional<Patient> pat, String d,int pickedtime)
			throws ParseException {

		Doctor doctor = doctorRepo.findByEmail(d);
		Date dateT = new Date();

		if (dateT.compareTo(date) <= 0) {
			if (type.equals("")) {
				NextProcedure np = new NextProcedure(procedure, date, pat.get(), doctor,pickedtime);
				nextProcedureRepo.save(np);
			} else {
				Optional<ExaminationType> ext = exmTypeRepo.findById(Long.parseLong(type));
				if (ext.isPresent()) {
					NextProcedure np = new NextProcedure(procedure, date, pat.get(), doctor, ext.get(),pickedtime);
					nextProcedureRepo.save(np);
				}

			}

			System.out.println("Snimio!");

			Optional<Clinic> clinic = clinicRepo.findById(doctor.getClinicId());
			if (clinic.isPresent()) {

				List<ClinicAdmin> admins = (List<ClinicAdmin>) clinic.get().getClinicAdmins();

				DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
				String dateE = formatter.format(date);
				String body = "Dear Sir/Madam \n \nYou have new " + procedure + " request made by doctor:  "
						+ doctor.getFirstName() + " " + doctor.getLastName() + "!\n" + "Select a room for this "
						+ procedure + " until " + dateE
						+ " or system will automatically assign room for this request! \n \n All the best!";

				for (ClinicAdmin ca : admins)
					emailService.sendSimpleMessage("mail@gmail.com", "New " + procedure + "!", body);

			}

		}

	}

	public List<Examination> findAllByRoom(Long id) {
		return examinationRepo.findAllByRoomId(id);
	}

	public Boolean sendRequest(JSONObject obj, String name) throws JSONException, ParseException {
		System.out.println(obj.getString("date") + "  " +obj.getString("doctor"));
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = inputFormat.parse(obj.getString("date"));

		System.out.println("DATUUUM : " + date);
		Patient p = patientService.getPatient(name);
		if(p == null){
			return false;
		}
		Doctor doc = doctorRepo.findByEmail(obj.getString("doctor"));
        if(doc == null){
        	return false;
		}
		Clinic c = clinicService.getClinicByDoktorID(doc.getId()) ;
		if(c == null){
			return false;
		}
        List<ExaminationType> ex = examinationTypeService.findByClinicID(c.getId());
        ExaminationType temp = new ExaminationType();
		for(ExaminationType e: ex){
		    if(e.getSpecialization().equals(doc.getSpecialization()) ){
				temp = e;
				System.out.println("This is examination type: " + e.getName() + e.getSpecialization());
				break;
			}
		}
		Examination e = new Examination(doc.getSpecialization(),date,p,doc,false,temp);
		examinationRepo.save(e);
		return  true;
	}
}
