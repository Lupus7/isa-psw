package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.HolidayTimeOff;

public interface HolidayTimeOffRepository extends JpaRepository<HolidayTimeOff, Long> {

}
