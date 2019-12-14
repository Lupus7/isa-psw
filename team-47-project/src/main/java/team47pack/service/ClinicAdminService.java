package team47pack.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.ClinicAdmin;
import team47pack.models.HolidayTimeOff;
import team47pack.models.User;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.HolidayTimeOffRepository;
import team47pack.repository.UserRepo;

import java.util.List;

//@------------------------author:Jokara
@Service
public class ClinicAdminService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	ClinicAdminRepo clinicAdminRepo;

	@Autowired
	HolidayTimeOffRepository holidayTimeoffRepo;

	public ClinicAdmin getInfo(String email) {

		return clinicAdminRepo.findByEmail(email);
	}

	public List<HolidayTimeOff> getHolidayTimeoffRequests() {
		return holidayTimeoffRepo.findByAccepted(false);
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
}
