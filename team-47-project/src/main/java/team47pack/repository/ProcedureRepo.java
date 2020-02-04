package team47pack.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.Procedure;

public interface ProcedureRepo extends JpaRepository<Procedure, Long> {
	
	List<Procedure> findByDate(Date date);

}
