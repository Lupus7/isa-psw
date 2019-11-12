package team47pack.service;

import org.springframework.stereotype.Service;
import team47pack.models.Patient;
import team47pack.repository.PatientRepo;

import java.util.List;

@Service
public class PatientService {
    private PatientRepo patientRepository;

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public void insert(Patient patient) {
        patientRepository.save(patient);
    }
}
