package team47pack.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team47pack.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long> {
	
	 ArrayList<Patient> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName,String lastName);


}


