package team47pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import team47pack.models.Authority;
import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.HolidayTimeOff;
import team47pack.models.NextProcedure;
import team47pack.models.User;
import team47pack.models.dto.DoctorRateDTO;
import team47pack.models.dto.ReportDTO;
import team47pack.repository.AuthorityRepository;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.HolidayTimeOffRepository;
import team47pack.repository.NextProcedureRepo;
import team47pack.repository.UserRepo;

//@------------------------author:Jokara
@Service
public class ClinicAdminService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private NextProcedureRepo nextProcedureRepo;

	@Autowired
	private AuthorityRepository authorityRepo;

	@Autowired
	ClinicAdminRepo clinicAdminRepo;

	@Autowired
	ClinicRepo clinicRepo;

	@Autowired
	HolidayTimeOffRepository holidayTimeoffRepo;

	public ClinicAdmin getInfo(String email) {

		return clinicAdminRepo.findByEmail(email);
	}

	public List<HolidayTimeOff> getHolidayTimeoffRequests() {
		List<HolidayTimeOff> holidays = holidayTimeoffRepo.findByAccepted(false);
		List<HolidayTimeOff> hs = new ArrayList<>();
		for (HolidayTimeOff h : holidays) {
			if (h.getStaff().isEnabled())
				hs.add(h);
		}
		return hs;
	}

	public boolean acceptRequest(Long id) {
		if (holidayTimeoffRepo.acceptRequest(id) != 0)
			return true;
		return false;
	}

	public boolean rejectRequest(Long id) {
		if (holidayTimeoffRepo.rejectRequest(id) != 0)
			return true;
		return false;
	}

	public void save(ClinicAdmin ca) {
		clinicAdminRepo.save(ca);
	}

	public boolean updateData(JSONObject obj, String email) throws JSONException {

		if (obj.getString("firstName") == null || obj.getString("lastName") == null || obj.getString("address") == null
				|| obj.getString("state") == null || obj.getString("city") == null || obj.getString("telephone") == null
				|| obj.getString("uniqueNum") == null)
			return false;

		if (obj.getString("firstName").equals("") || obj.getString("lastName").equals("")
				|| obj.getString("address").equals("") || obj.getString("state").equals("")
				|| obj.getString("city").equals("") || obj.getString("telephone").equals("")
				|| obj.getString("uniqueNum").equals(""))
			return false;

		User u = userRepo.findByEmail(email);
		if (u == null || !(u instanceof ClinicAdmin))
			return false;

		u.setFirstName(obj.getString("firstName"));
		u.setLastName(obj.getString("lastName"));
		u.setAddress(obj.getString("address"));
		u.setState(obj.getString("state"));
		u.setCity(obj.getString("city"));
		u.setTelephone(obj.getString("telephone"));
		u.setUniqueNum(obj.getString("uniqueNum"));

		userRepo.save(u);

		return true;
	}

	public boolean addDoctor(JSONObject obj, String email) throws JSONException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		if (obj.getString("firstName") == null || obj.getString("lastName") == null || obj.getString("address") == null
				|| obj.getString("state") == null || obj.getString("city") == null || obj.getString("telephone") == null
				|| obj.getString("uniqueNum") == null || obj.getString("specialization") == null
				|| obj.getString("email") == null || obj.getString("shift") == null)
			return false;

		if (obj.getString("firstName").equals("") || obj.getString("lastName").equals("")
				|| obj.getString("address").equals("") || obj.getString("state").equals("")
				|| obj.getString("city").equals("") || obj.getString("telephone").equals("")
				|| obj.getString("uniqueNum").equals("") || obj.getString("specialization").equals("")
				|| obj.getString("email").equals("") || obj.getString("shift").equals(""))
			return false;

		User u = userRepo.findByEmail(obj.getString("email"));
		if (u != null)
			return false;

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		String hash = enc.encode("test");

		Doctor doctor = new Doctor(obj.getString("firstName"), obj.getString("lastName"), hash, obj.getString("email"),
				obj.getString("address"), obj.getString("city"), obj.getString("state"), obj.getString("telephone"),
				obj.getString("uniqueNum"), false, obj.getString("specialization"), obj.getInt("shift"));

		doctor.setClinicId(Long.parseLong("" + ca.getClinic()));
		doctor.setAccepted(true);
		doctor.setEnabled(true);

		// authorities

		Authority a1 = authorityRepo.findByName("ROLE_DOCTOR");
		Authority a2 = authorityRepo.findByName("ROLE_USER");
		ArrayList<Authority> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);
		doctor.setAuthorities(list);

		Optional<Clinic> clinic = clinicRepo.findById(Long.parseLong("" + ca.getClinic()));

		if (!clinic.isPresent())
			return false;

		clinic.get().getDoctors().add(doctor);
		doctorRepo.save(doctor);

		return true;
	}

	public boolean removeDoctor(JSONObject obj, String email) throws JSONException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		if (obj.getString("id") == null)
			return false;

		if (obj.getString("id").equals(""))
			return false;

		Optional<Clinic> clinic = clinicRepo.findById(Long.parseLong("" + ca.getClinic()));
		if (!clinic.isPresent())
			return false;

		Optional<Doctor> doctor = doctorRepo.findById(obj.getLong("id"));

		Optional<NextProcedure> nxp = nextProcedureRepo.findByDoctorId(doctor.get().getId());

		if (nxp.isPresent())
			return false;

		clinic.get().getDoctors().remove(doctor.get());
		doctor.get().setEnabled(false);
		doctor.get().setAuthorities(new ArrayList<Authority>());
		doctorRepo.save(doctor.get());

		return true;
	}

	public ArrayList<Doctor> searchDoctor(JSONObject obj, String email) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		Long clinicId = Long.parseLong("" + ca.getClinic());

		if (obj.getString("firstName") == null || obj.getString("lastName") == null || obj.getString("id") == null)
			return new ArrayList<>();

		if (!obj.getString("id").equals("")) {
			Optional<Doctor> d = doctorRepo.findById(obj.getLong("id"));
			if (d.isPresent() && d.get().isEnabled() && d.get().getClinicId() == clinicId) {
				ArrayList<Doctor> doc = new ArrayList<>();
				doc.add(d.get());
				return doc;
			}
		} else if (!obj.getString("firstName").equals("") && !obj.getString("lastName").equals("")
				&& obj.getString("id").equals("")) {

			ArrayList<Doctor> doctors = doctorRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
					obj.getString("firstName"), obj.getString("lastName"));
			ArrayList<Doctor> ds = new ArrayList<>();
			for (Doctor d : doctors) {
				if (d.isEnabled() && d.getClinicId() == clinicId)
					ds.add(d);
			}

			return ds;

		} else if (!obj.getString("firstName").equals("") && obj.getString("lastName").equals("")
				&& obj.getString("id").equals("")) {

			ArrayList<Doctor> doctors = doctorRepo.findByFirstNameContainingIgnoreCase(obj.getString("firstName"));
			ArrayList<Doctor> ds = new ArrayList<>();
			for (Doctor d : doctors) {
				if (d.isEnabled() && d.getClinicId() == clinicId)
					ds.add(d);
			}

			return ds;

		} else if (obj.getString("firstName").equals("") && !obj.getString("lastName").equals("")
				&& obj.getString("id").equals("")) {

			ArrayList<Doctor> doctors = doctorRepo.findByLastNameContainingIgnoreCase(obj.getString("lastName"));
			ArrayList<Doctor> ds = new ArrayList<>();
			for (Doctor d : doctors) {
				if (d.isEnabled() && d.getClinicId() == clinicId)
					ds.add(d);
			}

			return ds;

		}

		return new ArrayList<>();
	}

	public ReportDTO getReport(String name) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(name);
		if (ca == null)
			return null;
		Optional<Clinic> clinic = clinicRepo.findById(Long.parseLong("" + ca.getClinic()));
		if (!clinic.isPresent())
			return null;
		double rate = clinic.get().calculateRate();
		ReportDTO report = new ReportDTO(clinic.get().getName(), rate);
		for (Doctor d : clinic.get().getDoctors()) {
			DoctorRateDTO drdto = new DoctorRateDTO(d.getFirstName() + " " + d.getLastName(), d.calculateRate());
			report.getDoctors().add(drdto);
		}

		return report;
	}
}
