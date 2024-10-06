package reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reservationservice.domain.TrainingType;

import java.util.Optional;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {
    Optional<TrainingType> findById(Integer id);

}
