package reservationservice.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import reservationservice.domain.Training;
import reservationservice.dto.TrainingCreateDto;
import reservationservice.dto.TrainingDto;
import reservationservice.exception.NotFoundException;
import reservationservice.repository.TrainingTypeRepository;

@Component
public class TrainingMapper {

    private TrainingTypeRepository trainingTypeRepository;
    private TrainingTypeMapper trainingTypeMapper;

    public TrainingMapper(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper){
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    public TrainingDto trainingToTrainingDto(Training training){
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setDatum(training.getDatum());
        trainingDto.setDan(training.getDan());
        trainingDto.setPocetak(training.getPocetak());
        trainingDto.setKraj(training.getKraj());
        trainingDto.setTrener(training.getTrener());
        trainingDto.setCapacity(training.getCapacity());
        trainingDto.setTrainingTypeDto(trainingTypeMapper.trainingTypetoTrainingTypeDto(training.getTrainingType()));
        trainingDto.setTip(training.getTip());
        return trainingDto;
    }

    public Training trainingCreateDtoToTraining(TrainingCreateDto trainingCreateDto){
        Training training = new Training();
        training.setTrainingType(trainingTypeRepository.findById(trainingCreateDto.getTrainingTypeId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Movie with id: %d does not exists.", trainingCreateDto.getTrainingTypeId()))));
        training.setDatum(trainingCreateDto.getDatum());
        training.setDan(trainingCreateDto.getDan());
        training.setPocetak(trainingCreateDto.getPocetak());
        training.setKraj(trainingCreateDto.getKraj());
        training.setTrener(trainingCreateDto.getTrener());
        training.setCapacity(trainingCreateDto.getCapacity());
        training.setTip(trainingCreateDto.getTip());
        return  training;
    }

}
