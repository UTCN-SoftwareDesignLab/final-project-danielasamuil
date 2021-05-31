package com.example.finalprojectdanielasamuil.fitnessClass;

import com.example.finalprojectdanielasamuil.TestCreationFactory;
import com.example.finalprojectdanielasamuil.mapper.FitnessClassMapper;
import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.repository.FitnessClassRepository;
import com.example.finalprojectdanielasamuil.service.FitnessClassService;
import com.example.finalprojectdanielasamuil.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class FitnessClassServiceTest {

    @InjectMocks
    private FitnessClassService fitnessClassService;

    @Mock
    private FitnessClassRepository fitnessClassRepository;

    @Mock
    private FitnessClassMapper fitnessClassMapper;

    @Mock
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fitnessClassService = new FitnessClassService(fitnessClassRepository, userService, fitnessClassMapper);
    }

    @Test
    void findAll() {
        List<FitnessClass> fitnessClassList = TestCreationFactory.listOf(FitnessClass.class);
        when(fitnessClassRepository.findAll()).thenReturn(fitnessClassList);

        List<FitnessClassDto> all = fitnessClassService.findAll();
        Assertions.assertEquals(all.size(), fitnessClassList.size());
    }

    @Test
    void createFitnessClass() throws Exception {

        User trainer = (User) TestCreationFactory.listOf(User.class).get(0);

        FitnessClass fitnessClass = (FitnessClass) TestCreationFactory.listOf(FitnessClass.class).get(0);
        fitnessClass.setTrainer(trainer);

        FitnessClassDto fitnessClassDto = FitnessClassDto.builder()
                .id(fitnessClass.getId())
                .price(fitnessClass.getPrice())
                .trainerId(trainer.getId())
                .name(fitnessClass.getName())
                .build();

        when(fitnessClassMapper.fromDto(fitnessClassDto)).thenReturn(fitnessClass);
        when(fitnessClassMapper.toDto(fitnessClass)).thenReturn(fitnessClassDto);
        when(userService.findById(fitnessClassDto.getTrainerId())).thenReturn(trainer);
        when(fitnessClassRepository.save(fitnessClass)).thenReturn(fitnessClass);

        Assertions.assertEquals(fitnessClassService.create(fitnessClassDto), fitnessClassDto);
    }

    @Test
    void editConsultation() throws Exception {
        User trainer = (User) TestCreationFactory.listOf(User.class).get(0);

        FitnessClass fitnessClass = (FitnessClass) TestCreationFactory.listOf(FitnessClass.class).get(0);
        fitnessClass.setTrainer(trainer);

        FitnessClassDto fitnessClassDto = FitnessClassDto.builder()
                .id(fitnessClass.getId())
                .price(fitnessClass.getPrice())
                .trainerId(trainer.getId())
                .name(fitnessClass.getName())
                .build();

        when(fitnessClassMapper.fromDto(fitnessClassDto)).thenReturn(fitnessClass);
        when(fitnessClassMapper.toDto(fitnessClass)).thenReturn(fitnessClassDto);
        when(userService.findById(fitnessClassDto.getTrainerId())).thenReturn(trainer);
        when(fitnessClassRepository.save(fitnessClass)).thenReturn(fitnessClass);
        when(fitnessClassRepository.findById(fitnessClass.getId())).thenReturn(Optional.of(fitnessClass));

        Assertions.assertEquals(fitnessClassService.update(fitnessClassDto.getId(), fitnessClassDto), fitnessClassDto);
    }

    @Test
    void deleteAll() {
        List<FitnessClass> fitnessClasses = TestCreationFactory.listOf(FitnessClass.class);
        fitnessClassService.deleteAll();

        List<FitnessClassDto> all = fitnessClassService.findAll();

        Assertions.assertEquals(0, all.size());
    }

    @Test
    void delete() {
        User trainer = (User) TestCreationFactory.listOf(User.class).get(0);

        FitnessClass fitnessClass = (FitnessClass) TestCreationFactory.listOf(FitnessClass.class).get(0);
        fitnessClass.setTrainer(trainer);

        when(fitnessClassRepository.save(fitnessClass)).thenReturn(fitnessClass);

        fitnessClassRepository.delete(fitnessClass);

        List<FitnessClassDto> all = fitnessClassService.findAll();
        Assertions.assertEquals(all.size(), 0);
    }
}
