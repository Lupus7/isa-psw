package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.User;
import team47pack.repository.UserRepo;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserByEmail(String email){return userRepo.findByEmail(email);}
}
