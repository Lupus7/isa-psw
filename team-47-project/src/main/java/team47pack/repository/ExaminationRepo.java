package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import team47pack.models.Examination;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ExaminationRepo extends JpaRepository<Examination,Long>, JpaSpecificationExecutor<Examination> {
    Examination findById(int id);
  //  @Query(nativeQuery=true, value="SELECT * FROM Examination e, Procedure p WHERE e.id = p.id and p.patient=?1 order by date asc")
    List<Examination> findByPatientId(Long id);
    List<Examination> findAll();
    ArrayList<Examination> findByType(String type);
    
   Optional<Examination> findByDoctorId(Long id);
   List<Examination> findAllByDoctorId(Long id);
}
