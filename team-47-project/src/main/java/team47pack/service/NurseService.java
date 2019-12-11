package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Nurse;
import team47pack.models.User;
import team47pack.models.dto.NurseInfoRequest;
import team47pack.models.dto.PrescriptionVerifyDTO;
import team47pack.repository.MedicalStaffRepo;
import team47pack.repository.UserRepo;

import java.util.Collection;

@Service
public class NurseService {
	
	@Autowired
    private UserRepo userRepo;
	@Autowired
	private MedicalStaffRepo medicalRepo;
	@Autowired
	private PresVerifService presVerifService;

    public Nurse getNurse(String email) {
    	Nurse n = (Nurse) userRepo.findByEmail(email);	
    	Nurse n1 = (Nurse) medicalRepo.getOne(n.getId());
    	n.setOnVacation(n1.getOnVacation());
    	
        return n;
    }
    
    public boolean updateNurseInfo(NurseInfoRequest req) {
    	User u = userRepo.findByEmail(req.getEmail());
    	
    	if(u == null || !(u instanceof Nurse))
    		return false;
   
    	u.setFirstName(req.getFirstName());
    	u.setLastName(req.getLastName());
    	u.setAddress(req.getAddress());
    	u.setState(req.getState());
    	u.setCity(req.getCity());
    	u.setTelephone(req.getTelephone());
    	u.setUniqueNum(req.getUniqueNum());
    	
    	userRepo.save(u);
    	    	
        return true;
    }

	// @author: Lupus7 (Sinisa Canak)
    public Collection<PrescriptionVerifyDTO> getPrescriptionVerification() {
		return presVerifService.getPresForVerification();
    }

	public boolean verifyPrescription(Long id, String nurseUsername) {
    	return presVerifService.verifyPrescription(id, getNurse(nurseUsername));
	}
}
