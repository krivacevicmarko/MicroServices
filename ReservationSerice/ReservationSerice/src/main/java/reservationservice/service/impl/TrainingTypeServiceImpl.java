package reservationservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reservationservice.domain.TrainingType;
import reservationservice.dto.TrainingTypeCreateDto;
import reservationservice.dto.TrainingTypeDto;
import reservationservice.exception.NotFoundException;
import reservationservice.mapper.TrainingTypeMapper;
import reservationservice.repository.TrainingTypeRepository;
import reservationservice.service.TrainingTypeService;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService {

    private TrainingTypeRepository trainingTypeRepository;
    private TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeServiceImpl(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    @Override
    public Page<TrainingTypeDto> findAll(Pageable pageable) {
        return trainingTypeRepository.findAll(pageable).map(trainingTypeMapper::trainingTypetoTrainingTypeDto);
    }

    @Override
    public TrainingTypeDto findbyId(Integer id) {
        return trainingTypeRepository.findById(id)
                .map(trainingTypeMapper::trainingTypetoTrainingTypeDto)
                .orElseThrow(()->new NotFoundException(String.format("TrainingType with id :%d does not exist",id)));
    }

    @Override
    public TrainingTypeDto add(TrainingTypeCreateDto trainingTypeCreateDto) {
        TrainingType trainingType = trainingTypeMapper.trainingTypeCreateDtoToTrainingType(trainingTypeCreateDto);
        trainingTypeRepository.save(trainingType);
        return trainingTypeMapper.trainingTypetoTrainingTypeDto(trainingType);
    }

    @Override
    public void deleteById(Integer id) {
         trainingTypeRepository.deleteById(id);
    }
}
