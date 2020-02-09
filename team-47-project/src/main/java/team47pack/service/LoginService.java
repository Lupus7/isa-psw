package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.CAdminRegReq;
import team47pack.models.dto.RegisterRequest;
import team47pack.repository.AuthorityRepository;
import team47pack.repository.PatientRepo;
import team47pack.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private ClinicAdminService clinicAdminService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private ClinicService clinicService;

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
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityRepository.findByName("ROLE_PATIENT"));
        authorities.add(authorityRepository.findByName("ROLE_USER"));

        p.setAuthorities(authorities);

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
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityRepository.findByName("ROLE_CADMIN"));
        authorities.add(authorityRepository.findByName("ROLE_USER"));
        ca.setAuthorities(authorities);
        clinicAdminService.save(ca);
        Clinic c = clinicService.getClinic((long) req.getClinic());
        c.getClinicAdmins().add(ca);
        clinicService.save(c);

        return true;
    }
}
