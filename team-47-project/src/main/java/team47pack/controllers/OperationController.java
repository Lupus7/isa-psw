package team47pack.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Operation;
import team47pack.service.OperationService;

import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping(value = "/ca")
@PreAuthorize("hasRole('CADMIN')")
public class OperationController {

    @Autowired
    OperationService operationService;

    @GetMapping(value="/getNextOperations")
    @PreAuthorize("hasRole('CADMIN')")
    public java.util.List<Operation> getOperations(Principal user){
        return operationService.getNextOper(user.getName());

    }

    @PostMapping(value = "/arrangeOperation")
    @PreAuthorize("hasRole('CADMIN')")
    public ResponseEntity<String> arrangeOperation(@RequestBody String json, Principal user) throws JSONException, ParseException {

        if (!operationService.arrangeOperation(json, user.getName()))
            return ResponseEntity.status(400).body("Unsuccessful!!");

        return ResponseEntity.ok("Room was successfuly arranged for examination!");
    }
}
