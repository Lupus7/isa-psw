package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Diagnosis;
import team47pack.repository.DiagnosisRepo;

import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Service
public class DiagnosisService {

    @Autowired
    DiagnosisRepo diagnosisRepo;

    public List<Diagnosis> findAll() {
        return diagnosisRepo.findAll();
    }
}
