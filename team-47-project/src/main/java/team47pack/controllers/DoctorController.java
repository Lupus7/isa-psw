package team47pack.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Doctor;
import team47pack.models.dto.DoctorInfoRequest;
import team47pack.security.TokenUtils;
import team47pack.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private TokenUtils tokenUtils;

	@GetMapping(value = "/doctor/getInfo")
	@PreAuthorize("hasRole('DOCTOR')")
	public Doctor getInfo(Principal user) {
		return this.doctorService.getDoctor(user.getName());
	}

	@PostMapping(value = "/doctor/updateDoctorInfo", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> updateInfo(@RequestBody DoctorInfoRequest req) {

		boolean check = doctorService.updateDoctorInfo(req);
		if (!check) {
			return ResponseEntity.badRequest().body("Unsuccessful");
		}

		return ResponseEntity.ok("Update successful!");
	}

}
