package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team47pack.models.dto.DiagnosisDTO;
import team47pack.models.dto.PrescriptionDTO;
import team47pack.service.CodebookService;

import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@RestController
@RequestMapping(value="/codebook")
public class CodebookController {
    @Autowired
    CodebookService cbService;

    @GetMapping(value = "/diagnosis")
    @PreAuthorize("hasRole('CCADMIN') or hasRole('DOCTOR')")
    public List<DiagnosisDTO> getAllDiagnosis(){
        return cbService.getAllDiag();
    }

    @GetMapping(value = "/prescription")
    @PreAuthorize("hasRole('CCADMIN') or hasRole('DOCTOR')")
    public List<PrescriptionDTO> getAllPrescriptions(){
        return cbService.getAllPres();
    }
}
