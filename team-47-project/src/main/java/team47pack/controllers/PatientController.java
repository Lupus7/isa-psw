package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.*;
import team47pack.models.dto.DiagnosisDTO;
import team47pack.models.dto.FilterPatientRequest;
import team47pack.models.dto.MedicalFileDto;
import team47pack.models.dto.SearchPatientRequest;
import team47pack.security.TokenUtils;
import team47pack.service.DiagnosisService;
import team47pack.service.PatientService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private DiagnosisService diagnosisService;

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

	// @author:Jokara-------------------------------------------------------------------------------------------
	@GetMapping(value = "patient/{id}")
	@PreAuthorize("hasRole('NURSE') or hasRole('DOCTOR') or hasRole('CADMIN') or hasRole('CCADMIN') ")
	public Patient getPatient(@PathVariable(value = "id") String id) {

		return patientService.getPatientbyID(id);
	}
}
