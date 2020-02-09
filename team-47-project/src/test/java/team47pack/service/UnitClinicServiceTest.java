package team47pack.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import team47pack.models.Clinic;
import team47pack.models.NextProcedure;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class UnitClinicServiceTest {

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    ClinicRepo clinicRepo;

    @MockBean
    ClinicAdminRepo caRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();

    }

    // 3.13 Find Clinic by Id - Success
    @Test
    public void testFindClinicAdminSuccessfull() throws Exception {
        Clinic c = new Clinic("klinika1");
        Mockito.when(clinicRepo.findById((long) 1)).thenReturn(Optional.ofNullable(c));
        Optional<Clinic> cop = clinicRepo.findById((long) 1);
        assertThat(cop.get().getName()).isEqualTo(c.getName());

    }

    // 3.13 Find All Clinics Success
    @Test
    public void testFindClinicsSuccessfull() throws Exception {
        Clinic c = new Clinic("klinika1");
        Clinic c1 = new Clinic("klinika2");
        List<Clinic> clinics = new ArrayList<Clinic>();
        clinics.add(c);
        clinics.add(c1);
        Mockito.when(clinicRepo.findAll()).thenReturn(clinics);
        List<Clinic> cop = clinicRepo.findAll();
        assertThat(cop.size()).isEqualTo(clinics.size());

    }

    // 3.13 Find Clinic by Id, Wrong Id
    @Test
    public void testFindClinicAdminIsNull() throws Exception {
        Mockito.when(clinicRepo.findById((long) 250)).thenReturn(Optional.ofNullable(new Clinic()));
        Optional<Clinic> cop = clinicRepo.findById((long) 250);
        assertThat(cop.get().getName()).isEqualTo(null);
    }

    // 3.13 Find All Search Clinics Success
    @Test
    public void testFindClinicsSearchSuccessfull() throws Exception {
        Specification<Clinic> spec = Specification.where(ClinicSpecification.clinicLocation("dr.andre"));

        Clinic c = new Clinic("klinika1");
        ArrayList<Clinic> cls = new ArrayList<>();
        cls.add(c);
        Page<Clinic> clinics = new PageImpl<>(cls);
        Mockito.when(clinicRepo.findAll(spec, PageRequest.of(0, 10, Sort.by("address")))).thenReturn(clinics);
        Page<Clinic> cop = clinicRepo.findAll(spec, PageRequest.of(0, 10, Sort.by("address")));
        assertThat(cop.getContent().size()).isEqualTo(clinics.getContent().size());

    }

    // 3.13 Save Clinic - Success
    @Test
    public void testSaveClinicSuccessfull() throws Exception {
        Clinic clinic = new Clinic();
        clinic.setName("klinika44");
        Mockito.when(clinicRepo.save(clinic)).thenReturn(clinic);
        Clinic proc = clinicRepo.save(clinic);
        assertThat(proc.getName()).isEqualTo(clinic.getName());

    }

}
