package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Nurse;
import team47pack.models.PrescriptionVerification;
import team47pack.models.dto.PrescriptionVerifyDTO;
import team47pack.models.mapper.PrescriptionVerificationMapper;
import team47pack.repository.PresVerifRepo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// @author: Lupus7 (Sinisa Canak)
@Service
public class PresVerifService {
    @Autowired
    private PresVerifRepo presVerifRepo;

    @Autowired
    private PrescriptionVerificationMapper mapper;

    public Collection<PrescriptionVerifyDTO> getPresForVerification() {
        List<PrescriptionVerification> presVerif = presVerifRepo.findAllByNurse(null);

        return presVerif.stream()
                .map(pv -> mapper.toDTO(pv)) // mapper::toDTO
                .collect(Collectors.toList());

    }

    public boolean verifyPrescription(Long id, Nurse nurse) {
        Optional<PrescriptionVerification> pv = presVerifRepo.findById(id);
        if (pv.isPresent()) {
            pv.get().setNurse(nurse);
            pv.get().setVerificationTime(new Date());
            presVerifRepo.save(pv.get());
        }
        return pv.isPresent();
    }
}
