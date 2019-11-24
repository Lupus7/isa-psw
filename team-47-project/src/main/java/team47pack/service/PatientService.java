package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Clinic;
import team47pack.models.Patient;
import team47pack.repository.ClinicRepo;
import team47pack.repository.PatientRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepository;
    @Autowired
    private ClinicRepo clinicRepository;


    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    public List<Clinic> getAllClinics(){return clinicRepository.findAll();}

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

    public Patient getPatient(String email){
        System.out.println(email);
        Patient ret = new Patient();
        for(Patient p: patientRepository.findAll()){
            if(p.getEmail().equals(email)){
                ret = p;
                break;
            }
        }
        System.out.println(ret.getUsername() + ret.getEmail());
        return ret;
    }

}
