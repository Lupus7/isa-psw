package team47pack.controllers;

import java.nio.charset.Charset;
import java.security.Principal;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import team47pack.service.NextExaminationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")

public class UnitNextExaminationControllerTest {

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private NextExaminationService nextExaminationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();

	}

	// 3.18 Unit Success
	@Test
	@WithMockUser(username = "cadmin", password = "test", roles = "CADMIN")
	public void testSendRequestSuccessfull() throws Exception {

		JSONObject obj = new JSONObject();
		obj.put("idNextProcedure", 2);
		obj.put("idRoom", 9);
		obj.put("date", "05.03.2020");
		obj.put("time", "");
		obj.put("idDoctorNew", "none");
		String json = obj.toString();

		Principal mockPrincipal = Mockito.mock(Principal.class);

		Mockito.when(mockPrincipal.getName()).thenReturn("cadmin");
		Mockito.when(nextExaminationService.arrangeExamination(json, "cadmin")).thenReturn(true);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/ca/arrangeExamination").principal(mockPrincipal)
				.content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	// 3.18 Unit Empty JSON
	@Test
	@WithMockUser(username = "cadmin", password = "test", roles = "CADMIN")
	public void testSendRequestJSONEmpty() throws Exception {

		JSONObject obj = new JSONObject();
		String json = obj.toString();

		Principal mockPrincipal = Mockito.mock(Principal.class);

		Mockito.when(mockPrincipal.getName()).thenReturn("cadmin");
		Mockito.when(nextExaminationService.arrangeExamination(json, "cadmin")).thenReturn(false);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/ca/arrangeExamination").principal(mockPrincipal)
				.content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	// 3.18 Unit Not Authorized
	@Test
	@WithMockUser(username = "cadmin", password = "test", roles = "CADMIN")
	public void testSendRequestNotAuthorized() throws Exception {

		JSONObject obj = new JSONObject();
		obj.put("idNextProcedure", 2);
		obj.put("idRoom", 9);
		obj.put("date", "05.03.2020");
		obj.put("time", "");
		obj.put("idDoctorNew", "none");
		String json = obj.toString();

		Principal mockPrincipal = Mockito.mock(Principal.class);

		Mockito.when(nextExaminationService.arrangeExamination(json, null)).thenReturn(false);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/ca/arrangeExamination").principal(mockPrincipal)
				.content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	// 3.18 Unit Doctor Wrong Shift
	@Test
	@WithMockUser(username = "cadmin", password = "test", roles = "CADMIN")
	public void testSendRequestWrongshift() throws Exception {

		JSONObject obj = new JSONObject();
		obj.put("idNextProcedure", 2);
		obj.put("idRoom", 9);
		obj.put("date", "05.03.2020");
		obj.put("time", "");
		obj.put("idDoctorNew", "2");
		String json = obj.toString();

		Principal mockPrincipal = Mockito.mock(Principal.class);

		Mockito.when(mockPrincipal.getName()).thenReturn("cadmin");
		Mockito.when(nextExaminationService.arrangeExamination(json, "cadmin")).thenReturn(false);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/ca/arrangeExamination").principal(mockPrincipal)
				.content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

}
