package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.Nurse;
import team47pack.models.PrescriptionVerification;

import java.util.List;

public interface PresVerifRepo extends JpaRepository<PrescriptionVerification, Long> {
    List<PrescriptionVerification> findAll();
    List<PrescriptionVerification> findAllByNurse(Nurse nurse);
}
