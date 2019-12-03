package team47pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import team47pack.models.HolidayTimeOff;

public interface HolidayTimeOffRepository extends JpaRepository<HolidayTimeOff, Long> {
	List<HolidayTimeOff> findByAccepted(boolean accepted);
	
	@Transactional
    @Modifying
    @Query("update HolidayTimeOff h set h.accepted = True where h.id = ?1")
    int acceptRequest(Long id);

    @Transactional
    @Modifying
    @Query("delete from HolidayTimeOff where id = ?1")
    int rejectRequest(Long id);

}
