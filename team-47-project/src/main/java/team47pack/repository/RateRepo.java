package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team47pack.models.Rate;

public interface RateRepo extends JpaRepository<Rate, Long> {
}
