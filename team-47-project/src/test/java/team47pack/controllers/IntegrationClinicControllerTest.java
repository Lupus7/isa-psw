package team47pack.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import team47pack.models.Clinic;
import team47pack.models.Doctor;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.models.dto.RateRequest;
import team47pack.service.ClinicService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class IntegrationClinicControllerTest {

    @Autowired
    private ClinicService clinicService;

    // 3.13 Search For Clinics - Success
    @Test
    @Transactional
    public void testSearchForClinics() {
        ClinicSearchRequest csr = new ClinicSearchRequest("Novi Sad", "", null, 6.0);
        ArrayList<ClinicSearchResult> rez = clinicService.search(csr);
        Assert.assertTrue(rez.size() == 1);
    }

    // 3.13 Search For Clinics - Failed
    @Test
    @Transactional
    public void testSearchForClinicsFail() {
        ClinicSearchRequest csr = new ClinicSearchRequest("xx", "", null, 9.0);
        ArrayList<ClinicSearchResult> rez = clinicService.search(csr);
        Assert.assertTrue(rez.size() == 0);
    }

    // 3.13 Get Doctors - Success
    @Test
    @Transactional
    public void testGetDoctors() {
        Clinic clinic = clinicService.getClinic(1L);
        List<Doctor> dd = clinic.getDoctors();
        Assert.assertTrue(dd.size() == 1);
    }

    // 3.13 Get Doctors - Failed
    @Test
    @Transactional
    public void testGetDoctorsFail() {
        Clinic clinic = clinicService.getClinic(111L);
        Assert.assertTrue(clinic == null);
    }

    // 3.13 Leave Rate - Success
    @Test
    @Transactional
    public void testLeaveRate() {
        boolean b = clinicService.leaveRate(new RateRequest(5, 12L, 1L));
        Assert.assertTrue(b);
    }

    // 3.13 Leave Rate - Failed
    @Test
    @Transactional
    public void testLeaveRateFail() {
        boolean b = clinicService.leaveRate(new RateRequest(5, 1L, 1L));
        Assert.assertFalse(b);
    }

    // 3.13 Get Clinic Info - Success
    @Test
    @Transactional
    public void testGetClinicInfo() {
        Clinic clinic = clinicService.getClinicInfo("cadmin");
        Assert.assertTrue(clinic != null);
    }

    // 3.13 Get Clinic Info - Failed
    @Test
    @Transactional
    public void testGetClinicInfoFail() {
        Clinic clinic = clinicService.getClinicInfo("xcadmin");
        Assert.assertTrue(clinic == null);
    }
}
