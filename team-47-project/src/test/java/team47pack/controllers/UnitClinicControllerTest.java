package team47pack.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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

import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.service.ClinicService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class UnitClinicControllerTest {

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ClinicService clinicService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();

	}

	// 3.13 SearchForClinicsSuccessfull
	@Test
	@WithMockUser(username = "test2", password = "test", roles = "PATIENT")
	public void testSearchForClinicsSuccessfull() throws Exception {
		ClinicSearchRequest req = new ClinicSearchRequest();
		req.setLocation("dr.andre");

		JSONObject obj = new JSONObject();
		obj.put("location", "dr.andre");
		String json = obj.toString();

		Principal mockPrincipal = Mockito.mock(Principal.class);

		Mockito.when(mockPrincipal.getName()).thenReturn("test2");
		Mockito.when(clinicService.search(req)).thenReturn(new ArrayList<>());
		this.mockMvc.perform(MockMvcRequestBuilders.post("/clinic/search").principal(mockPrincipal).content(json)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	// 3.13 Get Clinic Successfull
	@Test
	@WithMockUser(username = "cadmin", password = "test", roles = "CADMIN")
	public void testGetClinicSuccessfull() throws Exception {
		String json = "";

		Principal mockPrincipal = Mockito.mock(Principal.class);

		Mockito.when(mockPrincipal.getName()).thenReturn("cadmin");
		Mockito.when(clinicService.getClinicInfo("cadmin")).thenReturn(new Clinic());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinic/getInfo").principal(mockPrincipal).content(json)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}


}
