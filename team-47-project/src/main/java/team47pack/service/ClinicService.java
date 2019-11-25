package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.dto.ClinicAndAdmin;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicService {
    @Autowired
    ClinicRepo clinicRepo;

    @Autowired
    ClinicAdminRepo caRepo;

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
}
