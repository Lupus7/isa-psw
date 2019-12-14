package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Prescription;
import team47pack.repository.PrescriptionRepo;

import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepo prescriptionRepo;

    public List<Prescription> findAllByOrderByIdAsc() {
        return prescriptionRepo.findAllByOrderByIdAsc();
    }

    public void save(Prescription pres) {
        prescriptionRepo.save(pres);
    }

    public Prescription findOneById(Long id) {
        return prescriptionRepo.findOneById(id);
    }

    public void delete(Long id) { prescriptionRepo.deleteById(id); }
}
