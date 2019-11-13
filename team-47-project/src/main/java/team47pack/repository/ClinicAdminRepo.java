package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.ClinicAdmin;

public interface ClinicAdminRepo extends JpaRepository<ClinicAdmin,Long> {
    ClinicAdmin  findByEmail(String email);
}
