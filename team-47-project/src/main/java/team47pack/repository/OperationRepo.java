package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.NextProcedure;
import team47pack.models.Operation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OperationRepo extends JpaRepository<Operation, Long> {
    Optional<Operation> findById(Long id);
    List<Operation> findAllByOperationtypeId(Long Id);
    List<Operation> findAllByDoctorsIdAndApproved(Long Id, Boolean b);
    List<Operation> findAllByRoomIdAndApproved(Long Id, Boolean b);

    Operation findByDateAndTimeAndApproved(Date dateConv, int time, boolean b);

    List<Operation> findAllByApproved(boolean b);
}
