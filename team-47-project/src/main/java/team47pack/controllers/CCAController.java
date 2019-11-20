package team47pack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.User;
import team47pack.security.TokenUtils;
import team47pack.service.CCAService;

@RestController
public class CCAController {
    @Autowired
    private CCAService ccaService;
    
    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping(value="/cca/request-list")

    public List<User> reqList(@RequestHeader(name="Authorization") String token) {

    	String email = tokenUtils.getUsernameFromToken(token.substring(7));
    	String role = tokenUtils.getRole(token);
   	
    	if(!role.equals("ROLE_CCADMIN"))
    		return null;
    	

        return ccaService.getRegRequest();
    }

    @PostMapping(value="/cca/request-list/accept/{mail}")
    public ResponseEntity<String> acceptRequest(@PathVariable String mail) {
        if(ccaService.acceptReq(mail))
            return ResponseEntity.ok("Successful");
        else
            return ResponseEntity.status(400).body("Could not accept");
    }

    @PostMapping(value="/cca/request-list/reject/{mail}")
    public ResponseEntity<String> rejectRequest(@PathVariable String mail) {
        if(ccaService.rejectReq(mail))
            return ResponseEntity.ok("Successful");
        else
            return ResponseEntity.status(400).body("Could not accept");
    }
}
