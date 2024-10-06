package reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reservationservice.domain.Gym;

import java.util.Optional;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {
     Optional<Gym> findByName(String name);

}
