package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Patient;
import team47pack.service.PatientService;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
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
}
