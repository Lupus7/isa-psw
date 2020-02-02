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
import team47pack.models.ExaminationType;
import team47pack.models.NextProcedure;
import team47pack.models.Room;
import team47pack.service.FastExaminationService;

@RestController
public class FastExaminationController {

	@Autowired
	private FastExaminationService fastExamService;

	@PostMapping(value = "/ca/defineFastExamin")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<?> postExamination(@RequestBody String json, Principal p)
			throws ParseException, JSONException {
		return (fastExamService.addExamination(new JSONObject(json), p.getName()))
				? ResponseEntity.ok("Added Fast Examination!")
				: ResponseEntity.status(400).body("Invalid information");
	}

	@GetMapping(value = "/patient/getFastExaminations")
	@PreAuthorize("hasRole('PATIENT')")
	public List<NextProcedure> getFastExaminations(Principal user) {
		return fastExamService.getFastExaminations(user.getName());

	}
	
	@GetMapping(value = "/ca/getClinicRooms")
	@PreAuthorize("hasRole('CADMIN')")
	public List<Room> getRooms(Principal user) {
		return fastExamService.getRooms(user.getName());

	}
	
	@GetMapping(value = "/ca/getClinicDoctors")
	@PreAuthorize("hasRole('CADMIN')")
	public List<Doctor> getDoctors(Principal user) {
		return fastExamService.getDoctors(user.getName());

	}
	
	@GetMapping(value = "/ca/getExaminationTypes/{spec}")
	@PreAuthorize("hasRole('CADMIN')")
	public List<ExaminationType> getDoctors(@PathVariable(value = "spec") String spec,Principal user) {
		String specialization = spec;
		if(spec.contains("%20"))
			specialization.replace("%20", " ");
		return fastExamService.getExaminationTypes(specialization,user.getName());

	}

}
