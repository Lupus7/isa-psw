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
            Patient p=patientService.getPatientbyID(id.toString());
            String message="Dear "+ p.getFirstName()+ " "+p.getLastName()+",\n";
            message+="Your registration has been accepted. Thank you for using our services!";
            message+="\nSincerely,\nAdmin team";

            try {
                emailService.sendSimpleMessage(
                        p.getUsername(),
                        "Registration accepted",
                        message);
            } catch (Exception e) {}
            return true;
        }
        return false;
    }

    public boolean rejectRequest(Long id, String reason) {
        Patient p=patientService.getPatientbyID(id);
        if(p==null)
            return false;

        String message="Dear "+ p.getFirstName()+ " "+p.getLastName()+",\n";
        message+="We are sorry to inform you that your registration request has been rejected.";
        message+="Reason for rejection is:\n"+reason;
        message+="\nSincerely,\nAdmin team";

        try {
            emailService.sendSimpleMessage(
                    p.getUsername(),
                    "Registration rejected",
                    message);
        } catch (Exception e) {}

        return userService.rejectUser(id);

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

    public boolean save(UserInfo userInfo) {
        return userService.save(userInfo);
    }
}
