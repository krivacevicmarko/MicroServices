package reservationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reservationservice.dto.GymCreateDto;
import reservationservice.dto.GymDto;

import java.util.List;

public interface GymService {
    Page<GymDto> findAll(Pageable pageable);
    GymDto findbyId(Integer id);
    GymDto add(GymCreateDto gymCreateDto);
    List<GymDto> getAllTrainingsSortedByPocetak();
    void editGymProfile(Integer gymId, String name, String description, int numberOfPersonalTrainers);
    void deleteById(Integer id);



}
