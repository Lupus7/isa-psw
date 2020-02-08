package team47pack.service;


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
import team47pack.models.NextProcedure;
import team47pack.models.Patient;
import team47pack.repository.NextProcedureRepo;
import team47pack.repository.PatientRepo;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class UnitFastExaminationServiceTest {
    @Autowired
    private FastExaminationService fastExaminationService;

    @MockBean
    private PatientRepo patientRepo;
    @MockBean
    private NextProcedureRepo nextProcedureRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAppointSuccessfull(){
        NextProcedure n = new NextProcedure();
        n.setType("tipcina");
        Mockito.when(nextProcedureRepo.findById((long)1)).thenReturn(Optional.ofNullable(n));
        Patient p = new Patient();
        p.setEmail("test2");
        Mockito.when(patientRepo.findByEmail("test2")).thenReturn(p);
        Mockito.when(nextProcedureRepo.save(n)).thenReturn(null);
    }

    @Test
    public void testAppointUnsucessfull(){
        NextProcedure n = new NextProcedure();
        n.setType("tipcina");
        Mockito.when(nextProcedureRepo.findById((long)1)).thenReturn(null);
    }

    @Test
    public void testAppointPatientNull(){
        NextProcedure n = new NextProcedure();
        n.setType("tipcina");
        Mockito.when(nextProcedureRepo.findById((long)1)).thenReturn(Optional.ofNullable(n));
        Patient p = new Patient();
        p.setEmail("test2");
        Mockito.when(patientRepo.findByEmail("test2")).thenReturn(null);
    }
}
