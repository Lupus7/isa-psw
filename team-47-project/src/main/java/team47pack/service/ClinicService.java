package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.ClinicAndAdmin;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.models.dto.RateRequest;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.ExaminationRepo;
import team47pack.repository.RateRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ClinicService {
    @Autowired
    ClinicRepo clinicRepo;

    @Autowired
    ClinicAdminRepo caRepo;

    @Autowired
    ExaminationRepo exRepo;

    @Autowired
    RateRepo rateRepo;

    // @author: Lupus7 (Sinisa Canak)
    public List<ClinicAndAdmin> getClinics() {
        List<Clinic> clinics = clinicRepo.findAll();
        List<ClinicAndAdmin> retVal = new ArrayList<>();

        for (Clinic c : clinics) {
            List<String> admins = new ArrayList<>();

            for (ClinicAdmin admin : c.getClinicAdmins()) {
                admins.add(admin.getUsername());
            }

            retVal.add(new ClinicAndAdmin(c, admins));
        }

        return retVal;
    }

    public void save(Clinic clinic) {
        clinicRepo.save(clinic);
    }
    ////

    public ArrayList<ClinicSearchResult> search(ClinicSearchRequest csr) {
        ArrayList<Clinic> clinics1 = new ArrayList<>();
        clinics1 = retriveClinics(csr.getLocation());
        if (clinics1.size() != 0){
            System.out.println("Klinika: " + clinics1.get(0).getAddress() + clinics1.get(0).getName());
        }
        if(csr.getRate() != null){
            for(int i=0;i<clinics1.size();i++){
                Clinic c = clinics1.get(i);
                System.out.println("Iteriramo kroz" + c.getName() +c.getDescription());
                c.setAverage(c.calculateRate());
                if(c.getAverage() < csr.getRate()){
                    System.out.println("BRISE SE OVA: " +c.getName() + " " +c.getDescription());
                    clinics1.remove(i);
                }
            }
        }
        System.out.println("PRitmao one koje su ostale");
        for(Clinic c: clinics1){
            System.out.println(c.getAddress() + c.getName() + c.getDescription());
        }
        ArrayList<Examination> examinations = retrieveExamination(csr.getExamination());
        if (examinations.size() != 0) {
            System.out.println(examinations.get(0).getId() + " " + examinations.get(0).getType());
        }else{
            System.out.println("EXAMINATIONS SU NULL");
        }
        ArrayList<ClinicSearchResult> result = new ArrayList<>();
        for (Clinic c : clinics1) {
            for (Doctor d : c.getDoctors()) {
                for (Examination e : examinations) {
                    if (d.getId().intValue() == e.getDoctor().getId().intValue()) {
                            ClinicSearchResult res = new ClinicSearchResult();
                            System.out.println("Ovo je isto::::");
                            System.out.println(d.getId().intValue() +" "+ e.getDoctor().getId().intValue());
                            res.setClinic(c);
                            res.setCost(3000);
                            result.add(res);
                            break;
                    }
                }
            }
        }
            if(result.size() != 0) {
                System.out.println(result.get(0).getClinic().getAddress() + " " + result.get(0).getClinic().getName() + " " + result.get(0).getCost());
            }
            return result;

    }

    public ArrayList<Examination>retrieveExamination(String type){
        Specification<Examination> spec = Specification
                .where(ClinicSpecification.examinationType(type));
        return new ArrayList<>(new HashSet<>(exRepo.findAll(spec, PageRequest.of(0, 10, Sort.by("type"))).toList()));
    }

    public ArrayList<Clinic>retriveClinics(String address){
        Specification<Clinic> spec = Specification.where(ClinicSpecification.clinicLocation(address));
        return new ArrayList<>(new HashSet<>(clinicRepo.findAll(spec, PageRequest.of(0, 10, Sort.by("address"))).toList()));
    }

    public Clinic getClinic(Long id) {
        Clinic c= clinicRepo.getOne(id);
        System.out.println("Klinika iz repozitorijuma : " + c.getDescription() + c.getName());
        return c;
    }

    public boolean leaveRate(RateRequest rateRequest) {
        List<Clinic> klinike = clinicRepo.findAll();
        for(Clinic c: klinike){
            for(Doctor d: c.getDoctors()){
                if(d.getId() == rateRequest.getId()){
                    System.out.println("To je ta klinika: " + c.getName() +" " +c.getDescription());
                    Rate rate = new Rate();
                    rate.setValue(rateRequest.getValue());
                    rateRepo.save(rate);
                    c.getRatings().add(rate);
                    clinicRepo.save(c);
                    return true;
                }
            }
        }
        return false;
    }
}
