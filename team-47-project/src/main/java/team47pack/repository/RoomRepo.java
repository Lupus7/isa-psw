package team47pack.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.Room;

//@author:Jokara
public interface RoomRepo extends JpaRepository<Room, Long> {

	ArrayList<Room> findByClinicId(Long id);

	Room findByName(String name);

	ArrayList<Room> findByNameContainingIgnoreCaseAndNumberAndClinicId(String name, int number, int clinicId);

	ArrayList<Room> findByNameContainingIgnoreCaseAndClinicId(String name, int clinicId);

	ArrayList<Room> findByNumberAndClinicId(int number, int clinicId);

}
