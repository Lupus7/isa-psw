package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Clinic;
import team47pack.models.Patient;
import team47pack.models.User;
import team47pack.models.dto.CAdminRegReq;
import team47pack.models.dto.ClinicAndAdmin;
import team47pack.models.dto.ClinicRegister;
import team47pack.models.dto.UserInfo;

import java.util.ArrayList;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Service
public class CCAService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ClinicService clinicService;

    public List<UserInfo> getRequests() {
        List<UserInfo> users = new ArrayList<>();
        for(User user : userService.findByAccepted(false)) {
            if (user instanceof Patient)
                users.add(new UserInfo(user));
        }
        return users;
    }

    public boolean acceptRequest(Long id) {
        if(userService.acceptUser(id)) {
            emailService.sendSimpleMessage(
                patientService.getPatientbyID(id.toString()).getUsername(),
                "Registration accepted",
                "Welcome to our clinic!"); // TODO: Refine and add "Complete registration" link
            return true;
        }
        return false;
    }

    public boolean rejectRequest(Long id, String reason) {
        if(userService.rejectUser(id)) {
            emailService.sendSimpleMessage(
                patientService.getPatientbyID(id.toString()).getUsername(),
                "Registration rejected",
                "Reason for rejection: \n" + reason);
            return true;
        }
        return false;
    }

    public UserInfo findByEmail(String email) {
        return new UserInfo(userService.findByEmail(email));
    }

    public void registerClinic(ClinicRegister reg) {
        clinicService.save(new Clinic(reg));
    }

    public boolean registerAdmin(CAdminRegReq req) {
        return loginService.registerClinicAdmin(req);
    }

    public List<ClinicAndAdmin> getClinics() {
        return clinicService.getClinics();
    }
}
