package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import team47pack.models.Clinic;

import java.util.ArrayList;
import java.util.List;

public interface ClinicRepo extends JpaRepository<Clinic, Long>, JpaSpecificationExecutor<Clinic> {
        Clinic findByName(String name);
        List<Clinic> findAll();
        @Query(nativeQuery=true, value="SELECT * FROM Clinic WHERE address=?1")
        ArrayList<Clinic> findByAddress(String address);

        Clinic findByDoctorsId(Long Id);
}
