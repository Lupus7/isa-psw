package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import team47pack.models.ExaminationType;
import team47pack.models.OperationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface OperationTypeRepo extends JpaRepository<OperationType, Long> , JpaSpecificationExecutor<OperationType> {

	List<OperationType> findByClinic(Long clinic_id);
	List<OperationType>findAll();
	OperationType findByName(String name);

	Optional<OperationType> findById(Long id);
	
	void deleteById(Long id);

	ArrayList<OperationType> findByNameContainingIgnoreCaseAndClinic(String name, Long clinicId);

	ArrayList<OperationType> findByIdAndClinic(Long id, Long clinicId);

	ArrayList<OperationType> findByNameContainingIgnoreCaseAndIdAndClinic(String name, Long id, Long clinicId);

	ArrayList<OperationType> findByClinicAndSpecialization(Long clinicId, String specialization);

}
