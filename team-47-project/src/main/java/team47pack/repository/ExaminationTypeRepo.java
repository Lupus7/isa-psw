package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import team47pack.models.ExaminationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ExaminationTypeRepo extends JpaRepository<ExaminationType, Long> , JpaSpecificationExecutor<ExaminationType> {

	List<ExaminationType> findByClinic(Long clinic_id);
	List<ExaminationType>findAll();
	ExaminationType findByName(String name);

	Optional<ExaminationType> findById(Long id);
	
	void deleteById(Long id);

	ArrayList<ExaminationType> findByNameContainingIgnoreCaseAndClinic(String name, Long clinicId);

	ArrayList<ExaminationType> findByIdAndClinic(Long id, Long clinicId);

	ArrayList<ExaminationType> findByNameContainingIgnoreCaseAndIdAndClinic(String name, Long id, Long clinicId);

	ArrayList<ExaminationType> findByClinicAndSpecialization(Long clinicId, String specialization);

}
