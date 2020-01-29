package team47pack.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.Clinic;
import team47pack.models.ClinicAdmin;
import team47pack.models.Patient;
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

	public boolean addRoom(String name, int number, String type, String email) {
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

		Optional<Clinic> clinic = clinicRepo.findById(Long.parseLong("" + ca.getClinic()));
		if (!clinic.isPresent())
			return false;

		clinic.get().getRooms().remove(room);

		roomRepo.deleteById(Long.parseLong("" + id));

		return true;

	}

	public boolean editRoom(int id, String name, int number, String type, String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		Optional<Room> room = roomRepo.findById(Long.parseLong("" + id));
		if (!room.isPresent())
			return false;

		// provera
		Room room1 = roomRepo.findByName(name);
		if (room1 != null && room1.getClinicId() == ca.getClinic() && !room1.getName().equals(room.get().getName()))
			return false;

		if (!name.equals(room.get().getName()))
			room.get().setName(name);
		room.get().setNumber(number);
		room.get().setType(type);

		roomRepo.save(room.get());

		return true;
	}

	public ArrayList<Room> searchRoom(JSONObject obj, String email) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		ArrayList<Room> rooms = new ArrayList<>();
		if (ca == null)
			return rooms;
		
		//obj.get("date") == null,&& obj.get("date").equals("")
	

		if (obj.get("name") == null || obj.get("number") == null)
			return rooms;
		
		else if (obj.get("name").equals("") && obj.get("number").equals(""))
			return roomRepo.findAllByClinicId(ca.getClinic());

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

	public ArrayList<Room> filter(JSONObject obj, String email) throws JSONException {
		if (obj == null || obj.get("filterBy") == null || obj.get("valueFilter") == null)
			return new ArrayList<>();
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		
		if (obj.get("filterBy").equals("") && obj.get("valueFilter").equals(""))
			return new ArrayList<>();

		String filterBy = (String) obj.get("filterBy");
		String value = (String) obj.get("valueFilter");

		if (filterBy.equals("Name")) {
			return roomRepo.findByNameContainingIgnoreCaseAndClinicId(value, ca.getClinic());
			
		} else if (filterBy.equals("Number"))
			return roomRepo.findByNumberAndClinicId(Integer.parseInt(value), ca.getClinic());
		
		else if (filterBy.equals("Type"))
			return roomRepo.findByTypeContainingIgnoreCaseAndClinicId(value, ca.getClinic());		

		return new ArrayList<>();
		
	}

}
