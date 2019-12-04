package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import team47pack.models.Diagnosis;

import java.util.List;

public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long>, JpaSpecificationExecutor<Diagnosis> {
    List<Diagnosis> findAll();
}
