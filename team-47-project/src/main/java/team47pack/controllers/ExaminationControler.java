package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team47pack.models.Examination;
import team47pack.models.Patient;
import team47pack.service.ExaminationService;
import team47pack.service.PatientService;

import java.security.Principal;
import java.util.List;

@RestController
public class ExaminationControler {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private PatientService patientService;

    @GetMapping(value="/patient/getAllExaminations")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Examination> getAllExams(Principal user){
        System.out.println("11111>>>>>>>>>" + user.getName()+" <<<<<<<<<<");
        Patient patient = patientService.getPatient(user.getName());
        System.out.println("22222>>>>>>>>>" + patient.getEmail()+" "+patient.getId()+" <<<<<<<<<<");
        return examinationService.getByPatientId(patient.getId());
    }
    @GetMapping(value="/patient/getAll")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Examination> getAll(Principal user){
        System.out.println("11111>>>>>>>>>" + user.getName()+" <<<<<<<<<<");
        Patient patient = patientService.getPatient(user.getName());
        System.out.println("22222>>>>>>>>>" + patient.getEmail()+" "+patient.getId()+" <<<<<<<<<<");
        List<Examination> ret = examinationService.getAll();
        for(Examination e:ret){
            System.out.println("Nazad u servisu");
            System.out.println(e.getId() +" " +e.getDate() +" " +e.getPatient().getEmail());
        }
        return ret;
    }
}
