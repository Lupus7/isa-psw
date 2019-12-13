package team47pack.controllers;

import java.security.Principal;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team47pack.models.Room;
import team47pack.service.RoomService;

//@author:Jokara
@RestController
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping(value = "/room/getRooms")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<?> getRooms(Principal user) {
		return ResponseEntity.ok(roomService.getRooms(user.getName()));
	}

	@PostMapping(value = "/room/addRoom")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> addRoom(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if (obj == null || obj.get("name") == null || obj.get("number") == null)
			return ResponseEntity.status(400).body("Could not accept!");

		if (obj.get("name").equals("") || obj.get("number").equals(""))
			return ResponseEntity.status(401).body("Empty fields!");

		String name = (String) obj.get("name");
		int number = obj.getInt("number");

		if (!roomService.addRoom(name, number, user.getName()))
			return ResponseEntity.status(400).body("Unsuccessful!");

		return ResponseEntity.ok("Room added!");

	}

	@PostMapping(value = "/room/removeRoom")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> removeRoom(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if (obj == null || obj.get("id") == null)
			return ResponseEntity.status(400).body("Could not accept!");

		if (obj.get("id").equals(""))
			return ResponseEntity.status(400).body("Could not accept!");

		int id = obj.getInt("id");

		if (!roomService.removeRoom(id, user.getName()))
			return ResponseEntity.status(400).body("Unsuccessful!!");

		return ResponseEntity.ok("Room removed!");

	}

	@PostMapping(value = "/room/editRoom")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<String> editRoom(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if (obj == null || obj.get("id") == null || obj.get("name") == null || obj.get("number") == null)
			return ResponseEntity.status(400).body("Could not accept!");

		if (obj.get("id").equals("") || obj.get("name").equals("") || obj.get("number").equals(""))
			return ResponseEntity.status(401).body("Empty fields!");

		int id = obj.getInt("id");
		int number = obj.getInt("number");
		String name = obj.getString("name");

		if (!roomService.editRoom(id, name, number, user.getName()))
			return ResponseEntity.status(402).body("Name already taken!");

		return ResponseEntity.ok("Room edited!");

	}
	
	@PostMapping(value = "/room/search")
	@PreAuthorize("hasRole('CADMIN')")
	public ResponseEntity<?> searchRoom(@RequestBody String json, Principal user) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if (obj == null || obj.get("name") == null || obj.get("number") == null)
			return ResponseEntity.status(400).body("Could not accept!");

		ArrayList<Room> rooms = roomService.searchRoom(obj, user.getName());

		return ResponseEntity.ok(rooms);

	}

}
