package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Clinic;
import team47pack.models.MedFileEntry;
import team47pack.models.MedicalFile;
import team47pack.models.Patient;
import team47pack.models.dto.FilterPatientRequest;
import team47pack.models.dto.MedicalFileDto;
import team47pack.models.dto.SearchPatientRequest;
import team47pack.security.TokenUtils;
import team47pack.service.MedicalFileService;
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
	private MedicalFileService medicalFileService;

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

	@GetMapping(value = "/patient/getAllFiles")
	@PreAuthorize("hasRole('PATIENT')")
	public MedicalFileDto getPatientMF(Principal user) {
		Patient patient = patientService.getPatient(user.getName());
		MedicalFile mdf = patient.getMedicalFile();
		System.out.println(mdf.getId());
		List<MedFileEntry> entries = mdf.getEntries();
		MedFileEntry mdentry = entries.get(0);
		System.out.println(mdentry.getPrescriptions());

		return null;
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
