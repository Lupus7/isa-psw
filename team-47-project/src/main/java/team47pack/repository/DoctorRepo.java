package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team47pack.models.Doctor;

import java.util.ArrayList;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
    Doctor findByEmail(String email);

    ArrayList<Doctor> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName,String lastName);
    ArrayList<Doctor> findByFirstNameContainingIgnoreCase(String firstname);
    ArrayList<Doctor> findByLastNameContainingIgnoreCase(String lastname);
}
