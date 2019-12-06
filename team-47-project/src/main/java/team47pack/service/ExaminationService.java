package team47pack.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Diagnosis;
import team47pack.models.Doctor;
import team47pack.models.Examination;
import team47pack.models.MedFileEntry;
import team47pack.models.MedicalFile;
import team47pack.models.NextProcedure;
import team47pack.models.Patient;
import team47pack.models.Prescription;
import team47pack.models.dto.ExaminInfo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.DiagnosisRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.ExaminationRepo;
import team47pack.repository.MedEntryRepo;
import team47pack.repository.MedFileRepo;
import team47pack.repository.NextProcedureRepo;
import team47pack.repository.PatientRepo;
import team47pack.repository.PrescriptionRepo;

@Service
public class ExaminationService {
	@Autowired
	private ExaminationRepo examinationRepo;
	@Autowired
	private PatientRepo patientRepo;

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

	public List<Examination> getByPatientId(Long id) {
		System.out.println("Ovo je iz servica : " + id);
		return examinationRepo.findByPatientId(id);
	}

	public List<Examination> getAll() {
		List<Examination> ret = examinationRepo.findAll();
		for (Examination e : ret) {
			System.out.println(e.toString());
		}
		return ret;
	}

	public boolean addExamination(ExaminInfo examinInfo, String doctor) throws ParseException {
		Optional<Patient> pat = patientRepo.findById(examinInfo.getPatientId());

		if (!pat.isPresent())
			return false;

		MedFileEntry medFileEntry = new MedFileEntry();
		medFileEntry.setDesc(examinInfo.getDesc());

		Optional<Diagnosis> diag = diagRepo.findById(examinInfo.getDiag().getId());
		if (!diag.isPresent())
			return false;

		medFileEntry.setDiagnosis(diag.get());

		for (Prescription p : examinInfo.getPrescs()) {
			Optional<Prescription> pres = presRepo.findById(p.getId());
			if (!pres.isPresent())
				return false;
			medFileEntry.getPrescriptions().add(pres.get());
		}

		medEntryRepo.save(medFileEntry);

		if (pat.get().getMedicalFile() == null) {
			MedicalFile mf = new MedicalFile(pat.get().getId(), new ArrayList<>());
			mf.getEntries().add(medFileEntry);

			medFileRepo.save(mf);

			pat.get().setMedicalFile(mf);
		}

		patientRepo.save(pat.get());

		// @------author: Jokara nextProcedure
		if (examinInfo.getDate() != null && examinInfo.getProcedure() != null) {
			if (!examinInfo.getDate().equals("") && !examinInfo.getProcedure().equals("") && pat.isPresent()) {
				addNextProcedure(examinInfo.getDate(), examinInfo.getProcedure(), pat, doctor);
			}
		}

		return true;
	}

	// @------author: Jokara
	public void addNextProcedure(Date date, String procedure, Optional<Patient> pat, String d) throws ParseException {

		Doctor doctor = doctorRepo.findByEmail(d);
		Date dateT = new Date();

		if (dateT.compareTo(date) <= 0) {
			NextProcedure np = new NextProcedure(procedure, date, pat.get(), doctor);
			nextProcedureRepo.save(np);
			Clinic clinic = clinicRepo.getOne(doctor.getClinicId());
			List<ClinicAdmin> admins = (List<ClinicAdmin>) clinic.getClinicAdmins();

			DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			String dateE = formatter.format(date);
			String body = "Dear Sir/Madam \n \nYou have new " + procedure + " request made by doctor:  "
					+ doctor.getFirstName() + " " + doctor.getLastName() + "!\n" + "Select a room for this " + procedure
					+ " until " + dateE
					+ " or system will automatically assign room for this request! \n \n All the best!";

			for (ClinicAdmin ca : admins) {
				emailService.sendSimpleMessage(ca.getEmail(), "New " + procedure + "!", body);
			}

		}

	}

}
