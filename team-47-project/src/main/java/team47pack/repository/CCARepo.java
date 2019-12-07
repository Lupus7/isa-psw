package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.ClinicCentreAdmin;

// @author: Lupus7 (Sinisa Canak)
public interface CCARepo extends JpaRepository<ClinicCentreAdmin,Long> {
    ClinicCentreAdmin findByEmail(String email);
}