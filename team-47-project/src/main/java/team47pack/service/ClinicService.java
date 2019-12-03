package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.Examination;
import team47pack.models.dto.ClinicAndAdmin;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.ExaminationRepo;

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

    public List<ClinicAndAdmin> getClinics() {
        List<Clinic> clinics = clinicRepo.findAll();
        List<ClinicAndAdmin> retVal = new ArrayList<>();

        for (Clinic c : clinics) {
            List<String> admins = new ArrayList<>();
            List<ClinicAdmin> cAdmins = caRepo.findAllByClinic(c.getId().intValue());

            for (ClinicAdmin admin : cAdmins) {
                admins.add(admin.getUsername());
            }

            retVal.add(new ClinicAndAdmin(c, admins));
        }

        return retVal;
    }

    public ArrayList<ClinicSearchResult> search(ClinicSearchRequest csr) {
        ArrayList<Clinic> clinics1 = new ArrayList<>();
        clinics1 = retriveClinics(csr.getLocation());
        System.out.println(clinics1);
        ArrayList<Examination> examinations = retrieveExamination(csr.getExamination());

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
}
