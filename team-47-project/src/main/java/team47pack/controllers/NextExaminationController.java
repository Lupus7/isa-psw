package team47pack.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Doctor;
import team47pack.models.NextProcedure;
import team47pack.service.DoctorService;
import team47pack.service.NextExaminationService;

@RestController
public class NextExaminationController {
	
	@Autowired
	private NextExaminationService nextExamService;
	
	@Autowired
	private DoctorService doctorService;
	
    @GetMapping(value="/ca/getNextExamins")
    @PreAuthorize("hasRole('CADMIN')")
    public java.util.List<NextProcedure> getNextExamins(Principal user){
        return nextExamService.getNextExns(user.getName());
 
    }
    
    @GetMapping(value="/ca/getClinicDoctors/{spec}")
    @PreAuthorize("hasRole('CADMIN')")
    public List<Doctor> getClinicDoctors(@PathVariable(value = "spec") String spec,Principal user){
    	System.out.println("------------"+spec);
        return doctorService.getClinicDoctors(spec,user.getName());
 
    }
    
    @PostMapping(value = "/ca/arrangeExamination")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> arrangeExamination(@RequestBody String json, Principal user) throws JSONException, ParseException {

		if (!nextExamService.arrangeExamination(json, user.getName()))
			return ResponseEntity.status(400).body("Unsuccessful!!");

		return ResponseEntity.ok("Room was successfuly arranged for examination!");

	}

}
