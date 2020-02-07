package team47pack.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.*;
import team47pack.models.dto.*;
import team47pack.security.TokenUtils;
import team47pack.service.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private ExaminationService examinationService;
	@Autowired
	private DiagnosisService diagnosisService;

	@Autowired
	private ExaminationTypeService examinationTypeService;

	@Autowired
	private ClinicService clinicService;


	@GetMapping(value = "/patients")
	public List<Patient> posts() {
		return patientService.getAllPatients();
	}

	@PostMapping(value = "/patient")
	public void publishPatient(@RequestBody Patient patient) {
		patientService.insert(patient);
	}

	@GetMapping(value = "/patient/getInfo")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<Patient> getInfo(Principal user) {
		return ResponseEntity.ok(this.patientService.getPatient(user.getName()));
	}

	@GetMapping(value = "/patient/getAllClinics")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Clinic> getClinics() {
		return patientService.getAllClinics();
	}

	@PostMapping(value = "/patient/updateInfo", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> changeData(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);

		if (patientService.updateData(obj, user.getName()))
			return ResponseEntity.ok("Successfulty updated data!");
		else
			return ResponseEntity.status(400).body("Could not accept");
	}

	@GetMapping(value = "/patient/getMedicalFile")
	@PreAuthorize("hasRole('PATIENT')")
	public MedicalFileDto getPatientMedicalFile(Principal user){
		System.out.println("Usao :" + user.getName() + user.toString());
		Patient patient = patientService.getPatient(user.getName());
		System.out.println("+++++++++" + patient.getUsername());
		ArrayList<DiagnosisDTO> dijagnoze = new ArrayList<>();
		ArrayList<String> opisi = new ArrayList<>();
		//sada izvlacimo MedicalFile iz pacijenta
		MedicalFile mf = patient.getMedicalFile();
		System.out.println("Medical file ID: " + mf.getId());
		List<MedFileEntry> entrijevi = mf.getEntries();
		for(MedFileEntry m: entrijevi){
			System.out.println("OPIS ENTRIJA: " + m.getDesc());
			opisi.add(m.getDesc());
			Diagnosis d = diagnosisService.findOneById(m.getDiagnosis().getId());
			System.out.println("Nasao dijagnozu: " + d.getName()+d.getDesc());
			dijagnoze.add(new DiagnosisDTO(d));
		}
		MedicalFileDto mfd = new MedicalFileDto();
		mfd.setDijagnoze(dijagnoze);
		mfd.setOpisi(opisi);
		System.out.println(mfd.toString());


		return mfd;
	}

	// @author:Jokara-------------------------------------------------------------------------------------------
	@PostMapping(value = "/patient/searchPatients", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('NURSE') or hasRole('DOCTOR')")
	public ArrayList<Patient> searchPatients(@RequestBody SearchPatientRequest req) {

		return patientService.searchPatients(req);
	}
	
	// @author:Jokara-------------------------------------------------------------------------------------------
	@PostMapping(value = "/patient/filterPatients", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('NURSE') or hasRole('DOCTOR')")
	public ArrayList<Patient> filterPatients(@RequestBody FilterPatientRequest req) {

		return patientService.filterPatients(req);
	}
	
	@PostMapping(value = "patient/sort/{type}")
	@PreAuthorize("hasRole('NURSE') or hasRole('DOCTOR') or hasRole('CADMIN') or hasRole('CCADMIN') ")
	public List<Patient> getSortPatients(@PathVariable(value = "type") String typ) {
		return patientService.getSortedPatients(typ);
	}

	// @author:Jokara-------------------------------------------------------------------------------------------
	@GetMapping(value = "patient/{id}")
	@PreAuthorize("hasRole('NURSE') or hasRole('DOCTOR') or hasRole('CADMIN') or hasRole('CCADMIN') ")
	public Patient getPatient(@PathVariable(value = "id") String id) {

		return patientService.getPatientbyID(id);
	}
	
	@GetMapping(value = "patient/getMedFile/{id}")
	@PreAuthorize("hasRole('NURSE') or hasRole('DOCTOR') ")
	public List<MedicalFileViewDTO> getMedFile(@PathVariable(value = "id") String id, Principal user) {

		return patientService.getMedFile(id,user.getName());
	}

	@PostMapping(value="patient/requests/{id}/{spec}")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<String> sendRequest(@PathVariable(value = "id") Long id, @PathVariable(value = "spec")String spec,Principal user) {
		System.out.println(id + spec);

		Patient pat = patientService.getPatient(user.getName());
		Doctor doc = doctorService.getDoctorByID(id.toString());
		Clinic clin = clinicService.getClinicByDoktorID(id);
		System.out.println("Klinika u kojoj radi doktor je : " + clin.toString());
		List<ExaminationType> ex = examinationTypeService.findByClinicID(clin.getId());
		ExaminationType temp = new ExaminationType();
		for(ExaminationType e: ex){
			if(e.getSpecialization().equals(spec) && !e.getName().equals("Control")){
				temp = e;
				System.out.println("This is examination type: " + e.getName() + e.getSpecialization());
				break;
			}
		}
		Examination examination = new Examination(spec,new Date(),pat,doc,false,temp);
		examinationService.save(examination);
		return ResponseEntity.ok().body("Successfully send procedure request appointment");
	}

	@PostMapping(value="/patient/requestExam", produces="application/json",consumes = "application/json")
	public Boolean sendRequest(@RequestBody String json, Principal user) throws JSONException, ParseException {
		JSONObject obj = new JSONObject(json);
		System.out.println("JSON : " + json + " USER : " + user.getName());
		return examinationService.sendRequest(obj, user.getName());
	}
}
