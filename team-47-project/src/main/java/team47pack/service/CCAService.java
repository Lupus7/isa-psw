package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import team47pack.models.Clinic;
import team47pack.models.ClinicCentreAdmin;
import team47pack.models.User;
import team47pack.models.dto.ClinicRegister;
import team47pack.repository.CCARepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.UserRepo;


import java.util.List;

@Service
public class CCAService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CCARepo ccaRepo;

    @Autowired
    private ClinicRepo clinicRepo;

    public List<User> getRegRequest() {
        return userRepo.findByAccepted(false);
    }

    public boolean acceptReq(String mail) {
        if(userRepo.acceptUser(mail) != 0)
            return true;
        return false;
    }

    public boolean rejectReq(String mail) {
        if(userRepo.acceptUser(mail) != 0)
            return true;
        return false;
    }

    public ClinicCentreAdmin findByEmail(String email) {
        return (ClinicCentreAdmin) userRepo.findByEmail(email);
    }

    public void registerClinic(ClinicRegister reg) {
        clinicRepo.save(new Clinic(reg));
    }
}
