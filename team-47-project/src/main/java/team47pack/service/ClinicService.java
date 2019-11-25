package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Clinic;
import team47pack.repository.ClinicRepo;

import java.util.List;

@Service
public class ClinicService {
    @Autowired
    ClinicRepo clinicRepo;

    public List<Clinic> getClinics() {
        return clinicRepo.findAll();
    }
}
