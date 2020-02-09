package team47pack.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class IntegrationClinicServiceTest {

    @Autowired
    ClinicRepo clinicRepo;

    @Autowired
    ClinicAdminRepo clinicAdminRepo;

    // 3.13 Get Clinics - Successful
    @Test
    public void testGetClinics() {
        List<Clinic> clinics = clinicRepo.findAll();
        Assert.assertTrue(clinics.size() == 3);
    }

    // 3.13 Get Clinic - Successful
    @Test
    public void testGetClinic() {
        Optional<Clinic> c = clinicRepo.findById(1L);
        Assert.assertTrue(c.isPresent());
    }

    // 3.13 Get Clinic - Failed
    @Test
    public void testGetClinicFail() {
        Optional<Clinic> c = clinicRepo.findById(11L);
        Assert.assertFalse(c.isPresent());
    }

    // 3.13 Get Clinic Info - Successful
    @Test
    public void testGetClinicInfo() {
        ClinicAdmin ca = clinicAdminRepo.findByEmail("cadmin");
        Assert.assertTrue(ca != null);
    }

    // 3.13 Get Clinic Info - Failed
    @Test
    public void testGetClinicInfoFail() {
        ClinicAdmin ca = clinicAdminRepo.findByEmail("xcadmin");
        Assert.assertTrue(ca == null);
    }
}
