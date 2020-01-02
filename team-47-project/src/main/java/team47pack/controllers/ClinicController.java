package team47pack.controllers;

import java.security.Principal;
import java.util.ArrayList;
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

import team47pack.models.Clinic;
import team47pack.models.Doctor;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.models.dto.RateRequest;
import team47pack.security.TokenUtils;
import team47pack.service.ClinicService;

@RestController
public class ClinicController {
	@Autowired
	private ClinicService clinicService;

	@PostMapping(value = "/clinic/search", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('PATIENT')")
	public ArrayList<ClinicSearchResult> searchForClinics(@RequestBody ClinicSearchRequest csr) {
		ArrayList<ClinicSearchResult> rez = clinicService.search(csr);
		return rez;
	}

	@GetMapping(value = "clinic/{id}/getAvailableDoctors")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Doctor> getDoctors(@PathVariable(value = "id") Long id) {
		Clinic clinic = clinicService.getClinic(id);
		clinic.setAverage(clinic.calculateRate());
		if (clinic != null) {

			List<Doctor> dd = clinic.getDoctors();
			for (Doctor d : dd) {
				d.setAverage(d.calculateRate());
			}
			return dd;

		}

		return null;
	}

	@PostMapping(value = "clinic/leaveRate")
	@PreAuthorize("hasRole('PATIENT')")
	public void leaveRate(@RequestBody RateRequest rateRequest) {
		boolean b = clinicService.leaveRate(rateRequest);

	}

	// @author:Jokara
	@GetMapping(value = "clinic/getInfo")
	@PreAuthorize("hasRole('CADMIN')")
	public Clinic getClinicInfo(Principal user) {

		return clinicService.getClinicInfo(user.getName());
	}

	// @author:Jokara
	@PostMapping(value = "clinic/updateInfo", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> updateInfo(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);

		if (clinicService.updateInfo(obj, user.getName()))
			return ResponseEntity.ok("Clinic info successfulty updated!");
		else
			return ResponseEntity.status(400).body("Could not add!");
	}
}