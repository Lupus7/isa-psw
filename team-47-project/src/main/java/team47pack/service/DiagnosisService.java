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

    public List<Diagnosis> findAllByOrderByIdAsc() {
        return diagnosisRepo.findAllByOrderByIdAsc();
    }

    public Diagnosis findOneById(Long id){ return diagnosisRepo.getOne(id); }

    public void save(Diagnosis diag) {
        diagnosisRepo.save(diag);
    }

    public void delete(Long id) { diagnosisRepo.deleteById(id);}
}
