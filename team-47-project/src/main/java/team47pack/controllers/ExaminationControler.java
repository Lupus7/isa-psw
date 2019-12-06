package team47pack.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Examination;
import team47pack.models.Patient;
import team47pack.models.dto.ExaminInfo;
import team47pack.service.ExaminationService;
import team47pack.service.PatientService;

@RestController
public class ExaminationControler {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private PatientService patientService;

    @GetMapping(value="/patient/getAllExaminations")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Examination> getAllExams(Principal user){
        Patient patient = patientService.getPatient(user.getName());
        return examinationService.getByPatientId(patient.getId());
    }
    @GetMapping(value="/patient/getAll")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Examination> getAll(Principal user){
        Patient patient = patientService.getPatient(user.getName());
        List<Examination> ret = examinationService.getAll();
        for(Examination e:ret){
            System.out.println("Nazad u servisu");
            System.out.println(e.getId() +" " +e.getDate() +" " +e.getPatient().getEmail());
        }
        return ret;
    }

    @PostMapping(value="/patient/examination")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> postExamination(@RequestBody ExaminInfo examinInfo, Principal p) throws ParseException {
        return (examinationService.addExamination(examinInfo,p.getName())) ? ResponseEntity.ok("Successful") : ResponseEntity.status(400).body("Invalid information");
    }
}
