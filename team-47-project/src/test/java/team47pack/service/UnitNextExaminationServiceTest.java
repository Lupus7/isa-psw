package team47pack.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
import team47pack.repository.RoomRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class UnitNextExaminationServiceTest {

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ClinicAdminRepo caRepo;

	@MockBean
	private ClinicRepo clinicRepo;

	@MockBean
	private DoctorRepo doctorRepo;

	@MockBean
	private EmailService emailService;

	@MockBean
	private NextProcedureRepo nextProcedureRepo;

	@MockBean
	private RoomRepo roomRepo;

	@MockBean
	private RoomArrangeRepo roomArrangeRepo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();

	}

	// 3.18 Find Clinic Admin - Success
	@Test
	public void testFindClinicAdminSuccessfull() throws Exception {
		ClinicAdmin ca = new ClinicAdmin("test", "test", "cadmin");
		Mockito.when(caRepo.findById((long) 14))
				.thenReturn(Optional.ofNullable(new ClinicAdmin("test", "test", "cadmin")));
		Optional<ClinicAdmin> cop = caRepo.findById((long) 14);
		assertThat(ca.getEmail()).isEqualTo(cop.get().getEmail());

	}

	// 3.18 Clinic Admin Is Null
	@Test
	public void testFindClinicAdminIsNull() throws Exception {
		Mockito.when(caRepo.findById((long) 250)).thenReturn(Optional.ofNullable(new ClinicAdmin()));
		Optional<ClinicAdmin> cop = caRepo.findById((long) 250);
		assertThat(cop.get().getEmail()).isEqualTo(null);

	}

	// 3.18 Find Next Procedures Clinic doesnt exist
	@Test
	public void testFindNextProcedures() throws Exception {
		Pageable sort = PageRequest.of(0, 10);
		org.springframework.data.domain.Page<NextProcedure> page = null;

		Mockito.when(nextProcedureRepo.findByClinicAndArrangedAndPatientNotNull((long) 250, true, sort))
				.thenReturn(page);

		assertThat(page).isEqualTo(null);

	}

	// 3.18 Find Clinic - Success
	@Test
	public void testFindClinicSuccessfull() throws Exception {
		Clinic clinic = new Clinic();
		clinic.setName("klinika1");
		Mockito.when(clinicRepo.findById((long) 1)).thenReturn(Optional.ofNullable(new Clinic("klinika1")));
		Optional<Clinic> clinicF = clinicRepo.findById((long) 1);
		assertThat(clinicF.get().getName()).isEqualTo(clinic.getName());

	}

	// 3.18 Clinic Is Null Wrong Id
	@Test
	public void testFindClinicIsNull() throws Exception {
		Mockito.when(clinicRepo.findById((long) 250)).thenReturn(Optional.ofNullable(new Clinic()));
		Optional<Clinic> clinicF = clinicRepo.findById((long) 250);
		assertThat(clinicF.get().getName()).isEqualTo(null);

	}

	// 3.18 Find Doctor - Success
	@Test
	public void testFindDoctorSuccessfull() throws Exception {
		Doctor doc = new Doctor("Stomatologist");
		Mockito.when(doctorRepo.findById((long) 12)).thenReturn(Optional.ofNullable(new Doctor("Stomatologist")));
		Optional<Doctor> doktor = doctorRepo.findById((long) 12);
		assertThat(doktor.get().getSpecialization()).isEqualTo(doc.getSpecialization());

	}

	// 3.18 Doctor Is Null Wrong Id
	@Test
	public void testFindDoctorIsNull() throws Exception {
		Mockito.when(doctorRepo.findById((long) 250)).thenReturn(Optional.ofNullable(new Doctor()));
		Optional<Doctor> doctor = doctorRepo.findById((long) 250);
		assertThat(doctor.get().getFirstName()).isEqualTo(null);

	}

	// 3.18 Save NextProcedure - Success
	@Test
	public void testSavenextProcedureSuccessfull() throws Exception {
		NextProcedure np = new NextProcedure();
		np.setIdClinic((long) 1);
		Mockito.when(nextProcedureRepo.save(np)).thenReturn(new NextProcedure());
		NextProcedure proc = nextProcedureRepo.save(np);
		assertThat(proc.getId()).isEqualTo(np.getId());

	}

	// 3.18 Save RoomArrange - Success
	@Test
	public void testSaveroomArrangeSuccessfull() throws Exception {
		RoomArrange ra = new RoomArrange();
		ra.setClinic((long) 1);
		Mockito.when(roomArrangeRepo.save(ra)).thenReturn(new RoomArrange());
		RoomArrange proc = roomArrangeRepo.save(ra);
		assertThat(proc.getId()).isEqualTo(ra.getId());

	}


}
