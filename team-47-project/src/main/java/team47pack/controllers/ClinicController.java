package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team47pack.models.Clinic;
import team47pack.models.Doctor;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.models.dto.RateRequest;
import team47pack.security.TokenUtils;
import team47pack.service.ClinicService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClinicController {
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping(value = "/clinic/search", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('PATIENT')")
    public ArrayList<ClinicSearchResult> searchForClinics(@RequestBody ClinicSearchRequest csr) {
        System.out.println(csr.getExamination() + " " + csr.getLocation() + " " + csr.getDate());
        System.out.println("PROSEK je: " + csr.getRate());
        ArrayList<ClinicSearchResult> rez = clinicService.search(csr);
        for (ClinicSearchResult c : rez) {
            System.out.println(c.getClinic().getAddress() + " " + c.getClinic().getDescription());
            System.out.println("PRosek klinike : " + c.getClinic().calculateRate());
        }
        return rez;
    }

    @GetMapping(value = "clinic/{id}/getAvailableDoctors")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Doctor> getDoctors(@PathVariable(value = "id") Long id) {
        System.out.println("DA li je ime klinike stiglo : " + id);
        Clinic clinic = clinicService.getClinic(id);
        clinic.setAverage(clinic.calculateRate());
        System.out.println("PROSEK JE: " + clinic.getAverage());
        if (clinic != null) {
            System.out.println("Uspesno vratio kliniku : " + clinic.getName() + clinic.getDescription());
            return clinic.getDoctors();
        }
        return null;
    }

    @PostMapping(value = "clinic/leaveRate")
    @PreAuthorize("hasRole('PATIENT')")
    public void leaveRate(@RequestBody RateRequest rateRequest){
        System.out.println(rateRequest.getId() + " " +rateRequest.getValue());
        boolean b = clinicService.leaveRate(rateRequest);
        if(b){
            System.out.println("USPESNO");
        }
        System.out.println("Nije");
    }
}