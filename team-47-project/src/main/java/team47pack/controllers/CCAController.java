package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team47pack.models.User;
import team47pack.service.CCAService;

import java.util.List;

@RestController
public class CCAController {
    @Autowired
    private CCAService ccaService;

    @GetMapping(value="/cca/request-list")
    public List<User> reqList() {
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
