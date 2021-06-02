package com.example.finalprojectdanielasamuil.service;

import com.example.finalprojectdanielasamuil.mapper.FitnessClassMapper;
import com.example.finalprojectdanielasamuil.model.ClassSubscription;
import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.repository.FitnessClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FitnessClassService {

    private final FitnessClassRepository fitnessClassRepository;

    private final UserService userService;

    private final FitnessClassMapper fitnessClassMapper;

    public FitnessClass findById(Integer id) {
        return fitnessClassRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fitness class not found: " + id));
    }

    public List<FitnessClassDto> findAll() {

        List<FitnessClassDto> fitnessClassDtos = new ArrayList<>();

        List<FitnessClass> fitnessClasses = fitnessClassRepository.findAll();

        for(FitnessClass f: fitnessClasses){
            FitnessClassDto fitnessClassDto = FitnessClassDto.builder()
                    .name(f.getName())
                    .trainerId(f.getTrainer().getId())
                    .price(f.getPrice())
                    .id(f.getId())
                    .build();
            fitnessClassDtos.add(fitnessClassDto);
        }

        return fitnessClassDtos;
    }

    public void delete(int id) {

        fitnessClassRepository.deleteById(id);
    }

    public void deleteAll() {

        fitnessClassRepository.deleteAll();
    }

    public FitnessClassDto create(FitnessClassDto fitnessClassDto) {

        User trainer = userService.findById(fitnessClassDto.getTrainerId());

        FitnessClass fitnessClass = fitnessClassMapper.fromDto(fitnessClassDto);

        fitnessClass.setName(fitnessClassDto.getName());
        fitnessClass.setTrainer(trainer);
        fitnessClass.setPrice(fitnessClassDto.getPrice());

        return fitnessClassMapper.toDto(fitnessClassRepository.save(fitnessClass));
    }

    public FitnessClassDto update(Integer id, FitnessClassDto fitnessClassDto) {

        FitnessClass fitnessClass = findById(id);

        User trainer = userService.findById(fitnessClassDto.getTrainerId());

        fitnessClass.setName(fitnessClassDto.getName());
        fitnessClass.setTrainer(trainer);
        fitnessClass.setPrice(fitnessClassDto.getPrice());

        return fitnessClassMapper.toDto(
                fitnessClassRepository.save(fitnessClass)
        );
    }
}
