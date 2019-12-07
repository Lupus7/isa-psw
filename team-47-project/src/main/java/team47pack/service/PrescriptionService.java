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
    PrescriptionRepo prescriptionRepp;

    public List<Prescription> findAll() {
        return prescriptionRepp.findAll();
    }
}
