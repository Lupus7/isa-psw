package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import team47pack.models.Patient;
import team47pack.repository.PatientRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepository;

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public void insert(Patient patient) {
        patientRepository.save(patient);
    }

    public List<Patient> getRegRequest() {
        List<Patient> retVal = new ArrayList<Patient>();
        for (Patient p : patientRepository.findAll()) {
            if (!p.isAccepted()) {
                retVal.add(p);
            }
        }

        return retVal;
    }
}
