package com.example.finalprojectdanielasamuil.fitnessClass;

import com.example.finalprojectdanielasamuil.BaseControllerTest;
import com.example.finalprojectdanielasamuil.TestCreationFactory;
import com.example.finalprojectdanielasamuil.controller.FitnessClassController;
import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.service.FitnessClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.finalprojectdanielasamuil.TestCreationFactory.*;
import static com.example.finalprojectdanielasamuil.TestCreationFactory.randomInt;
import static com.example.finalprojectdanielasamuil.UrlMapping.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FitnessClassControllerTest extends BaseControllerTest {

    @InjectMocks
    private FitnessClassController controller;

    @Mock
    private FitnessClassService fitnessClassService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new FitnessClassController(fitnessClassService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allFitnessClasses() throws Exception {
        List<FitnessClassDto> fitnessClassDtos = TestCreationFactory.listOf(FitnessClassDto.class);
        when(fitnessClassService.findAll()).thenReturn(fitnessClassDtos);

        ResultActions result = mockMvc.perform(get(FITNESS_CLASSES));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(fitnessClassDtos));
    }

    @Test
    void create() throws Exception {
        FitnessClassDto fitnessClassDto = FitnessClassDto.builder()
                .id(randomInt())
                .name(randomString())
                .price(randomInt())
                .trainerId(randomInt())
                .build();

        when(fitnessClassService.create(fitnessClassDto)).thenReturn(fitnessClassDto);

        ResultActions result = performPostWithRequestBody(FITNESS_CLASSES, fitnessClassDto);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(fitnessClassDto));
    }

    @Test
    void edit() throws Exception {
        int id = randomInt();
        FitnessClassDto fitnessClassDto = FitnessClassDto.builder()
                .name(randomString())
                .price(20)
                .trainerId(randomInt())
                .build();

        when(fitnessClassService.update(id, fitnessClassDto)).thenReturn(fitnessClassDto);

        ResultActions result = performPutWithRequestBodyAndPathVariable(FITNESS_CLASSES + ENTITY, fitnessClassDto, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(fitnessClassDto));
    }

    @Test
    void deleteAll() throws Exception {
        doNothing().when(fitnessClassService).deleteAll();

        ResultActions result = performDelete(FITNESS_CLASSES);
        result.andExpect(status().isOk());
        verify(fitnessClassService, times(1)).deleteAll();
    }

    @Test
    void delete() throws Exception {
        int id = randomInt();
        doNothing().when(fitnessClassService).delete(id);

        ResultActions result = performDeleteWithPathVariable(FITNESS_CLASSES + ENTITY, id);
        result.andExpect(status().isOk());
        verify(fitnessClassService, times(1)).delete(id);
    }
}