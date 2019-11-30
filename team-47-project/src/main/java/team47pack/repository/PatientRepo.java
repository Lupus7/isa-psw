package team47pack.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team47pack.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

	ArrayList<Patient> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName,
			String lastName);

	ArrayList<Patient> findByFirstNameContainingIgnoreCase(String firstName);

	ArrayList<Patient> findByLastNameContainingIgnoreCase(String lastName);

	ArrayList<Patient> findByAddressContainingIgnoreCase(String address);

	ArrayList<Patient> findByCityContainingIgnoreCase(String city);

	ArrayList<Patient> findByStateContainingIgnoreCase(String state);

	ArrayList<Patient> findByTelephoneContainingIgnoreCase(String telephone);

	ArrayList<Patient> findByUniqueNumContainingIgnoreCase(String uniqueNum);

}
