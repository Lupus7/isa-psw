package team47pack.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.RoomArrange;

public interface RoomArrangeRepo extends JpaRepository<RoomArrange, Long> {

	List<RoomArrange> findByDate(Date date);

	List<RoomArrange> findByRoom(Long id);

}
