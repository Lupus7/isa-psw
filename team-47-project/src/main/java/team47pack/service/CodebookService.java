package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Diagnosis;
import team47pack.models.Prescription;
import team47pack.repository.DiagnosisRepo;
import team47pack.repository.PrescriptionRepo;

import java.util.List;

@Service
public class CodebookService {
    @Autowired
    PrescriptionRepo presRepo;

    @Autowired
    DiagnosisRepo diagRepo;

    public List<Prescription> getAllPres() {
        return presRepo.findAll();
    }

    public List<Diagnosis> getAllDiag() {
        return diagRepo.findAll();
    }
}
