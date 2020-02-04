package team47pack.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Doctor;
import team47pack.models.ExaminationType;
import team47pack.models.Room;
import team47pack.models.dto.FastExamDto;
import team47pack.service.FastExaminationService;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

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
	public List<FastExamDto> getFastExaminations(Principal user) {
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

	@PostMapping(value = "/patient/fastExams/{id}")
	@PreAuthorize("hasRole('PATIENT')")
	public Boolean appoint(@PathVariable(value="id")Long id, Principal user){
		System.out.println(id + "*******"+user.getName());
		fastExamService.appoint(id,user.getName());
		return true;
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
