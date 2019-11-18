package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team47pack.models.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.accepted = True where u.email = ?1")
    int acceptUser(String mail);

    @Transactional
    @Modifying
    @Query("delete from User where email = ?1")
    int rejectUser(String mail);

    List<User> findByAccepted(boolean accepted);
}

