package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Diagnosis;
import team47pack.models.Prescription;
import team47pack.models.dto.DiagnosisDTO;
import team47pack.models.dto.PrescriptionDTO;

import java.util.ArrayList;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Service
public class CodebookService {

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    DiagnosisService diagnosisService;

    public List<PrescriptionDTO> getAllPres() {
        List<PrescriptionDTO> prescriptions = new ArrayList<>();
        for (Prescription p : prescriptionService.findAll()) {
            prescriptions.add(new PrescriptionDTO(p));
        }
        return prescriptions;
    }

    public List<DiagnosisDTO> getAllDiag() {
        List<DiagnosisDTO> diagnosis = new ArrayList<>();
        for (Diagnosis d : diagnosisService.findAll()) {
            diagnosis.add(new DiagnosisDTO(d));
        }
        return diagnosis;
    }
}
