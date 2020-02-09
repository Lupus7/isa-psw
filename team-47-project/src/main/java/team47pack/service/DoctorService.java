package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.DoctorInfoRequest;
import team47pack.models.dto.RateRequest;
import team47pack.models.dto.SearchDoctorRequest;
import team47pack.repository.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ClinicAdminRepo clinicAdminRepo;

	@Autowired
	private ClinicRepo clinicRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private ExaminationRepo exRepo;
	@Autowired
	private MedicalStaffRepo medicalRepo;

	@Autowired
	private RateRepo rateRepo;

	@Autowired
	private OperationRepo opRepo;

	public Doctor getDoctor(String email) {
		Doctor d = doctorRepo.findByEmail(email);

		return d;
	}

	public boolean updateDoctorInfo(DoctorInfoRequest req) {
		User u = userRepo.findByEmail(req.getEmail());

		if (u == null || !(u instanceof Doctor))
			return false;

		Doctor d = (Doctor) u;
		u.setFirstName(req.getFirstName());
		u.setLastName(req.getLastName());
		u.setAddress(req.getAddress());
		u.setState(req.getState());
		u.setCity(req.getCity());
		u.setTelephone(req.getTelephone());
		u.setUniqueNum(req.getUniqueNum());
		d.setSpecialization(req.getSpecialization());

		userRepo.save(u);
		doctorRepo.save(d);

		return true;
	}

	public ArrayList<Doctor> searchDoctor(SearchDoctorRequest req) {
		if (req.getName() != "" && req.getSurname() == "") {
			ArrayList<Doctor> doctors = doctorRepo.findByFirstNameContainingIgnoreCase(req.getName());
			ArrayList<Doctor> ds = new ArrayList<>();
			for (Doctor d : doctors) {
				if (d.isEnabled())
					ds.add(d);
			}
			return ds;

		} else if (req.getName() == "" && req.getSurname() != "") {

			ArrayList<Doctor> doctors = doctorRepo.findByLastNameContainingIgnoreCase(req.getSurname());
			ArrayList<Doctor> ds = new ArrayList<>();
			for (Doctor d : doctors) {
				if (d.isEnabled())
					ds.add(d);
			}
			return ds;

		} else {

			ArrayList<Doctor> doctors = doctorRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
					req.getName(), req.getSurname());

			ArrayList<Doctor> ds = new ArrayList<>();
			for (Doctor d : doctors) {
				if (d.isEnabled())
					ds.add(d);
			}
			return ds;

		}
	}

	// @author:Bojan
	public ArrayList<Doctor> search2(SearchDoctorRequest req) {
		Specification<Doctor> spec = Specification.where(DoctorSpecification.doctorFirstName(req.getName()))
				.and(DoctorSpecification.doctormLastName(req.getSurname()))
				.and(DoctorSpecification.doctorSpecialization(req.getSpecialization()));
		ArrayList<Doctor> doctors = new ArrayList<>(
				new HashSet<>(doctorRepo.findAll(spec, PageRequest.of(0, 10, Sort.by("firstName"))).toList()));

		ArrayList<Doctor> ds = new ArrayList<>();
		for (Doctor d : doctors) {
			if (d.isEnabled())
				ds.add(d);
		}
		return ds;
	}

	public Doctor getDoctorByID(String idd) {
		if (idd == null)
			return null;
		if (idd.equals(""))
			return null;

		Long id = Long.parseLong(idd);
		if (id < 1 || id == null)
			return null;
		Doctor d = doctorRepo.getOne(id);
		if (d == null)
			return null;

		return d;
	}

	public List<Doctor> getDoctors(String email) {
		List<Doctor> doctors = new ArrayList<>();
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);

		if (ca == null)
			return doctors;

		Optional<Clinic> clinic = clinicRepo.findById(Long.parseLong("" + ca.getClinic()));

		if (!clinic.isPresent())
			return doctors;

		return clinic.get().getDoctors();

	}

	// @author:Bojan
	public boolean leaveRate(RateRequest rateRequest) {
		Doctor dd = doctorRepo.getOne(rateRequest.getId());
		Rate rate = new Rate();
		try {
			Examination e = exRepo.getOne(rateRequest.getExamination());
			e.setRatedDoctor(true);
			exRepo.save(e);
		} catch (Exception e) {
			Optional<Operation> o = opRepo.findById(rateRequest.getExamination());
			o.get().setRatedDoctor(true);
			opRepo.save(o.get());
		}
		rate.setValue(rateRequest.getValue());
		rateRepo.save(rate);
		dd.getRatings().add(rate);
		doctorRepo.save(dd);
		return true;
	}

	// @author:Jokara
	public List<Doctor> getClinicDoctors(String spec, String email) {
		String specialization = spec.replace("%20", " ");
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		Long clinicId = Long.parseLong("" + ca.getClinic());

		return doctorRepo.findBySpecializationAndClinicIdAndOnVacation(specialization, clinicId,false);
	}


	public List<Doctor> getClinicDoctors(String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		Long clinicId = Long.parseLong("" + ca.getClinic());

		return doctorRepo.findByClinicIdAndOnVacation(clinicId,false);
	}
}
