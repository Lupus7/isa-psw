package team47pack.service;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Room;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ClinicRepo;
import team47pack.repository.RoomRepo;

//@author:Jokara
@Service
public class RoomService {

	@Autowired
	private RoomRepo roomRepo;

	@Autowired
	private ClinicRepo clinicRepo;

	@Autowired
	private ClinicAdminRepo clinicAdminRepo;

	public ArrayList<Room> getRooms(String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		ArrayList<Room> rooms = new ArrayList<>();
		if (ca == null)
			return rooms;
		rooms = (ArrayList<Room>) roomRepo.findAllByClinicId(ca.getClinic());

		return rooms;
	}

	public boolean addRoom(String name, int number, String type,String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		Room room = roomRepo.findByName(name);
		if (room != null && room.getClinicId() == ca.getClinic())
			return false;

		roomRepo.save(new Room(name, number, ca.getClinic(), type));

		return true;
	}

	public boolean removeRoom(int id, String email) {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		Room room = roomRepo.getOne(Long.parseLong("" + id));

		if (room == null)
			return false;

		Clinic clinic = clinicRepo.getOne(Long.parseLong("" + ca.getClinic()));
		clinic.getRooms().remove(room);

		roomRepo.deleteById(Long.parseLong("" + id));

		return true;

	}

	public boolean editRoom(int id, String name, int number, String type, String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		Room room = roomRepo.getOne(Long.parseLong("" + id));
		if (room == null)
			return false;

		// provera
		Room room1 = roomRepo.findByName(name);
		if (room1 != null && room1.getClinicId() == ca.getClinic() && !room1.getName().equals(room.getName()))
			return false;

		if (!name.equals(room.getName()))
			room.setName(name);
		room.setNumber(number);
		room.setType(type);

		roomRepo.save(room);

		return true;
	}

	public ArrayList<Room> searchRoom(JSONObject obj, String email) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		ArrayList<Room> rooms = new ArrayList<>();
		if (ca == null)
			return rooms;

		if (obj.get("name").equals("") && obj.get("number").equals(""))
			return rooms;

		else if (!obj.get("name").equals("") && obj.get("number").equals(""))
			return roomRepo.findByNameContainingIgnoreCaseAndClinicId(obj.getString("name"), ca.getClinic());

		else if (obj.get("name").equals("") && !obj.get("number").equals(""))
			return roomRepo.findByNumberAndClinicId(obj.getInt("number"), ca.getClinic());

		else if (!obj.get("name").equals("") && !obj.get("number").equals("")) {

			String name = obj.getString("name");
			int number = obj.getInt("number");
			return roomRepo.findByNameContainingIgnoreCaseAndNumberAndClinicId(name, number, ca.getClinic());

		}

		return rooms;
	}

}
