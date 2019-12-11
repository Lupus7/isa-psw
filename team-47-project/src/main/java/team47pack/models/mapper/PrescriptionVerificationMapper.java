package team47pack.models.mapper;

import org.springframework.stereotype.Component;
import team47pack.models.PrescriptionVerification;
import team47pack.models.dto.PrescriptionDTO;
import team47pack.models.dto.PrescriptionVerifyDTO;

@Component
public class PrescriptionVerificationMapper {
    public PrescriptionVerifyDTO toDTO(PrescriptionVerification model) {
        return new PrescriptionVerifyDTO(model.getId(),
                                      model.getDoctor(),
                                      model.getPatient(),
                                      new PrescriptionDTO(model.getPrescription()));
    }
}
