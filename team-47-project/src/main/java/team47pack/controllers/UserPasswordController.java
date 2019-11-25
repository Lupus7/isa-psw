package team47pack.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import team47pack.models.dto.PasswordRequest;
import team47pack.models.dto.RegisterRequest;
import team47pack.service.UserPasswordService;

@Controller
public class UserPasswordController {

	@Autowired
	private UserPasswordService userPService;

	@GetMapping(value = "/user/firstLogin")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Boolean> getInfo(Principal user) {
		return ResponseEntity.ok(this.userPService.getFirstLogin(user.getName()));
	}

	@PostMapping(value = "/user/updatePasswordFLogin", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updatePasswordFirstLogin(@RequestBody RegisterRequest req) {

		boolean check = userPService.updatePasswordFL(req);
		if (!check) {
			return ResponseEntity.badRequest().body("Unsuccessful");
		}

		return ResponseEntity.ok("You have successfuly changed your password!");
	}

	@PostMapping(value = "/user/updatePassword", produces = "application/json", consumes = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordRequest req) {

		return ResponseEntity.ok(userPService.updatePassword(req));
	}

}
