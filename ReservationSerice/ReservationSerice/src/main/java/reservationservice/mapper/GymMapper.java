package reservationservice.mapper;

import org.springframework.stereotype.Component;
import reservationservice.domain.Gym;
import reservationservice.dto.GymCreateDto;
import reservationservice.dto.GymDto;
import reservationservice.exception.NotFoundException;
import reservationservice.repository.TrainingRepository;

@Component
public class GymMapper {

    private TrainingRepository trainingRepository;
    private TrainingMapper trainingMapper;

    public GymMapper(TrainingRepository trainingRepository, TrainingMapper trainingMapper){
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    public GymDto gymToGymDto(Gym gym){
        GymDto gymDto = new GymDto();
        gymDto.setId(gym.getId());
        gymDto.setName(gym.getName());
        gymDto.setShortDescription(gym.getShortDescription());
        gymDto.setNumberOfPersonalTrainers(gym.getNumberOfPersonalTrainers());
        gymDto.setTraining(trainingMapper.trainingToTrainingDto(gym.getTraining()));
        return gymDto;
    }

    public Gym gymCreateDtoToGym(GymCreateDto gymCreateDto){
        Gym gym = new Gym();
        gym.setName(gymCreateDto.getName());
        gym.setShortDescription(gymCreateDto.getShortDescription());
        gym.setNumberOfPersonalTrainers(gymCreateDto.getNumberOfPersonalTrainers());
        gym.setTraining(trainingRepository.findById(gymCreateDto.getTrainingId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Movie with id: %d does not exists.", gymCreateDto.getTrainingId()))));
        return gym;
    }


}
