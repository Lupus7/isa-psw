package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.security.TokenUtils;
import team47pack.service.ClinicService;

import java.util.ArrayList;

@RestController
public class ClinicController {
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping(value = "/clinic/search", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('PATIENT')")
    public ArrayList<ClinicSearchResult> searchForClinics(@RequestBody ClinicSearchRequest csr){
        System.out.println(csr.getExamination() + " " + csr.getLocation());
        ArrayList<ClinicSearchResult> rez =  clinicService.search(csr);
        for(ClinicSearchResult c: rez){
            System.out.println(c.getClinic().getAddress() +" " + c.getClinic().getDescription());
        }
        return rez;
    }
}
