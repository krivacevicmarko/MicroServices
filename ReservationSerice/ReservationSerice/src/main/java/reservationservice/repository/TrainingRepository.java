package reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reservationservice.domain.Tip;
import reservationservice.domain.Training;
import reservationservice.domain.TrainingType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Optional<Training> findById(Integer id);
    List<Training> findByDatum(LocalDate localDate);
    List<Training> findByTip(Tip tip);
    List<Training> findByTrener(String trener);
    List<Training> findByDan(String dan);

}
