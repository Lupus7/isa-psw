package team47pack.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import team47pack.models.Examination;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class ExaminationServiceTest {

    @Autowired
    ExaminationService examinationService;

    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;

    @Autowired
    ClinicService clinicService;

    @Test
    public void testFindByPatientId(){
        Long l = new Long(1);
        List<Examination> exams = examinationService.getByPatientId(l);
        assertThat(exams).hasSize(1);
    }

    @Test
    @Transactional
    @Rollback
    public void testSendRequest() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("date","2011-11-02T02:50:12.208Z");
        obj.put("doctor","doctor2");
        String name = "test1";
        Boolean reserved=false;
        try {
             reserved = examinationService.sendRequest(obj,name);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertThat(reserved).isEqualTo(true);
        Examination e = examinationService.findOne((long)1);
        assertThat(e.getPatient().getEmail()).isEqualTo("test1");
        assertThat(e.getDoctor().getEmail()).isEqualTo("doctor2");
    }

    //Failing tests
    @Test(expected = ParseException.class)
    @Transactional
    @Rollback
    public void testSendRequestWithInvalidDate() throws JSONException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("date", "2011-11-02");
        obj.put("doctor", "doctor2");
        String name = "test1";
        Boolean reserved = false;

        reserved = examinationService.sendRequest(obj, name);

        assertThat(reserved).isEqualTo(false);
    }

    @Test()
    @Transactional
    @Rollback
    public void testSendRequestWithinvalidPatiendEmail() throws JSONException,ParseException{
        JSONObject obj = new JSONObject();
        obj.put("date", "2011-11-02T02:50:12.208Z");
        obj.put("doctor", "doctor2");
        String name = "test10";
        Boolean reserved = false;

        reserved = examinationService.sendRequest(obj, name);

        assertThat(reserved).isEqualTo(false);
    }

    @Test()
    @Transactional
    @Rollback
    public void testSendRequestWithinvalidDoctorEmaill() throws JSONException,ParseException{
        JSONObject obj = new JSONObject();
        obj.put("date", "2011-11-02T02:50:12.208Z");
        obj.put("doctor", "doctor34");
        String name = "test1";
        Boolean reserved = false;

        reserved = examinationService.sendRequest(obj, name);

        assertThat(reserved).isEqualTo(false);
    }

    @Test()
    @Transactional
    @Rollback
    public void testSendRequestWithinvalidClinicBeingNull() throws JSONException,ParseException{
        JSONObject obj = new JSONObject();
        obj.put("date", "2011-11-02T02:50:12.208Z");
        obj.put("doctor", "doctor3");
        String name = "test1";
        Boolean reserved = false;

        reserved = examinationService.sendRequest(obj, name);

        assertThat(reserved).isEqualTo(false);
    }

}
