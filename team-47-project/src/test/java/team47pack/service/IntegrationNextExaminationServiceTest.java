package team47pack.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.NextProcedure;
import team47pack.models.RoomArrange;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.NextProcedureRepo;
import team47pack.repository.RoomArrangeRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class IntegrationNextExaminationServiceTest {

	@Autowired
	private ClinicAdminRepo caRepo;

	@Autowired
	private ClinicRepo clinicRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private NextProcedureRepo nextProcedureRepo;

	@Autowired
	private RoomArrangeRepo roomArrangeRepo;

	/*
	 * @Test public void testGetNextExamiantions() { List<NextProcedure> list =
	 * nextExaminationService.getNextExns("cadmin"); // check if in the database now
	 * Assert.assertTrue(list.size() > 0); }
	 */

	@Test
	public void testFindClinicAdminSuccessfull() throws Exception {
		Optional<ClinicAdmin> ca = caRepo.findById((long) 14);
		Assert.assertTrue(ca.isPresent());

	}

	// 3.18 Clinic Admin Is Null
	@Test
	public void testFindClinicAdminIsNull() throws Exception {
		Optional<ClinicAdmin> ca = caRepo.findById((long) 140);
		Assert.assertNull(ca.orElse(null));
	}

	// 3.18 Find Next Procedures Clinic doesnt exist
	@Test
	public void testFindNextProcedures() throws Exception {
		Pageable sort = PageRequest.of(0, 10);

		@SuppressWarnings("unchecked")
		List<NextProcedure> npx = nextProcedureRepo.findByClinicAndArrangedAndPatientNotNull((long) 250, true, sort)
				.getContent();

		Assert.assertTrue(npx.size() == 0);

	}

	// 3.18 Find Clinic - Success
	@Test
	public void testFindClinicSuccessfull() throws Exception {
		Optional<Clinic> clinic = clinicRepo.findById((long) 1);
		Assert.assertTrue(clinic.isPresent());

	}

	// 3.18 Clinic Is Null Wrong Id
	@Test
	public void testFindClinicIsNull() throws Exception {
		Optional<Clinic> clinic = clinicRepo.findById((long) 250);
		Assert.assertNull(clinic.orElse(null));

	}

	// 3.18 Find Doctor - Success
	@Test
	public void testFindDoctorSuccessfull() throws Exception {
		Optional<Doctor> doktor = doctorRepo.findById((long) 12);
		Assert.assertTrue(doktor.isPresent());

	}

	// 3.18 Doctor Is Null Wrong Id
	@Test
	public void testFindDoctorIsNull() throws Exception {
		Optional<Doctor> doktor = doctorRepo.findById((long) 120);
		Assert.assertNull(doktor.orElse(null));

	}

	// 3.18 Save NextProcedure - Success
	@Test
	public void testSavenextProcedureSuccessfull() throws Exception {
		NextProcedure np = new NextProcedure();
		np.setIdClinic((long) 4);
		NextProcedure proc = nextProcedureRepo.save(np);
		Assert.assertNotNull(proc);

	}

	// 3.18 Save RoomArrange - Success
	@Test
	public void testSaveroomArrangeSuccessfull() throws Exception {
		RoomArrange ra = new RoomArrange();
		ra.setClinic((long) 3);
		RoomArrange proc = roomArrangeRepo.save(ra);
		Assert.assertNotNull(proc);

	}

}
