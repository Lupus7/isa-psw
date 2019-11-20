package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Doctor;
import team47pack.security.TokenUtils;
import team47pack.service.DoctorService;

@RestController
public class DoctorController {
	
	@Autowired
    private DoctorService doctorService;
	
	@Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value="/doctor/getInfo")
    public Doctor getInfo(@RequestHeader(name="Authorization") String token) {

    	String email = tokenUtils.getUsernameFromToken(token.substring(7));
    	String role = tokenUtils.getRole(token);
    	Doctor doctor = null;
    	
    
   	
    	if(!role.equals("ROLE_DOCTOR") || email == null )
    		return null;
    		
    	doctor = doctorService.getDoctor(email);	
    	
        return doctor;
    }

	

}
