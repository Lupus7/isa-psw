package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team47pack.models.Patient;
import team47pack.security.TokenUtils;
import team47pack.service.PatientService;

import java.security.Principal;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping(value="/patients")
    public List<Patient> posts(){
        return patientService.getAllPatients();
    }
    @PostMapping(value="/patient")
    public void publishPatient(@RequestBody Patient patient){
        patientService.insert(patient);
    }

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value = "/patient/getInfo")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Patient> getInfo(Principal user) {
        return ResponseEntity.ok(this.patientService.getPatient(user.getName()));
    }
}
