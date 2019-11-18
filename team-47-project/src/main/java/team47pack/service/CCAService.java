package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team47pack.models.Patient;
import team47pack.repository.PatientRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class CCAService {
    @Autowired
    private PatientRepo patientRepository;

    public List<Patient> getRegRequest() {
        List<Patient> retVal = new ArrayList<Patient>();
        for (Patient p : patientRepository.findAll()) {
            if (!p.getIsAccepted()) {
                retVal.add(p);
            }
        }

        return retVal;
    }

    public boolean acceptReq(String mail) {
        if(patientRepository.acceptUser(mail) != 0)
            return true;
        return false;
    }

    public boolean rejectReq(String mail) {
        if(patientRepository.rejectUser(mail) != 0)
            return true;
        return false;
    }
}
