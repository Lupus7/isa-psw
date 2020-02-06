package team47pack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import team47pack.service.ExaminationServiceTest;
import team47pack.service.FastExaminationServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExaminationServiceTest.class,
        FastExaminationServiceTest.class
})
public class ExaminationsSuite {
}
