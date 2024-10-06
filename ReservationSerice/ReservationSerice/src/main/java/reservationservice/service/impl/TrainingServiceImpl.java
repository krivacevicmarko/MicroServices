package reservationservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reservationservice.domain.Tip;
import reservationservice.domain.Training;
import reservationservice.domain.TrainingType;
import reservationservice.dto.GymDto;
import reservationservice.dto.TrainingCreateDto;
import reservationservice.dto.TrainingDto;
import reservationservice.exception.NotFoundException;
import reservationservice.mapper.TrainingMapper;
import reservationservice.repository.TrainingRepository;
import reservationservice.service.TrainingService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;
    private TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public List<TrainingDto> getAllTrainingsSortedByPocetak() {
        List<Training> allTrainings = trainingRepository.findAll();
        List<TrainingDto> pomocna = allTrainings.stream()
                .map(trainingMapper::trainingToTrainingDto)
                .collect(Collectors.toList());

        // Sort the trainings by the 'pocetak' property
        Collections.sort(pomocna, Comparator.comparing(TrainingDto::getPocetak));

        return pomocna;
    }

    @Override
    public Page<TrainingDto> findAll(Pageable pageable) {
        return trainingRepository.findAll(pageable)
                .map(trainingMapper::trainingToTrainingDto);
    }

    @Override
    public TrainingDto findbyId(Integer id) {
        return trainingRepository.findById(id)
                .map(trainingMapper::trainingToTrainingDto)
                .orElseThrow(()->new NotFoundException(String.format("Training with id: %d does not exists.", id)));
    }

    @Override
    public List<TrainingDto> findByDate(LocalDate localDate) {
        List<Training> optionalTraining = trainingRepository.findByDatum(localDate);
        List<Training> trainings = optionalTraining;
        return trainings.stream()
                .map(trainingMapper::trainingToTrainingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> findByTrainingType(Tip tip) {
        List<Training> optionalTraining = trainingRepository.findByTip(tip);
        List<Training> trainings = optionalTraining;
        return trainings.stream()
                .map(trainingMapper::trainingToTrainingDto)
                .collect(Collectors.toList());
    }

    @Override
public List<TrainingDto> findByTrener(String trener) {
    List<Training> optionalTraining = trainingRepository.findByTrener(trener);
    List<Training> trainings = optionalTraining;
    return trainings.stream()
            .map(trainingMapper::trainingToTrainingDto)
            .collect(Collectors.toList());
}

    @Override
    public List<TrainingDto> findByDayOfTheWeek(String dan) {
        List<Training> optionalDay = trainingRepository.findByDan(dan);
        List<Training> days = optionalDay;
        return days.stream()
                .map(trainingMapper::trainingToTrainingDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingDto add(TrainingCreateDto trainingCreateDto) {
        Training training = trainingMapper.trainingCreateDtoToTraining(trainingCreateDto);
        trainingRepository.save(training);
        return trainingMapper.trainingToTrainingDto(training);
    }

    @Override
    public void deleteById(Integer id) {
       trainingRepository.deleteById(id);
    }


}
