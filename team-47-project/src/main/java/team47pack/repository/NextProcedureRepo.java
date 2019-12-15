package team47pack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.NextProcedure;

public interface NextProcedureRepo extends JpaRepository<NextProcedure, Long> {
	
	 Optional<NextProcedure> findByDoctorId(Long id);

}
