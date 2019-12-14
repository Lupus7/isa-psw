package team47pack.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import team47pack.models.ClinicAdmin;
import team47pack.models.HolidayTimeOff;
import team47pack.service.ClinicAdminService;
import team47pack.service.EmailService;

//@author:Jokara
@RestController
@RequestMapping(value = "/ca")
@PreAuthorize("hasRole('CADMIN')")
public class ClinicAdminController {

	@Autowired
	ClinicAdminService clinicAdminService;

	@Autowired
	private EmailService emailService;

	@GetMapping(value = "/getInfo")
	public ClinicAdmin getInfo(Principal user) {
		return clinicAdminService.getInfo(user.getName());
	}

	@GetMapping(value = "/request-list")
	public List<HolidayTimeOff> reqList() {
		return clinicAdminService.getHolidayTimeoffRequests();
	}

	@PostMapping(value = "/updateInfo", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> changeData(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);

		if (clinicAdminService.updateData(obj, user.getName()))
			return ResponseEntity.ok("Successfulty updated data!");
		else
			return ResponseEntity.status(400).body("Could not accept");
	}

	@PostMapping(value = "/request-list/accept")
	public ResponseEntity<String> acceptRequest(@RequestBody String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if (obj == null || obj.get("mail") == null || obj.get("id") == null || obj.get("dateB") == null
				|| obj.get("type") == null || obj.get("dateE") == null)
			return ResponseEntity.status(400).body("Could not accept");

		if (obj.get("mail").equals("") || obj.get("id").equals("") || obj.get("dateB").equals("")
				|| obj.get("dateE").equals("") || obj.get("type").equals(""))
			return ResponseEntity.status(400).body("Could not accept");

		String Email = (String) obj.get("mail");
		Long id = (Long) obj.getLong("id");
		Long d1 = (Long) obj.get("dateB");
		Long d2 = (Long) obj.get("dateE");
		Date date1 = new Date(d1);
		Date date2 = new Date(d2);
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String dat1 = formatter.format(date1);
		String dat2 = formatter.format(date2);

		String type = (String) obj.get("type");
		String body = "Dear Sir/Madam \n \nYour " + type + " request from " + dat1 + " to " + dat2
				+ " has been accepted!" + "\n" + "Enjoy your free time." + "\n\n" + "Admin team";

		System.out.println(Email);
		System.out.println(body);

		if (clinicAdminService.acceptRequest(id)) {
			emailService.sendSimpleMessage(Email, type, body);
			return ResponseEntity.ok("Successful");
		} else
			return ResponseEntity.status(400).body("Could not accept");
	}

	@PostMapping(value = "request-list/reject")
	public ResponseEntity<String> rejectRequest(@RequestBody String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if (obj == null || obj.get("expl") == null || obj.get("mail") == null || obj.get("id") == null
				|| obj.get("type") == null)
			return ResponseEntity.status(400).body("Could not accept");
		if (obj.get("expl").equals("") || obj.get("mail").equals("") || obj.get("id").equals("")
				|| obj.get("type").equals(""))
			return ResponseEntity.status(400).body("Could not accept");

		Long id = (Long) obj.getLong("id");
		String email = (String) obj.get("mail");
		String expl = (String) obj.get("expl");
		String type = (String) obj.get("type");
		String body = "Dear Sir/Madam \n \nYour " + type + " request" + " has been rejected!" + "\n"
				+ "Reason for rejecting: " + expl + "\nAll the best!" + "\n\n" + "Admin team";

		if (clinicAdminService.rejectRequest(id)) {
			emailService.sendSimpleMessage(email, type, body);
			return ResponseEntity.ok("Successful");
		} else
			return ResponseEntity.status(400).body("Could not accept");
	}
}
