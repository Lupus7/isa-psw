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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.ExaminationType;
import team47pack.service.ExaminationTypeService;

//@author:Jokara
@RestController
@RequestMapping(value = "/ca")
public class ExaminationTypeController {

	@Autowired
	ExaminationTypeService examinationTypeService;

	@GetMapping(value = "/getExaminTypes")
	@PreAuthorize("hasRole('CADMIN')")
	public List<ExaminationType> getExaminTypes(Principal user) {
		return examinationTypeService.getExaminTypes(user.getName());
	}

	@GetMapping(value = "/getNextExmType")
	@PreAuthorize("hasRole('DOCTOR')")
	public List<ExaminationType> getNextExmType(Principal user) {
		return examinationTypeService.getNextExmType(user.getName());
	}

	@PostMapping(value = "/addExaminType", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> changeData(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);

		if (examinationTypeService.addExaminType(obj, user.getName()))
			return ResponseEntity.ok("Examination type successfulty added!");
		else
			return ResponseEntity.status(400).body("Could not add!");
	}

	@PostMapping(value = "/removeExaminType")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> removeExaminType(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if (obj == null || obj.get("id") == null)
			return ResponseEntity.status(400).body("Could not accept!");

		if (obj.get("id").equals(""))
			return ResponseEntity.status(400).body("Could not accept!");

		Long id = obj.getLong("id");

		if (!examinationTypeService.removeExaminType(id, user.getName()))
			return ResponseEntity.status(400).body("Unsuccessful!!");

		return ResponseEntity.ok("Examination type removed!");

	}

	@PostMapping(value = "/editExaminType")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> editRoom(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);

		if (!examinationTypeService.editExaminType(obj, user.getName()))
			return ResponseEntity.status(402).body("Name already taken!");

		return ResponseEntity.ok("Examination type edited!");

	}

	@PostMapping(value = "/searchExaminTypes")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<?> searchRoom(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);

		ArrayList<ExaminationType> ets = examinationTypeService.searchET(obj, user.getName());

		return ResponseEntity.ok(ets);

	}

}
