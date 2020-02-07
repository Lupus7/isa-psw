package team47pack.controllers;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import team47pack.models.Patient;
import team47pack.service.ExaminationService;
import team47pack.service.PatientService;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
//@ContextConfiguration(classes = {UnitPatientControllerTest.TestConfig.class, Team47ProjectApplication.class})
public class UnitPatientControllerTest {

    private static final String URL_PREFIX = "/api/students";

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

//    @Profile("test")
//    @Configuration
//    static class TestConfig{
//        @Bean
//        @Primary
//        public PatientService mockB(){
//            PatientService patientService = Mockito.mock(PatientService.class);
//            Mockito.when(patientService.getAllPatients()).thenReturn(new ArrayList<>());
//            return patientService;
//        }
//    }

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @MockBean
//    private PatientService patientService;

//    @MockBean
//    private ExaminationService examinationService;
    @MockBean
     private PatientService patientService ;

    @MockBean
    private ExaminationService examinationService;

//    @PostConstruct
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.
//                webAppContextSetup(webApplicationContext).build();
//    }

    @Before
    public void setUp() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //mockMvc = MockMvcBuilders.standaloneSetup(new ExaminationService()).build();
         //patientService = Mockito.mock(PatientService.class);
         //when(patientService.getAllPatients()).thenReturn(new ArrayList<>());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSendRequestSuccessfull()throws  Exception{
        //JSONObject object = new JSONObject("{\"date\":\"2011-11-02T02:50:12.208Z\",\"doctor\":\"doctor2\"}");
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doctor2");
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("test1");
        Mockito.when(examinationService.sendRequest(obj,"test1")).thenReturn(true);
        String json = obj.toString();
        System.out.println(json );
        ResultActions res = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/patient/requestExam")
                .contentType(contentType).principal(mockPrincipal)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSendRequestInvalidDate()throws  Exception{
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T");
        obj.put("doctor","doctor2");
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("test1");
        Mockito.when(examinationService.sendRequest(obj,"test1")).thenReturn(false);
        String json = obj.toString();
        System.out.println(json );
        ResultActions res = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/patient/requestExam")
                        .contentType(contentType).principal(mockPrincipal)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
        assertThat("false").isEqualTo(res.andReturn().getResponse().getContentAsString());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSendRequestInvalidDoctorEmail()throws  Exception{
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doct22");
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("test1");
        Mockito.when(examinationService.sendRequest(obj,"test1")).thenReturn(false);
        String json = obj.toString();
        System.out.println(json );
        ResultActions res = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/patient/requestExam")
                        .contentType(contentType).principal(mockPrincipal)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
        assertThat("false").isEqualTo(res.andReturn().getResponse().getContentAsString());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSendRequestInvalidPrincipalEmail()throws  Exception{
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doctor2");
        Principal mockPrincipal = Mockito.mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("test122");
        Mockito.when(examinationService.sendRequest(obj,"test122")).thenReturn(false);
        String json = obj.toString();
        System.out.println(json );
        ResultActions res = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/patient/requestExam")
                        .contentType(contentType).principal(mockPrincipal)
                        .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
        assertThat("false").isEqualTo(res.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testGetAllPatients() throws Exception {
        PatientService mockServoce = Mockito.mock(PatientService.class);
        ArrayList<Patient> pat = new ArrayList<>();
        Patient p = new Patient();
        p.setAddress("asdasd");
        p.setCity("asdasd");
        p.setFirstName("ADASD");
        p.setLastName("Adasddd");
        pat.add(p);
        Mockito.when(patientService.getAllPatients()).thenReturn(pat);
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/patients").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
