package team47pack.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Nurse;
import team47pack.models.dto.NurseInfoRequest;
import team47pack.security.TokenUtils;
import team47pack.service.NurseService;

@RestController
public class NurseController {
	
	@Autowired
	private NurseService nurseService;

	@Autowired
	private TokenUtils tokenUtils;

	@GetMapping(value = "/nurse/getInfo")
	@PreAuthorize("hasRole('NURSE')")
	public ResponseEntity<Nurse> getInfo(Principal user) {
		return ResponseEntity.ok(this.nurseService.getNurse(user.getName()));
	}

	@PostMapping(value = "/nurse/updateNurseInfo", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('NURSE')")
	public ResponseEntity<?> updateInfo(@RequestBody NurseInfoRequest req, Principal user) {

		boolean check = nurseService.updateNurseInfo(req);
		if (!check) {
			return ResponseEntity.badRequest().body("Unsuccessful");
		}

		return ResponseEntity.ok("Update successful!");
	}


}
