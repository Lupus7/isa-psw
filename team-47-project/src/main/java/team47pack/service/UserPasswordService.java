package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import team47pack.models.User;
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

	public boolean updatePassword(RegisterRequest req) {
		User u = userRepo.findByEmail(req.getEmail());
		if (u == null || req.getPassword().equals("") || req.getPassword() == null)
			return false;

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		String hash = enc.encode(req.getPassword());
		u.setPassword(hash);
		userRepo.save(u);

		return true;
	}

}
