package team47pack.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.Room;

//@author:Jokara
public interface RoomRepo extends JpaRepository<Room, Long> {

	ArrayList<Room> findAllByClinicId(int clinicId);

	Room findByName(String name);

	ArrayList<Room> findByNameContainingIgnoreCaseAndNumberAndClinicId(String name, int number, int clinicId);

	ArrayList<Room> findByNameContainingIgnoreCaseAndClinicId(String name, int clinicId);

	ArrayList<Room> findByNumberAndClinicId(int number, int clinicId);

	ArrayList<Room> findByTypeContainingIgnoreCaseAndClinicId(String value, int clinic);

	List<Room> findByType(String string);

}
