package team47pack.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import team47pack.models.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {
	Doctor findByEmail(String email);

	Optional<Doctor> findById(Long id);

	ArrayList<Doctor> findAll();

	ArrayList<Doctor> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName,
			String lastName);

	ArrayList<Doctor> findByFirstNameContainingIgnoreCase(String firstname);

	ArrayList<Doctor> findByLastNameContainingIgnoreCase(String lastname);

	List<Doctor> findBySpecializationAndClinicId(String spec, Long clinicId);

	List<Doctor> findByClinicId(Long clinicId);


}
