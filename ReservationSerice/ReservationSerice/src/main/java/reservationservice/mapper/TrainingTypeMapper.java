package reservationservice.mapper;

import org.springframework.stereotype.Component;
import reservationservice.domain.TrainingType;
import reservationservice.dto.TrainingTypeCreateDto;
import reservationservice.dto.TrainingTypeDto;

@Component
public class TrainingTypeMapper {

    public TrainingTypeDto trainingTypetoTrainingTypeDto(TrainingType trainingType){
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto();
        trainingTypeDto.setId(trainingType.getId());
        trainingTypeDto.setName(trainingType.getName());
        trainingTypeDto.setPrice(trainingType.getPrice());
        return trainingTypeDto;
    }

    public TrainingType trainingTypeCreateDtoToTrainingType(TrainingTypeCreateDto trainingTypeCreateDto) {
        TrainingType trainingType = new TrainingType();
        trainingType.setName(trainingTypeCreateDto.getName());
        trainingType.setPrice(trainingTypeCreateDto.getPrice());
        return trainingType;
    }


}
