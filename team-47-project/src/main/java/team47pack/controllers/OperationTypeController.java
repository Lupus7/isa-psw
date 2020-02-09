package team47pack.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.OperationType;
import team47pack.service.OperationTypeService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ca")
public class OperationTypeController {

    @Autowired
    OperationTypeService operationTypeService;

    @GetMapping(value = "/getOperationTypes")
    @PreAuthorize("hasRole('CADMIN')")
    public List<OperationType> getOperationTypes(Principal user) {
        return operationTypeService.getOperationTypes(user.getName());
    }

    @GetMapping(value = "/getOperTypes")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<OperationType> getOperTypes(Principal user) {
        return operationTypeService.getOperTypes(user.getName());
    }

    @PostMapping(value = "/addOperationType", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('CADMIN')")
    public ResponseEntity<String> changeData(@RequestBody String json, Principal user) throws JSONException {
        JSONObject obj = new JSONObject(json);

        if (operationTypeService.addOperationType(obj, user.getName()))
            return ResponseEntity.ok("Operation type successfulty added!");
        else
            return ResponseEntity.status(400).body("Could not add!");
    }

    @PostMapping(value = "/removeOperationType")
    @PreAuthorize("hasRole('CADMIN')")
    public ResponseEntity<String> removeOperationType(@RequestBody String json, Principal user) throws JSONException {
        JSONObject obj = new JSONObject(json);
        if (obj == null || obj.get("id") == null)
            return ResponseEntity.status(400).body("Could not accept!");

        if (obj.get("id").equals(""))
            return ResponseEntity.status(400).body("Could not accept!");

        Long id = obj.getLong("id");

        if (!operationTypeService.removeOperationType(id, user.getName()))
            return ResponseEntity.status(400).body("Unsuccessful!!");

        return ResponseEntity.ok("Operation type removed!");

    }

    @PostMapping(value = "/editOperationType")
    @PreAuthorize("hasRole('CADMIN')")
    public ResponseEntity<String> editRoom(@RequestBody String json, Principal user) throws JSONException {
        JSONObject obj = new JSONObject(json);

        if (!operationTypeService.editOperationType(obj, user.getName()))
            return ResponseEntity.status(402).body("Name already taken!");

        return ResponseEntity.ok("Operation type edited!");

    }

    @PostMapping(value = "/searchOperationTypes")
    @PreAuthorize("hasRole('CADMIN')")
    public ResponseEntity<?> searchRoom(@RequestBody String json, Principal user) throws JSONException {
        JSONObject obj = new JSONObject(json);

        ArrayList<OperationType> ets = operationTypeService.searchOT(obj, user.getName());

        return ResponseEntity.ok(ets);

    }

}

