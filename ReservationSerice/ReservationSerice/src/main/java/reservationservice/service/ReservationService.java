package reservationservice.service;

import reservationservice.dto.DecrementTrainingCapacityDto;
import reservationservice.dto.ReservationCreateDto;
import reservationservice.dto.ReservationDto;

public interface ReservationService {
    void addReservation(ReservationCreateDto reservationCreateDto);
    void deleteReservation(Integer id);
    ReservationDto findById(Integer id);

}
