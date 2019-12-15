package team47pack.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Doctor;
import team47pack.models.dto.DoctorInfoRequest;
import team47pack.models.dto.SearchDoctorRequest;
import team47pack.security.TokenUtils;
import team47pack.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private TokenUtils tokenUtils;

	// @-------------------author:Jokara
	@GetMapping(value = "/doctor/getInfo")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<Doctor> getInfo(Principal user) {
		return ResponseEntity.ok(this.doctorService.getDoctor(user.getName()));
	}

	// @-------------------author:Jokara
	@GetMapping(value = "/doctor/getDoctors")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<List<Doctor>> getDoctors(Principal user) {
		return ResponseEntity.ok(doctorService.getDoctors(user.getName()));
	}

	// @--------------------------author:Jokara
	@PostMapping(value = "/doctor/updateDoctorInfo", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<?> updateInfo(@RequestBody DoctorInfoRequest req, Principal user) {

		boolean check = doctorService.updateDoctorInfo(req);
		if (!check) {
			return ResponseEntity.badRequest().body("Unsuccessful");
		}

		return ResponseEntity.ok("Update successful!");
	}

	// @---------------author:Jokara
	@PostMapping(value = "/doctor/searchDoctor", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('PATIENT')")
	public ArrayList<Doctor> searchForDoctor(@RequestBody SearchDoctorRequest req) {
		System.out.println(req.getName() + " " + req.getSurname());
		return doctorService.search2(req);
	}

	// @@@ Author: Boki
	@GetMapping(value = "doctor/{id}")
	public Doctor getDoctor(@PathVariable(value = "id") String id) {

		return doctorService.getDoctorByID(id);
	}

}
