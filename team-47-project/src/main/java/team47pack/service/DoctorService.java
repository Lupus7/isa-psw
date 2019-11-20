package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.Doctor;
import team47pack.repository.DoctorRepo;
import team47pack.repository.MedicalStaffRepo;
import team47pack.repository.UserRepo;

@Service
public class DoctorService {
	
	 	@Autowired
	    private UserRepo userRepo;
		@Autowired
	    private DoctorRepo doctorRepo;
		@Autowired
	    private MedicalStaffRepo medicalRepo;


	    public Doctor getDoctor(String email) {
	    	Doctor d = (Doctor) userRepo.findByEmail(email);
	    	Doctor d1 = doctorRepo.getOne(d.getId());    	
	    	d.setSpecialization(d1.getSpecialization());
	    	d1 = (Doctor) medicalRepo.getOne(d.getId());
	    	d.setOnVacation(d1.getOnVacation());
	    	
	        return d;
	    }

	    

}
