package team47pack.controllers;

import java.security.Principal;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import team47pack.models.Clinic;
import team47pack.models.ClinicCentreAdmin;
import team47pack.models.User;
import team47pack.models.dto.CAdminRegReq;
import team47pack.models.dto.ClinicAndAdmin;
import team47pack.models.dto.ClinicRegister;
import team47pack.models.dto.RegisterRequest;
import team47pack.security.TokenUtils;
import team47pack.service.CCAService;
import team47pack.service.ClinicService;
import team47pack.service.EmailService;
import team47pack.service.LoginService;

@RestController
@RequestMapping(value="/cca")
@PreAuthorize("hasRole('CCADMIN')")
public class CCAController {
    @Autowired
    private CCAService ccaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value="/request-list")
    public List<User> reqList() {
        return ccaService.getRegRequest();
    }

    @PostMapping(value="/request-list/accept")
    public ResponseEntity<String> acceptRequest(@RequestBody String mail) throws JSONException {
        JSONObject obj = new JSONObject(mail);
        if (obj == null || obj.get("mail") == null || obj.get("mail") == "")
            return ResponseEntity.status(400).body("Could not accept");

        if(ccaService.acceptReq((String) obj.get("mail"))) {
            emailService.sendSimpleMessage((String) obj.get("mail"), "Registration accepted",
                    "Welcome to our clinic!");
            return ResponseEntity.ok("Successful");
        }
        else
            return ResponseEntity.status(400).body("Could not accept");
    }

    @PostMapping(value="request-list/reject")
    public ResponseEntity<String> rejectRequest(@RequestBody String expl) throws JSONException {
        JSONObject obj = new JSONObject(expl);
        if (obj == null || obj.get("expl") == null || obj.get("expl") == "" || obj.get("mail") == null || obj.get("mail") == "")
            return ResponseEntity.status(400).body("Could not accept");

        if(ccaService.rejectReq((String) obj.get("mail"))) {
            emailService.sendSimpleMessage((String) obj.get("mail"), "Registration rejected",
                    "Reason for rejection: \n" + (String) obj.get("expl"));
            return ResponseEntity.ok("Successful");
        }
        else
            return ResponseEntity.status(400).body("Could not accept");
    }

    @PostMapping(value="/reg_admin")
    public ResponseEntity<String> register(@RequestBody CAdminRegReq req) {
        if (loginService.registerAdmin(req)) {
            return ResponseEntity.ok("Successful");
        }
        return ResponseEntity.status(400).body("Invalid information");
    }

    @GetMapping(value="/getInfo")
    public ClinicCentreAdmin getInfo(Principal user) {
        return ccaService.findByEmail(user.getName());
    }

    @PostMapping(value="/reg_clinic")
    public ResponseEntity<String> registerClinic(@RequestBody ClinicRegister req) {
        ccaService.registerClinic(req);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping(value="/get_clinics")
    public List<ClinicAndAdmin> getClinics() {
        return clinicService.getClinics();
    }
}
