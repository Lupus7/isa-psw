package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.MedicalStaff;

public interface MedicalStaffRepo extends JpaRepository<MedicalStaff, Long> {
	MedicalStaff findByEmail(String email);


}
