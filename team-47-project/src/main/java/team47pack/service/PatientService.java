package team47pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import team47pack.models.Clinic;
import team47pack.models.Patient;
import team47pack.models.dto.SearchPatientRequest;
import team47pack.repository.ClinicRepo;
import team47pack.repository.PatientRepo;

@Service
public class PatientService {
	@Autowired
	private PatientRepo patientRepository;
	@Autowired
	private ClinicRepo clinicRepository;

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public List<Clinic> getAllClinics() {
		return clinicRepository.findAll();
	}

	public void insert(Patient patient) {
		patientRepository.save(patient);
	}

	public List<Patient> getRegRequest() {
		List<Patient> retVal = new ArrayList<Patient>();
		for (Patient p : patientRepository.findAll()) {
			if (!p.isAccepted()) {
				retVal.add(p);
			}
		}

		return retVal;
	}

	public Patient getPatient(String email) {
		System.out.println(email);
		Patient ret = new Patient();
		for (Patient p : patientRepository.findAll()) {
			if (p.getEmail().equals(email)) {
				ret = p;
				break;
			}
		}
		System.out.println(ret.getUsername() + ret.getEmail());
		return ret;
	}

	// @author:Jokara---------------------------------------------------------------
	public ArrayList<Patient> searchPatients(@RequestBody SearchPatientRequest req) {
		ArrayList<Patient> patients = new ArrayList<>();
		if (req.getFirstName() == null || req.getLastName() == null || req.getId() == null)
			return patients;
		if (req.getFirstName().equals("") && req.getLastName().equals("") && req.getId().equals(""))
			return patients;

		if (!req.getId().equals("")) {
			Long id = Long.parseLong(req.getId());
			if (id < 1 || id == null)
				return patients;

			Patient p = (Patient) patientRepository.getOne(id);

			if (p != null)
				patients.add(p);
			return patients;

		} else
			patients = patientRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
					req.getFirstName(), req.getLastName());

		return patients;

	}

	// @author:Jokara---------------------------------------------------------------
	public Patient getPatientbyID(String idP) {
		if (idP == null)
			return null;
		if (idP.equals(""))
			return null;

		Long id = Long.parseLong(idP);
		if (id < 1 || id == null)
			return null;
		Patient p = patientRepository.getOne(id);
		if (p == null)
			return null;

		return p;
	}

}
