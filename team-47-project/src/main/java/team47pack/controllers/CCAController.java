package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team47pack.models.Patient;
import team47pack.service.PatientService;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class CCAController {
    @Autowired
    private PatientService patientService;

    @GetMapping(value="/request-list")
    public List<Patient> reqList() {
        return patientService.getRegRequest();
    }
}
