package team47pack.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.NextProcedure;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NextProcedureRepo extends JpaRepository<NextProcedure, Long> {

	Optional<NextProcedure> findByDoctorId(Long id);

	Optional<NextProcedure> findByExaminationtypeId(Long id);

	Page<NextProcedure> findByClinicAndArranged(Long clinicId, boolean b, Pageable sort);

	List<NextProcedure> findByArrangedAndType(boolean b, String string);

	List<NextProcedure> findByPatientAndArranged(Object object, boolean b);

	Page<NextProcedure> findByPatientAndArranged(Object object, boolean b, Pageable pageable);

	List<NextProcedure> findByArrangedAndTypeAndPatientNotNull(boolean b, String string);

	//Page<NextProcedure> findByPatientAndArranged(Long patientId, boolean b, Pageable pageable);

	Page<NextProcedure> findByClinicAndArrangedAndPatientNotNull(Long clinicId, boolean b, Pageable sort);

	NextProcedure findByDateAndPickedtimeAndArranged(Date date, int time, boolean b);

}
