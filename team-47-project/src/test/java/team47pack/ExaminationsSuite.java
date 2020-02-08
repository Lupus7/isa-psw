package team47pack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import team47pack.controllers.IntegrationFastExaminationController;
import team47pack.controllers.UnitNextExaminationControllerTest;
import team47pack.controllers.UnitPatientControllerTest;
import team47pack.service.ExaminationServiceTest;
import team47pack.service.FastExaminationServiceTest;
import team47pack.service.UnitExaminationServiceTest;
import team47pack.service.UnitFastExaminationServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExaminationServiceTest.class,
        FastExaminationServiceTest.class,
        UnitPatientControllerTest.class,
        UnitNextExaminationControllerTest.class,
        UnitExaminationServiceTest.class,
        UnitFastExaminationServiceTest.class,
        IntegrationFastExaminationController.class
})
public class ExaminationsSuite {
}
