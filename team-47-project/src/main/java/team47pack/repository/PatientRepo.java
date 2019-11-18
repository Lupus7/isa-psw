package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team47pack.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {
    Patient findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Patient p set p.isAccepted = True where p.email = ?1")
    int acceptUser(String mail);

    @Transactional
    @Modifying
    @Query("delete from Patient where email = ?1")
    int rejectUser(String mail);
}
