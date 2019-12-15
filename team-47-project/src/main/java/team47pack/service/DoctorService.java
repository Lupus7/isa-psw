package team47pack.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.Rate;
import team47pack.models.User;
import team47pack.models.dto.DoctorInfoRequest;
import team47pack.models.dto.RateRequest;
import team47pack.models.dto.SearchDoctorRequest;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.MedicalStaffRepo;
import team47pack.repository.RateRepo;
import team47pack.repository.UserRepo;

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
	private MedicalStaffRepo medicalRepo;
  
  @Autowired
	private RateRepo rateRepo;

	public Doctor getDoctor(String email) {
		Doctor d = (Doctor) userRepo.findByEmail(email);
		Doctor d1 = doctorRepo.getOne(d.getId());
		d.setSpecialization(d1.getSpecialization());
		d1 = (Doctor) medicalRepo.getOne(d.getId());
		d.setOnVacation(d1.getOnVacation());

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

  //@author:Bojan
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
      
  //@author:Bojan
   public boolean leaveRate(RateRequest rateRequest) {
			Doctor dd = doctorRepo.getOne(rateRequest.getId());
			Rate rate = new Rate();
			rate.setValue(rateRequest.getValue());
			rateRepo.save(rate);
			dd.getRatings().add(rate);
			doctorRepo.save(dd);
			return true;
    }

}
