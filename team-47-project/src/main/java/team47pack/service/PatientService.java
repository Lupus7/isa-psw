package team47pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import team47pack.models.Clinic;
import team47pack.models.Doctor;
import team47pack.models.MedFileEntry;
import team47pack.models.MedicalFile;
import team47pack.models.MedicalStaff;
import team47pack.models.Nurse;
import team47pack.models.Patient;
import team47pack.models.PrescriptionVerification;
import team47pack.models.dto.FilterPatientRequest;
import team47pack.models.dto.MedicalFileViewDTO;
import team47pack.models.dto.SearchPatientRequest;
import team47pack.repository.ClinicRepo;
import team47pack.repository.MedEntryRepo;
import team47pack.repository.MedFileRepo;
import team47pack.repository.MedicalStaffRepo;
import team47pack.repository.PatientRepo;

@Service
public class PatientService {
	@Autowired
	private PatientRepo patientRepository;
	@Autowired
	private ClinicRepo clinicRepository;

	@Autowired
	private MedFileRepo mfRepo;

	@Autowired
	private MedicalStaffRepo msRepo;

	@Autowired
	private MedEntryRepo meRepo;

	public List<Patient> getAllPatients() {
		Pageable page = PageRequest.of(0, 15);
		return patientRepository.findAllByEnabled(true, page).getContent();
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

			Optional<Patient> p = patientRepository.findByIdAndEnabled(id, true);

			if (p.isPresent())
				patients.add(p.get());
			return patients;

		} else if (!req.getFirstName().equals("") && !req.getLastName().equals("") && req.getId().equals(""))
			patients = patientRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndEnabled(
					req.getFirstName(), req.getLastName(), true);
		else if (!req.getFirstName().equals("") && req.getLastName().equals("") && req.getId().equals(""))
			patients = patientRepository.findByFirstNameContainingIgnoreCaseAndEnabled(req.getFirstName(), true);
		else if (req.getFirstName().equals("") && !req.getLastName().equals("") && req.getId().equals(""))
			patients = patientRepository.findByLastNameContainingIgnoreCaseAndEnabled(req.getLastName(), true);

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
			Optional<Patient> p = patientRepository.findByIdAndEnabled(id, true);
			if (p.isPresent())
				patients.add(p.get());
			return patients;

		} else if (filterBy.equals("First Name"))
			return patientRepository.findByFirstNameContainingIgnoreCaseAndEnabled(value, true);

		else if (filterBy.equals("Last Name"))
			return patientRepository.findByLastNameContainingIgnoreCaseAndEnabled(value, true);
		else if (filterBy.equals("Address"))
			return patientRepository.findByAddressContainingIgnoreCaseAndEnabled(value, true);
		else if (filterBy.equals("City"))
			return patientRepository.findByCityContainingIgnoreCaseAndEnabled(value, true);
		else if (filterBy.equals("State"))
			return patientRepository.findByStateContainingIgnoreCaseAndEnabled(value, true);
		else if (filterBy.equals("Telephone"))
			return patientRepository.findByTelephoneContainingIgnoreCaseAndEnabled(value, true);
		else if (filterBy.equals("Unique Number"))
			return patientRepository.findByUniqueNumContainingIgnoreCaseAndEnabled(value, true);

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
		Optional<Patient> p = patientRepository.findByIdAndEnabled(id, true);
		if (!p.isPresent())
			return null;

		return p.get();
	}

	public List<Patient> getSortedPatients(String type) {
		if (type.equals(""))
			return new ArrayList<>();

		int tip = Integer.parseInt(type);

		if (tip == 1) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("id"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			return page.getContent();
		} else if (tip == 2) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("firstName"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			
			return page.getContent();
		} else if (tip == 3) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("lastName"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			return page.getContent();
		} else if (tip == 4) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("address"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			return page.getContent();
		} else if (tip == 5) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("city"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			return page.getContent();
		} else if (tip == 6) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("state"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			return page.getContent();
		} else if (tip == 7) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("telephone"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			return page.getContent();
		} else if (tip == 8) {
			Pageable sort = PageRequest.of(0, 15, Sort.by("uniqueNum"));
			Page<Patient> page = patientRepository.findByEnabled(sort, true);
			return page.getContent();
		}

		return new ArrayList<>();
	}

	public List<MedicalFileViewDTO> getMedFile(String id, String email) {
		if (id == null)
			return new ArrayList<MedicalFileViewDTO>();
		if (id.equals(""))
			return new ArrayList<MedicalFileViewDTO>();

		MedicalStaff ms = msRepo.findByEmail(email);

		Long idFile = Long.parseLong(id);
		Optional<MedicalFile> mf = mfRepo.findById(idFile);

		if (!mf.isPresent())
			return new ArrayList<MedicalFileViewDTO>();

		boolean canWatch = false;
		for (MedFileEntry e : mf.get().getEntries()) {

			if (ms instanceof Doctor) {
				if (e.getDoctor().getId() == ms.getId()) {
					canWatch = true;
					break;
				}

			} else if (ms instanceof Nurse) {
				for (PrescriptionVerification p : e.getPrescriptions()) {
					if (p.getNurse().getId() == ms.getId()) {
						canWatch = true;
						break;
					}
				}
			}
		}

		if (canWatch) {
			List<MedicalFileViewDTO> dtos = new ArrayList<>();
			for (MedFileEntry e : mf.get().getEntries()) {
				MedicalFileViewDTO dto = new MedicalFileViewDTO(e.getDate(),
						e.getDoctor().getFirstName() + " " + e.getDoctor().getLastName(),
						e.getDiagnosis().getName(), e.getDiagnosis().getDesc());
				dtos.add(dto);
				
			}

			return dtos;

		}

		return new ArrayList<MedicalFileViewDTO>();

	}
}
