package team47pack.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.ExaminationType;

public interface ExaminationTypeRepo extends JpaRepository<ExaminationType, Long> {

	List<ExaminationType> findByClinic(Long clinic_id);

	ExaminationType findByName(String name);

	Optional<ExaminationType> findById(Long id);
	
	void deleteById(Long id);

	ArrayList<ExaminationType> findByNameContainingIgnoreCaseAndClinic(String name, Long clinicId);

	ArrayList<ExaminationType> findByIdAndClinic(Long id, Long clinicId);

	ArrayList<ExaminationType> findByNameContainingIgnoreCaseAndIdAndClinic(String name, Long id, Long clinicId);

}
