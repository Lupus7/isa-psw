package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.Patient;
import team47pack.models.User;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.PatientRepo;

@Service
public class LoginService {
    @Autowired
    private PatientRepo patientrepo;
    @Autowired
    private DoctorRepo doctorepo;
    @Autowired
    ClinicAdminRepo clinicadminrepo;

    public User login(String email, String password){
        System.out.println("Email:" + email+"   pass: "+password);
       Patient patient = patientrepo.findByEmail(email);
       if (patient != null){
           System.out.println("Patient: " + patient);
           return patient;
       }
       Doctor doctor = doctorepo.findByEmail(email);
       if (doctor != null){
           return doctor;
       }
        ClinicAdmin clinicadmin=clinicadminrepo.findByEmail(email);
       if(clinicadmin != null){
           return clinicadmin;
       }
       return null;
    }
}
