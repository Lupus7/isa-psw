package team47pack.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import team47pack.models.NextProcedure;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

//import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class FastExaminationServiceTest {

    @Autowired
    FastExaminationService fastExaminationService;

    //Successfull test
    @Test
    @Transactional
    @Rollback
    public void testAppointSuccessfull(){
        fastExaminationService.appoint((long)2,"test1");
        //check if in the database now
        NextProcedure next = fastExaminationService.getOne((long)2);
        Assert.assertNotNull(next);
    }

    @Test(expected = NoSuchElementException.class)
    @Transactional
    @Rollback
    public void testAppointNextProcedureNotPresent() throws  EntityNotFoundException{
        fastExaminationService.appoint((long)7,"test1");
        //check if in the database now
        NextProcedure next = fastExaminationService.getOne((long)7);
        Assert.assertNull(next);
    }

    @Test(expected = NoSuchElementException.class)
    @Transactional
    @Rollback
    public void testAppointNextPatientDoesntExit() throws  EntityNotFoundException{
        fastExaminationService.appoint((long)5,"test14");
        //check if in the database now
        NextProcedure next = fastExaminationService.getOne((long)5);
        Assert.assertNull(next);
    }


}
