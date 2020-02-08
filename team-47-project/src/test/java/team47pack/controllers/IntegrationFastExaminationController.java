package team47pack.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import team47pack.models.NextProcedure;
import team47pack.service.FastExaminationService;

import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class IntegrationFastExaminationController {

    @Autowired
    private FastExaminationService fastExaminationService;

    //3.12 - fast appointing examinations

    @Test
    public void testAppointSuccessfull() {
        fastExaminationService.appoint((long)3,"test1");
        // check if in the database now
        NextProcedure n = fastExaminationService.getOne((long)3);
        System.out.println(n.getPatient().getUsername());
        Assert.assertNotNull(n.getPatient());
    }

    //unsuccessfull test
    @Test(expected = NoSuchElementException.class)
    public void testAppointNotValidExaminationID() {
        fastExaminationService.appoint((long)4,"test1");
        // check if in the database now
        Assert.assertNull(fastExaminationService.getOne((long)4));
    }

    @Test()
    public void testAppointNotValidEmail() {
        fastExaminationService.appoint((long)2,"test112");
        // check if in the database now
        Assert.assertNull(fastExaminationService.getOne((long)2).getPatient());
    }

}