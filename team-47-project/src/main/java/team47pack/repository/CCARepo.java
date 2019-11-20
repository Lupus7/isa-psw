package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.ClinicCentreAdmin;

public interface CCARepo extends JpaRepository<ClinicCentreAdmin,Long> {
    ClinicCentreAdmin findByEmail(String email);
}