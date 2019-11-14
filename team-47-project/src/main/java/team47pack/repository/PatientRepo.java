package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team47pack.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {
    Patient findByEmail(String email);
}
