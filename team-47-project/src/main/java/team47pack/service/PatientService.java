package team47pack.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import team47pack.models.Clinic;
import team47pack.models.Patient;
import team47pack.models.dto.FilterPatientRequest;
import team47pack.models.dto.SearchPatientRequest;
import team47pack.repository.ClinicRepo;
import team47pack.repository.PatientRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
	@Autowired
	private PatientRepo patientRepository;
	@Autowired
	private ClinicRepo clinicRepository;

	public List<Patient> getAllPatients() {
		Pageable page = PageRequest.of(0, 15);
		return patientRepository.findAll(page).getContent();
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

			Optional<Patient> p = patientRepository.findById(id);

			if (p.isPresent())
				patients.add(p.get());
			return patients;

		} else if(!req.getFirstName().equals("") && !req.getLastName().equals("") && req.getId().equals(""))
			patients = patientRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
					req.getFirstName(), req.getLastName());
		else if(!req.getFirstName().equals("") && req.getLastName().equals("") && req.getId().equals(""))
			patients = patientRepository.findByFirstNameContainingIgnoreCase(req.getFirstName());		
		else if(req.getFirstName().equals("") && !req.getLastName().equals("") && req.getId().equals(""))
			patients = patientRepository.findByLastNameContainingIgnoreCase(req.getLastName());

		return patients;

	}

	// @author:Jokara---------------------------------------------------------------
	public ArrayList<Patient> filterPatients(@RequestBody FilterPatientRequest req) {
		ArrayList<Patient> patients = new ArrayList<>();
		if (req.getValueFilter() == null || req.getValueFilter() == null)
			return patients;
		if (req.getValueFilter().equals("") && req.getValueFilter().equals(""))
			return patients;

		String filterBy = req.getFilterBy();
		String value = req.getValueFilter();

		if (filterBy.equals("Patient ID")) {
			Long id = Long.parseLong(value);
			Optional<Patient> p = patientRepository.findById(id);
			if (p.isPresent())
				patients.add(p.get());
			return patients;
			
		} else if (filterBy.equals("First Name"))
			return patientRepository.findByFirstNameContainingIgnoreCase(value);

		else if (filterBy.equals("Last Name"))
			return patientRepository.findByLastNameContainingIgnoreCase(value);
		else if (filterBy.equals("Address"))
			return patientRepository.findByAddressContainingIgnoreCase(value);
		else if (filterBy.equals("City"))
			return patientRepository.findByCityContainingIgnoreCase(value);
		else if (filterBy.equals("State"))
			return patientRepository.findByStateContainingIgnoreCase(value);
		else if (filterBy.equals("Telephone"))
			return patientRepository.findByTelephoneContainingIgnoreCase(value);
		else if (filterBy.equals("Unique Number"))
			return patientRepository.findByUniqueNumContainingIgnoreCase(value);

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
		Optional<Patient> p = patientRepository.findById(id);
		if (!p.isPresent())
			return null;

		return p.get();
	}

	public List<Patient> getSortedPatients(String type) {
		if(type.equals(""))
			return new ArrayList<>();
		
		int tip  = Integer.parseInt(type);
		
		if(tip == 1) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("id"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}else if(tip == 2) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("firstName"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}else if(tip == 3) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("lastName"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}else if(tip == 4) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("address"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}else if(tip == 5) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("city"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}else if(tip == 6) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("state"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}else if(tip == 7) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("telephone"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}else if(tip == 8) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("uniqueNum"));
			Page<Patient> page = patientRepository.findAll(sort);
			return page.getContent();
		}
		
		return new ArrayList<>();
	}

}
