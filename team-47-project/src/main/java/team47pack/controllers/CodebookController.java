package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Diagnosis;
import team47pack.models.Prescription;
import team47pack.service.CodebookService;

import java.util.List;

@RestController
@RequestMapping(value="/codebook")
public class CodebookController {
    @Autowired
    CodebookService cbService;

    @GetMapping(value = "/diagnosis")
    @PreAuthorize("hasRole('CCADMIN') or hasRole('DOCTOR')")
    public List<Diagnosis> getAllDiagnosis(){
        return cbService.getAllDiag();
    }

    @GetMapping(value = "/prescription")
    @PreAuthorize("hasRole('CCADMIN') or hasRole('DOCTOR')")
    public List<Prescription> getAllPrescriptions(){
        return cbService.getAllPres();
    }
}
