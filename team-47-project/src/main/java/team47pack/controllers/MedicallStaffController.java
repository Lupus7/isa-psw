package team47pack.controllers;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.dto.HolidayTimeOffRequest;
import team47pack.service.MedicallStaffService;

@RestController
public class MedicallStaffController {

	@Autowired
	private MedicallStaffService medicalstaffService;

	// @author:Jokara---------------------------------------------------------------
	@PostMapping(value = "/medicalStaff/holiday", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('DOCTOR') or hasRole('NURSE') ")
	public ResponseEntity<?> sendRequest(@RequestBody HolidayTimeOffRequest req, Principal user) throws ParseException {

		if (!user.getName().equals(req.getEmail()))
			return ResponseEntity.badRequest().body("Unsuccessful!");

		return medicalstaffService.sendRequest(req,user.getName());
	}
}
