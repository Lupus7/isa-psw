package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team47pack.models.Examination;

import java.util.ArrayList;
import java.util.List;

public interface ExaminationRepo extends JpaRepository<Examination,Long> {
    Examination findById(int id);
    @Query(nativeQuery=true, value="SELECT * FROM Examination WHERE patient_id=?1 order by date asc")
    List<Examination> findByPatientId(Long id);
    List<Examination> findAll();
    ArrayList<Examination> findByType(String type);
}
