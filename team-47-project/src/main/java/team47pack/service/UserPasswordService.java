package team47pack.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import team47pack.models.User;
import team47pack.models.dto.PasswordRequest;
import team47pack.models.dto.RegisterRequest;
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

	public boolean updatePasswordFL(RegisterRequest req) {
		User u = userRepo.findByEmail(req.getEmail());
		if (u == null || req.getPassword().equals("") || req.getPassword() == null)
			return false;

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		String hash = enc.encode(req.getPassword());
		u.setPassword(hash);
		userRepo.save(u);

		return true;
	}

	public String updatePassword(PasswordRequest req) {
		User u = userRepo.findByEmail(req.getEmail());
		if (u == null)
			return "Unsuccessful!";

		if (req.getPasswordCurr().equals("") || req.getPasswordNew().equals("") || req.getPasswordConf().equals("")
				|| req.getPasswordCurr() == null || req.getPasswordNew() == null || req.getPasswordConf() == null)
			return "Fill all the fields";

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		
		if (!enc.matches(req.getPasswordCurr(), u.getPassword()))
			return "Wrong password!";
		else {
			if (!req.getPasswordNew().equals(req.getPasswordConf()))
				return "Your password and confirmation password do not match!";
			else {
				String hashPass = enc.encode(req.getPasswordNew());
				u.setPassword(hashPass);
				u.setLastPasswordResetDate(Timestamp.valueOf(LocalDateTime.now()));
				userRepo.save(u);
				return "You have successfuly changed your password!";
			}
		}
	}

}
