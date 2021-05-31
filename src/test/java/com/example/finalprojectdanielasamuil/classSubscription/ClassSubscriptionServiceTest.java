package com.example.finalprojectdanielasamuil.classSubscription;

import com.example.finalprojectdanielasamuil.TestCreationFactory;
import com.example.finalprojectdanielasamuil.mapper.ClassSubscriptionMapper;
import com.example.finalprojectdanielasamuil.mapper.UserMapper;
import com.example.finalprojectdanielasamuil.model.ClassSubscription;
import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.repository.ClassSubscriptionRepository;
import com.example.finalprojectdanielasamuil.service.ClassSubscriptionService;
import com.example.finalprojectdanielasamuil.service.FitnessClassService;
import com.example.finalprojectdanielasamuil.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

public class ClassSubscriptionServiceTest {
    @InjectMocks
    private ClassSubscriptionService classSubscriptionService;

    @Mock
    private ClassSubscriptionRepository classSubscriptionRepository;

    @Mock
    private ClassSubscriptionMapper classSubscriptionMapper;

    @Mock
    private UserService userService;

    @Mock
    private FitnessClassService fitnessClassService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        classSubscriptionService = new ClassSubscriptionService(classSubscriptionRepository, userService, fitnessClassService, classSubscriptionMapper, userMapper);
    }

    @Test
    void findAll() {
        List<ClassSubscription> classSubscriptions = TestCreationFactory.listOf(ClassSubscription.class);
        when(classSubscriptionRepository.findAll()).thenReturn(classSubscriptions);

        List<ClassSubscriptionDto> all = classSubscriptionService.findAll();
        Assertions.assertEquals(all.size(), classSubscriptions.size());
    }

    @Test
    void createClassSubscription() throws Exception {

        User customer = (User) TestCreationFactory.listOf(User.class).get(0);
        customer.setAmountOfMoney(100);
        customer.setIsLoyal(true);
        FitnessClass fitnessClass = (FitnessClass) TestCreationFactory.listOf(FitnessClass.class).get(0);
        fitnessClass.setPrice(10);

        ClassSubscription classSubscription = (ClassSubscription) TestCreationFactory.listOf(ClassSubscription.class).get(0);
        classSubscription.setFitnessClass(fitnessClass);
        classSubscription.setCustomer(customer);

        ClassSubscriptionDto classSubscriptionDto = ClassSubscriptionDto.builder()
                .id(classSubscription.getId())
                .fitnessClassId(fitnessClass.getId())
                .customerId(customer.getId())
                .build();

        when(classSubscriptionMapper.fromDto(classSubscriptionDto)).thenReturn(classSubscription);
        when(classSubscriptionMapper.toDto(classSubscription)).thenReturn(classSubscriptionDto);
        when(userService.findById(classSubscriptionDto.getCustomerId())).thenReturn(customer);
        when(fitnessClassService.findById(classSubscriptionDto.getFitnessClassId())).thenReturn(fitnessClass);
        when(classSubscriptionRepository.save(classSubscription)).thenReturn(classSubscription);

        Assertions.assertEquals(classSubscriptionService.create(classSubscriptionDto), true);
    }

    @Test
    void deleteAll() {
        List<ClassSubscription> classSubscriptions = TestCreationFactory.listOf(ClassSubscription.class);
        classSubscriptionService.deleteAll();

        List<ClassSubscriptionDto> all = classSubscriptionService.findAll();

        Assertions.assertEquals(0, all.size());
    }

    @Test
    void delete() {
        User customer = (User) TestCreationFactory.listOf(User.class).get(0);
        FitnessClass fitnessClass = (FitnessClass) TestCreationFactory.listOf(FitnessClass.class).get(0);

        ClassSubscription classSubscription = (ClassSubscription) TestCreationFactory.listOf(ClassSubscription.class).get(0);
        classSubscription.setFitnessClass(fitnessClass);
        classSubscription.setCustomer(customer);

        when(classSubscriptionRepository.save(classSubscription)).thenReturn(classSubscription);

        classSubscriptionRepository.delete(classSubscription);

        List<ClassSubscriptionDto> all = classSubscriptionService.findAll();
        Assertions.assertEquals(all.size(), 0);
    }
}
