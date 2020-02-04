package team47pack.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team47pack.models.Patient;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
	
	Optional<Patient> findById(Long id);

	ArrayList<Patient> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName,
			String lastName);

	ArrayList<Patient> findByFirstNameContainingIgnoreCase(String firstName);

	ArrayList<Patient> findByLastNameContainingIgnoreCase(String lastName);

	ArrayList<Patient> findByAddressContainingIgnoreCase(String address);

	ArrayList<Patient> findByCityContainingIgnoreCase(String city);

	ArrayList<Patient> findByStateContainingIgnoreCase(String state);

	ArrayList<Patient> findByTelephoneContainingIgnoreCase(String telephone);

	ArrayList<Patient> findByUniqueNumContainingIgnoreCase(String uniqueNum);

	Patient findByEmail(String email);
	
	// new functions
	Page<Patient> findAll(Pageable page);

	Slice<Patient> findAllByEnabled(boolean b, Pageable page);

	Optional<Patient> findByIdAndEnabled(Long id, boolean b);

	ArrayList<Patient> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndEnabled(String firstName,
			String lastName, boolean b);

	ArrayList<Patient> findByFirstNameContainingIgnoreCaseAndEnabled(String firstName, boolean b);

	ArrayList<Patient> findByLastNameContainingIgnoreCaseAndEnabled(String lastName, boolean b);

	ArrayList<Patient> findByAddressContainingIgnoreCaseAndEnabled(String value, boolean b);

	ArrayList<Patient> findByCityContainingIgnoreCaseAndEnabled(String value, boolean b);

	ArrayList<Patient> findByStateContainingIgnoreCaseAndEnabled(String value, boolean b);

	ArrayList<Patient> findByTelephoneContainingIgnoreCaseAndEnabled(String value, boolean b);

	ArrayList<Patient> findByUniqueNumContainingIgnoreCaseAndEnabled(String value, boolean b);

	Page<Patient> findByEnabled(Pageable sort, boolean b);

}
