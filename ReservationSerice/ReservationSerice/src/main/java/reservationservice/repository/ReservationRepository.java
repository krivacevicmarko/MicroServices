package reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reservationservice.domain.Reservation;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findByUserIdAndGymId(Integer userId, Integer gymId);

    void deleteById(Integer integer);
}
