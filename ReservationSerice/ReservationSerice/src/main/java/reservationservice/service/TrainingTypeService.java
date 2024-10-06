package reservationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reservationservice.dto.TrainingTypeCreateDto;
import reservationservice.dto.TrainingTypeDto;


public interface TrainingTypeService {

    Page<TrainingTypeDto> findAll(Pageable pageable);
    TrainingTypeDto findbyId(Integer id);
    TrainingTypeDto add(TrainingTypeCreateDto trainingTypeCreateDto);
    void deleteById(Integer id);



}
