package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team47pack.models.ClinicAdmin;
import team47pack.models.Patient;
import team47pack.models.User;
import team47pack.models.dto.CAdminRegReq;
import team47pack.models.dto.RegisterRequest;
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
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        String hash = enc.encode(p.getPassword());
        p.setPassword(hash);
        userRepo.save(p);

        return true;
    }

    // @author: Lupus7 (Sinisa Canak)
    public Boolean registerClinicAdmin(CAdminRegReq req) {
        if(userRepo.findByEmail(req.getEmail()) != null){
            return  false;
        }

        ClinicAdmin ca = new ClinicAdmin(req, req.getClinic());
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        String hash = enc.encode(ca.getPassword());
        ca.setPassword(hash);
        userRepo.save(ca);

        return true;
    }
}
