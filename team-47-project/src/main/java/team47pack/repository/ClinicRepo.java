package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.Clinic;

public interface ClinicRepo extends JpaRepository<Clinic, Long> {
        Clinic findByName(String name);
}
