package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import team47pack.models.MedicalFile;

import java.util.List;

public interface MedFileRepo extends JpaRepository<MedicalFile, Long>, JpaSpecificationExecutor<MedicalFile> {
    List<MedicalFile> findAll();
}
