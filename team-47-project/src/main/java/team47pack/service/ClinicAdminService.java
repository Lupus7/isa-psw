package team47pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.ClinicAdmin;
import team47pack.models.HolidayTimeOff;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.HolidayTimeOffRepository;

//@author:Jokara
@Service
public class ClinicAdminService {

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
        if(holidayTimeoffRepo.rejectRequest(id) != 0)
            return true;
        return false;
    }

}
