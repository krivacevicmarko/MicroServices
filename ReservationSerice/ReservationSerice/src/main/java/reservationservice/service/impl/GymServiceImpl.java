package reservationservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reservationservice.domain.Gym;
import reservationservice.dto.GymCreateDto;
import reservationservice.dto.GymDto;
import reservationservice.exception.NotFoundException;
import reservationservice.mapper.GymMapper;
import reservationservice.repository.GymRepository;
import reservationservice.service.GymService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymServiceImpl implements GymService {

    private GymRepository gymRepository;
    private GymMapper gymMapper;

    public GymServiceImpl(GymRepository gymRepository, GymMapper gymMapper) {
        this.gymRepository = gymRepository;
        this.gymMapper = gymMapper;
    }

    @Override
    public Page<GymDto> findAll(Pageable pageable) {
        return gymRepository.findAll(pageable)
                .map(gymMapper::gymToGymDto);
    }

    @Override
    public GymDto findbyId(Integer id) {
        return gymRepository.findById(id)
                .map(gymMapper::gymToGymDto)
                .orElseThrow(()->new NotFoundException(String.format("Gym with id: %d does not exists.", id)));
    }


    @Override
    public GymDto add(GymCreateDto gymCreateDto) {
        Gym gym = gymMapper.gymCreateDtoToGym(gymCreateDto);
        gymRepository.save(gym);
        return gymMapper.gymToGymDto(gym);

    }

    @Override
    public List<GymDto> getAllTrainingsSortedByPocetak() {
        List<Gym> allGyms = gymRepository.findAll();
        List<GymDto> pomocna = allGyms.stream()
                .map(gymMapper::gymToGymDto)
                .collect(Collectors.toList());

        // Sort the trainings by the 'pocetak' property
        Collections.sort(pomocna, Comparator.comparing(gymDto->gymDto.getTraining().getPocetak()));

        return pomocna;
    }

    @Override
    public void editGymProfile(Integer gymId, String name, String description, int numberOfPersonalTrainers) {

        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(()->new NotFoundException("Gym not found"));

        gym.setId(gymId);
        gym.setName(name);
        gym.setShortDescription(description);
        gym.setNumberOfPersonalTrainers(numberOfPersonalTrainers);
        gymRepository.save(gym);
    }


    @Override
    public void deleteById(Integer id) {
       gymRepository.deleteById(id);
    }



}
