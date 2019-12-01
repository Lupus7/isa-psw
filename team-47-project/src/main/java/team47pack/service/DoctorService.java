package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.Doctor;
import team47pack.models.User;
import team47pack.models.dto.DoctorInfoRequest;
import team47pack.models.dto.SearchDoctorRequest;
import team47pack.repository.DoctorRepo;
import team47pack.repository.MedicalStaffRepo;
import team47pack.repository.UserRepo;

import java.util.ArrayList;

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
	    
	    public boolean updateDoctorInfo(DoctorInfoRequest req) {
	    	User u = userRepo.findByEmail(req.getEmail());
	    	
	    	if(u == null || !(u instanceof Doctor))
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
	    	if(req.getName()!="" && req.getSurname()==""){
	    		return doctorRepo.findByFirstNameContainingIgnoreCase(req.getName());
			}else if(req.getName()=="" && req.getSurname()!=""){
	    		return  doctorRepo.findByLastNameContainingIgnoreCase(req.getSurname());
			}else {
				return doctorRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(req.getName(), req.getSurname());
			}
    }
}
