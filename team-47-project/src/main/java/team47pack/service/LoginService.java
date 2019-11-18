package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.Patient;
import team47pack.models.User;
import team47pack.models.dto.RegisterRequest;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.PatientRepo;
import team47pack.repository.UserRepo;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PatientRepo patientRepo;

    public User login(String email, String password){
        System.out.println("Email:" + email+"   pass: "+password);
        return userRepo.findByEmail(email);
    }

    public Boolean register(RegisterRequest req) {
        User u = userRepo.findByEmail(req.getEmail());
        if(u != null){
            return  false;
        }

        Patient p = new Patient(req);
        userRepo.save(p);

        return true;
    }
}
