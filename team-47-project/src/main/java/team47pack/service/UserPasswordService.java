package team47pack.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import team47pack.models.User;
import team47pack.models.dto.PasswordRequest;
import team47pack.repository.UserRepo;

@Service
public class UserPasswordService {

	@Autowired
	private UserRepo userRepo;

	public boolean getFirstLogin(String email) {
		User u = (User) userRepo.findByEmail(email);
		if (u.getLastPasswordResetDate() != null)
			return false;

		return true;
	}

	public ResponseEntity<String> updatePasswordFL(PasswordRequest req) {
		User u = userRepo.findByEmail(req.getEmail());
		if (u == null)
			return ResponseEntity.badRequest().body("Unsuccessful!");

		if (req.getPasswordConf() == null || req.getPasswordNew() == null || req.getPasswordConf().equals("")
				|| req.getPasswordNew().equals(""))
			return ResponseEntity.badRequest().body("Fill all the fields");

		if (!req.getPasswordConf().equals(req.getPasswordNew()))
			return ResponseEntity.badRequest().body("Your password and confirmation password do not match!");

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		String hash = enc.encode(req.getPasswordNew());
		u.setPassword(hash);
		u.setLastPasswordResetDate(Timestamp.valueOf(LocalDateTime.now()));
		userRepo.save(u);

		return ResponseEntity.ok("You have successfuly changed your password!");
	}

	public ResponseEntity<String> updatePassword(PasswordRequest req) {
		User u = userRepo.findByEmail(req.getEmail());
		if (u == null)
			return ResponseEntity.badRequest().body("Unsuccessful!");

		if (req.getPasswordCurr() == null || req.getPasswordNew() == null || req.getPasswordConf() == null
				|| req.getPasswordCurr().equals("") || req.getPasswordNew().equals("") || req.getPasswordConf().equals(""))
			return ResponseEntity.badRequest().body("Fill all the fields");

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

		if (!enc.matches(req.getPasswordCurr(), u.getPassword()))
			return ResponseEntity.badRequest().body("Wrong password!");
		else {
			if (!req.getPasswordNew().equals(req.getPasswordConf()))
				return ResponseEntity.badRequest().body("Your password and confirmation password do not match!");
			else {
				String hashPass = enc.encode(req.getPasswordNew());
				u.setPassword(hashPass);
				u.setLastPasswordResetDate(Timestamp.valueOf(LocalDateTime.now()));
				userRepo.save(u);
				return ResponseEntity.ok("You have successfuly changed your password!");
			}
		}
	}

}
