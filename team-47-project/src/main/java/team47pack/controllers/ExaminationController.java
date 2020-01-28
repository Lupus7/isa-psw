package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team47pack.models.Examination;
import team47pack.models.NextProcedure;
import team47pack.models.Patient;
import team47pack.models.dto.ExaminInfo;
import team47pack.models.dto.ExaminationDTO;
import team47pack.service.ExaminationService;
import team47pack.service.PatientService;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private PatientService patientService;

    @GetMapping(value="/patient/getAllExaminations")
    @PreAuthorize("hasRole('PATIENT')")
    public List<ExaminationDTO> getAllExams(Principal user){
        List<ExaminationDTO> ret = new ArrayList<>();
        Patient patient = patientService.getPatient(user.getName());
        List<Examination> list =  examinationService.getByPatientId(patient.getId());
        for(Examination e: list){
            ret.add(new ExaminationDTO(e));
        }
        return ret;
    }
    @GetMapping(value="/patient/getAll")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Examination> getAll(Principal user){
        Patient patient = patientService.getPatient(user.getName());
        return examinationService.getAll();
 
    }

    // @author: Lupus7 (Sinisa Canak)
    @PostMapping(value="/patient/examination")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> postExamination(@RequestBody ExaminInfo examinInfo, Principal p) throws ParseException {
        return (examinationService.addExamination(examinInfo,p.getName())) ?
                ResponseEntity.ok("Successful") :
                ResponseEntity.status(400).body("Invalid information");
    }
    
    
}
