package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Nurse;
import team47pack.models.dto.NurseInfoRequest;
import team47pack.models.dto.PrescriptionVerifyDTO;
import team47pack.security.TokenUtils;
import team47pack.service.NurseService;

import java.security.Principal;
import java.util.Collection;

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

	// @author: Lupus7 (Sinisa Canak)
	@GetMapping(value = "/nurse/prescription")
	@PreAuthorize("hasRole('NURSE')")
	public Collection<PrescriptionVerifyDTO> getPrescriptions() {
		return nurseService.getPrescriptionVerification();
	}

	// @author: Lupus7 (Sinisa Canak)
	@PutMapping(value = "/nurse/prescription/{id}")
	@PreAuthorize("hasRole('NURSE')")
	public ResponseEntity<?> verifyPrescription(@PathVariable(value = "id") Long id, Principal nurse) {
		if (nurseService.verifyPrescription(id, nurse.getName()))
			return ResponseEntity.ok("Verification successful!");
		return ResponseEntity.badRequest().body("Verification error. Check if id is valid");
	}
}
