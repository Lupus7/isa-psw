package team47pack.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.dto.DayReportDTO;
import team47pack.models.dto.MonthDTO;
import team47pack.models.dto.WeekDTO;
import team47pack.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	ReportService reportService;

	// vrati DTO

	@PostMapping(value = "/ca/day", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<DayReportDTO>> day(@RequestBody String json, Principal user)
			throws JSONException, ParseException {
		JSONObject obj = new JSONObject(json);

		return ResponseEntity.ok(reportService.dayReport(obj, user.getName()));

	}

	@PostMapping(value = "/ca/week", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<WeekDTO>> week(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);

		return ResponseEntity.ok(reportService.weekReport(obj, user.getName()));

	}

	@PostMapping(value = "/ca/month", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<MonthDTO>> month(@RequestBody String json, Principal user) throws JSONException, ParseException {

		JSONObject obj = new JSONObject(json);

		return ResponseEntity.ok(reportService.monthReport(obj, user.getName()));
	}

	@PostMapping(value = "/ca/income", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<DayReportDTO>> income(@RequestBody String json, Principal user)
			throws JSONException, ParseException {
		JSONObject obj = new JSONObject(json);

		return ResponseEntity.ok(reportService.incomeReport(obj, user.getName()));
	}

}
