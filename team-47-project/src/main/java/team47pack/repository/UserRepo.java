package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team47pack.models.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);

    // @author: Lupus7 (Sinisa Canak)
    @Transactional
    @Modifying
    @Query("update User set accepted = True where id = ?1")
    int acceptUser(Long id);

    // @author: Lupus7 (Sinisa Canak)
    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    int rejectUser(Long id);

    // @author: Lupus7 (Sinisa Canak)
    List<User> findByAccepted(boolean accepted);
}

