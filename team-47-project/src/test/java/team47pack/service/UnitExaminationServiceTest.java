package team47pack.service;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team47pack.models.*;
import team47pack.repository.DoctorRepo;
import team47pack.repository.ExaminationRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class UnitExaminationServiceTest {

    @Autowired
    ExaminationService examinationService;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorRepo doctorRepo;

    @MockBean
    private ClinicService clinicService;

    @MockBean
    private ExaminationTypeService examinationTypeService;

    @MockBean
    private ExaminationRepo examinationRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendRequest() throws JSONException, ParseException {
        JSONObject obj = new JSONObject();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = inputFormat.parse("2011-11-02T02:50:12.208Z");
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doctor2");
        String name = "test1";
        Boolean reserved=false;
        Patient p = new Patient();
        p.setEmail("test1");
        Mockito.when(patientService.getPatient("test1")).thenReturn(p);
        Doctor d = new Doctor();
        d.setEmail("doctor2");
        d.setId((long)2);
        d.setSpecialization("jeben");
        Mockito.when(doctorRepo.findByEmail("doctor2")).thenReturn(d);
        Clinic c = new Clinic();
        c.setId((long)2);
        Mockito.when(clinicService.getClinicByDoktorID((long)2)).thenReturn(c);
        Mockito.when(examinationTypeService.findByClinicID(c.getId())).thenReturn(new ArrayList<>());
        Examination ee = new Examination("jeben",date,p,d,false,new ExaminationType());
        Mockito.when(examinationRepo.save(ee)).thenReturn(null);
        c.setName("klinika 1");
        try {
            reserved = examinationService.sendRequest(obj,name);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertThat(reserved).isEqualTo(true);
    }

    @Test
    public void testSendRequestInvalidPatient() throws JSONException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doctor2");
        String name = "test1";
        Boolean reserved=false;
        Patient p = new Patient();
        p.setEmail("test1");
        Mockito.when(patientService.getPatient("test1")).thenReturn(null);
        try {
            reserved = examinationService.sendRequest(obj,name);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertThat(reserved).isEqualTo(false);
    }

    @Test
    public void testSendRequestDoctorNull() throws JSONException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doctor2");
        String name = "test1";
        Boolean reserved=false;
        Patient p = new Patient();
        p.setEmail("test1");
        Mockito.when(patientService.getPatient("test1")).thenReturn(p);
        Doctor d = new Doctor();
        d.setEmail("doctor2");
        d.setId((long)2);
        d.setSpecialization("jeben");
        Mockito.when(doctorRepo.findByEmail("doctor2")).thenReturn(null);
        try {
            reserved = examinationService.sendRequest(obj,name);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertThat(reserved).isEqualTo(false);
    }

    @Test
    public void testSendRequestClinicNUll() throws JSONException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doctor2");
        String name = "test1";
        Boolean reserved=false;
        Patient p = new Patient();
        p.setEmail("test1");
        Mockito.when(patientService.getPatient("test1")).thenReturn(p);
        Doctor d = new Doctor();
        d.setEmail("doctor2");
        d.setId((long)2);
        d.setSpecialization("jeben");
        Mockito.when(doctorRepo.findByEmail("doctor2")).thenReturn(d);
        Clinic c = new Clinic();
        c.setId((long)2);
        Mockito.when(clinicService.getClinicByDoktorID((long)2)).thenReturn(null);
        try {
            reserved = examinationService.sendRequest(obj,name);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertThat(reserved).isEqualTo(false);
    }

}
