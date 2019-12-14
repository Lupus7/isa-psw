package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.dto.DiagnosisDTO;
import team47pack.models.dto.PrescriptionDTO;
import team47pack.service.CodebookService;

import javax.validation.Valid;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@RestController
@RequestMapping(value = "/codebook")
public class CodebookController {
    @Autowired
    CodebookService cbService;

    @GetMapping(value = "/diagnosis")
    @PreAuthorize("hasRole('CCADMIN') or hasRole('DOCTOR')")
    public List<DiagnosisDTO> getAllDiagnosis() {
        return cbService.getAllDiag();
    }

    @GetMapping(value = "/prescription")
    @PreAuthorize("hasRole('CCADMIN') or hasRole('DOCTOR')")
    public List<PrescriptionDTO> getAllPrescriptions() {
        return cbService.getAllPres();
    }

    @PostMapping(value = "/diagnosis")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<String> saveDiagnosis(@Valid @RequestBody DiagnosisDTO diagnosisDTO) {
        cbService.saveDiag(diagnosisDTO);
        return ResponseEntity.ok("Successful");
    }

    @PostMapping(value = "/prescription")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<String> savePrescriptions(@Valid @RequestBody PrescriptionDTO prescriptionDTO) {
        cbService.savePres(prescriptionDTO);
        return ResponseEntity.ok("Successful");
    }

    @DeleteMapping(value = "/diagnosis/{id}")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<String> deleteDiagnosis(@PathVariable(value = "id") Long id) {
        try {
            cbService.deleteDiag(id);
            return ResponseEntity.ok("Successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Diagnosis in use!");
        }
    }

    @DeleteMapping(value = "/prescription/{id}")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<String> deletePrescriptions(@PathVariable(value = "id") Long id) {
        try {
            cbService.deletePres(id);
            return ResponseEntity.ok("Successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Prescription in use!");
        }
    }
}
