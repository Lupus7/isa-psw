package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Patient;
import team47pack.security.TokenUtils;
import team47pack.service.PatientService;

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

    @GetMapping(value="/patient/getInfo")
    public Patient getInfo(@RequestHeader(name="Authorization") String token){
        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        String role = tokenUtils.getRole(token);
        System.out.println(email + " " +role);
        Patient patient= null;

        if(!role.equals("ROLE_PATIENT") || email == null )
            return null;

        patient = patientService.getPatient(email);

        return patient;
    }
}
