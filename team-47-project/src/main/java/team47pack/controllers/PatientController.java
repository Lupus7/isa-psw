package team47pack.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Clinic;
import team47pack.models.Patient;
import team47pack.models.dto.FilterPatientRequest;
import team47pack.models.dto.MedicalFileDto;
import team47pack.models.dto.SearchPatientRequest;
import team47pack.security.TokenUtils;
import team47pack.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private TokenUtils tokenUtils;

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
	public MedicalFileDto getPatientMF() {
		List<String> bolesti = new ArrayList<String>();
		bolesti.add("Upala krajnika");
		bolesti.add("Prehlada");
		bolesti.add("Temperatura, grip");
		List<String> opisBolest = new ArrayList<>();
		opisBolest.add("Antibiotici, il operacija jbg");
		opisBolest.add("Fervex i kapi za nos");
		opisBolest.add("Mirovanje, cajevi, krompir u carapu");
		MedicalFileDto mdto = new MedicalFileDto(bolesti, opisBolest);
		return mdto;

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
