package reservationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reservationservice.domain.Tip;
import reservationservice.domain.TrainingType;
import reservationservice.dto.TrainingCreateDto;
import reservationservice.dto.TrainingDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TrainingService {

    Page<TrainingDto> findAll(Pageable pageable);
    TrainingDto findbyId(Integer id);
    List<TrainingDto> findByDate(LocalDate localDate);
    List<TrainingDto> getAllTrainingsSortedByPocetak();
    List<TrainingDto> findByTrainingType(Tip tip);
    List<TrainingDto> findByTrener(String trener);
    List<TrainingDto> findByDayOfTheWeek(String dan);
    TrainingDto add(TrainingCreateDto trainingCreateDto);
    void deleteById(Integer id);


}
