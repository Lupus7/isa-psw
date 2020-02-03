package team47pack.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.MedicalStaff;

public interface MedicalStaffRepo extends JpaRepository<MedicalStaff, Long> {
	MedicalStaff findByEmail(String email);

	List<MedicalStaff> findByHolidayBegin(Date begin);
	List<MedicalStaff> findByHolidayEnd(Date end);

	List<MedicalStaff> findByHolidayBeginNotNullAndHolidayEndNotNull();



}
