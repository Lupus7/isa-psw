package team47pack.controllers;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import team47pack.models.NextProcedure;
import team47pack.service.NextExaminationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class IntegrationNextExaminationControllerTest {

	@Autowired
	private NextExaminationService nextExaminationService;

	// 3.18 Get NextExamins - Success
	@Test
	public void testGetNextExamiantions() {
		List<NextProcedure> list = nextExaminationService.getNextExns("cadmin");
		// check if in the database now
		Assert.assertTrue(list.size() > 0);
	}

	// 3.18 Arrange NextExamination-Success
	@Test
	public void testArrangeNextExamiantionSuccess() throws JSONException, ParseException {

		JSONObject obj = new JSONObject();
		obj.put("idNextProcedure", 2);
		obj.put("idRoom", 9);
		obj.put("date", "2020-03-05");
		obj.put("time", 10);
		obj.put("idDoctorNew", "none");
		String json = obj.toString();

		boolean t = nextExaminationService.arrangeExamination(json, "cadmin");
		// check if in the database now
		Assert.assertTrue(t);
	}

	// 3.18 Appoinment Already Taken Arrange NextExamination
	@Test
	public void testAppoinmentAlreadyTakenArrangeNextExamiantion() throws JSONException, ParseException {

		JSONObject obj = new JSONObject();
		obj.put("idNextProcedure", 2);
		obj.put("idRoom", 9);
		obj.put("date", "2020-03-05");
		obj.put("time", "");
		obj.put("idDoctorNew", "none");
		String json = obj.toString();

		boolean t = nextExaminationService.arrangeExamination(json, "cadmin");
		Assert.assertFalse(t);
	}

	// 3.18 Wrong Doctor Shift
	@Test
	public void testWrongDoctorShiftNextExamiantion() throws JSONException, ParseException {

		JSONObject obj = new JSONObject();
		obj.put("idNextProcedure", 2);
		obj.put("idRoom", 9);
		obj.put("date", "2020-03-07");
		obj.put("time", "");
		obj.put("idDoctorNew", 13);
		String json = obj.toString();

		boolean t = nextExaminationService.arrangeExamination(json, "cadmin");
		Assert.assertFalse(t);
	}


}
