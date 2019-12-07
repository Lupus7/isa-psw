package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Diagnosis;

import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Service
public class DiagnosisService {

    @Autowired
    DiagnosisService diagnosisService;

    public List<Diagnosis> findAll() {
        return diagnosisService.findAll();
    }
}
