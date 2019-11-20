package team47pack.controllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import team47pack.models.ClinicCentreAdmin;
import team47pack.models.Doctor;
import team47pack.models.User;
import team47pack.models.dto.RegisterRequest;
import team47pack.repository.CCARepo;
import team47pack.security.TokenUtils;
import team47pack.service.CCAService;
import team47pack.service.EmailService;
import team47pack.service.LoginService;

@RestController
public class CCAController {
    @Autowired
    private CCAService ccaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CCARepo ccaRepo;

    @GetMapping(value="/cca/request-list")

    public List<User> reqList(@RequestHeader(name="Authorization") String token) {

    	String email = tokenUtils.getUsernameFromToken(token.substring(7));
    	String role = tokenUtils.getRole(token);

    	if(!role.equals("ROLE_CCADMIN"))
    		return null;

        return ccaService.getRegRequest();
    }

    @PostMapping(value="/cca/request-list/accept")
    public ResponseEntity<String> acceptRequest(@RequestHeader(name="Authorization") String token, @RequestBody String mail) throws JSONException {

        if(!tokenUtils.getRole(token).equals("ROLE_CCADMIN"))
            return ResponseEntity.status(400).body("Not authorized");

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

    @PostMapping(value="/cca/request-list/reject")
    public ResponseEntity<String> rejectRequest(@RequestHeader(name="Authorization") String token, @RequestBody String expl) throws JSONException {

        System.out.println(expl);

        if(!tokenUtils.getRole(token).equals("ROLE_CCADMIN"))
            return ResponseEntity.status(400).body("Not authorized");

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

    @PostMapping(value = "/cca/reg_admin", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        boolean b = loginService.registerAdmin(req);
        if (b == true) {
            return ResponseEntity.ok("Successful");
        }
        return ResponseEntity.status(400).body("Invalid information");
    }

    @GetMapping(value="/cca/getInfo")
    public ClinicCentreAdmin getInfo(@RequestHeader(name="Authorization") String token) {

        String email = tokenUtils.getUsernameFromToken(token.substring(7));
        String role = tokenUtils.getRole(token);

        if(!role.equals("ROLE_CCADMIN") || email == null )
            return null;

        ClinicCentreAdmin cca = ccaRepo.findByEmail(email);

        return cca;
    }
}
