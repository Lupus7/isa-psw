package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Doctor;
import team47pack.models.dto.DoctorInfoRequest;
import team47pack.models.dto.RateRequest;
import team47pack.models.dto.SearchDoctorRequest;
import team47pack.security.TokenUtils;
import team47pack.service.DoctorService;

import java.security.Principal;
import java.util.ArrayList;

@RestController
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private TokenUtils tokenUtils;

	//@-------------------author:Jokara
	@GetMapping(value = "/doctor/getInfo")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<Doctor> getInfo(Principal user) {
		return ResponseEntity.ok(this.doctorService.getDoctor(user.getName()));
	}

	//@--------------------------author:Jokara
	@PostMapping(value = "/doctor/updateDoctorInfo", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<?> updateInfo(@RequestBody DoctorInfoRequest req, Principal user) {

		boolean check = doctorService.updateDoctorInfo(req);
		if (!check) {
			return ResponseEntity.badRequest().body("Unsuccessful");
		}

		return ResponseEntity.ok("Update successful!");
	}
	
	//@---------------author:Jokara
	@PostMapping(value="/doctor/searchDoctor",produces = "application/json",consumes = "application/json")
	@PreAuthorize("hasRole('PATIENT')")
	public ArrayList<Doctor> searchForDoctor(@RequestBody SearchDoctorRequest req){
		System.out.println(req.getName() +" " +req.getSurname());
		ArrayList<Doctor> doc = doctorService.search2(req);
		for(Doctor d:doc){
			d.setAverage(d.calculateRate());
			System.out.println("AVERAGE: " + d.getAverage());
		}
		return doc;
	}

	//@@@ Author: Boki
	@GetMapping(value = "doctor/{id}")
	public Doctor getDoctor(@PathVariable(value = "id") String id) {

		return doctorService.getDoctorByID(id);
	}
	// Author: bokimilinkovic
	@PostMapping(value = "doctor/leaveRate")
	@PreAuthorize("hasRole('PATIENT')")
	public void leaveRate(@RequestBody RateRequest rateRequest){
		System.out.println(rateRequest.getId() + " " +rateRequest.getValue());
		boolean b = doctorService.leaveRate(rateRequest);
		if(b){
			System.out.println("USPESNO");
		}
		System.out.println("Nije");
	}

}
