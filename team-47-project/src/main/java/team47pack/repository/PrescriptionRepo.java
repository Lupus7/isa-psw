package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import team47pack.models.Prescription;

import java.util.List;

public interface PrescriptionRepo extends JpaRepository<Prescription, Long>, JpaSpecificationExecutor<Prescription> {
        List<Prescription> findAll();
}
