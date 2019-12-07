package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.dto.CAdminRegReq;
import team47pack.models.dto.ClinicAndAdmin;
import team47pack.models.dto.ClinicRegister;
import team47pack.models.dto.UserInfo;
import team47pack.service.CCAService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@RestController
@RequestMapping(value="/cca")
@PreAuthorize("hasRole('CCADMIN')")
public class CCAController {

    @Autowired
    private CCAService ccaService;

    @GetMapping(value="/requests")
    public List<UserInfo> reqList() {
        return ccaService.getRequests();
    }

    @PutMapping(value="/requests/{id}")
    public ResponseEntity<String> acceptRequest(@PathVariable(value = "id") Long id) {
        if (ccaService.acceptRequest(id))
            return ResponseEntity.ok("Successful");

        return ResponseEntity.status(400).body("Could not accept User#" + id);
    }

    @DeleteMapping(value="/requests/{id}")
    public ResponseEntity<String> rejectRequest(@RequestBody String reason, @PathVariable(value = "id") Long id) {
        if (reason == null || reason.length() < 10)
            return ResponseEntity.status(400).body("Rejection explanation not acceptable (min 10 characters)");

        if(ccaService.rejectRequest(id, reason))
            return ResponseEntity.ok("Successful");

        return ResponseEntity.status(400).body("Could not reject User#" + id);
    }

    @PostMapping(value="/admin")
    public ResponseEntity<String> register(@Valid @RequestBody CAdminRegReq req) {
        if (ccaService.registerAdmin(req))
            return ResponseEntity.ok("Successful");

        return ResponseEntity.status(400).body("Invalid information");
    }

    @GetMapping(value="/info")
    public UserInfo getInfo(Principal user) {
        return ccaService.findByEmail(user.getName());
    }

    @PostMapping(value="/clinic")
    public ResponseEntity<String> registerClinic(@Valid @RequestBody ClinicRegister req) {
        ccaService.registerClinic(req);
        return ResponseEntity.ok("Successful");
    }

    @GetMapping(value="/clinics")
    public List<ClinicAndAdmin> getClinics() {
        return ccaService.getClinics();
    }
}
