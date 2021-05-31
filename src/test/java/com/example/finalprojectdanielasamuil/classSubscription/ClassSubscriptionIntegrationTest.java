package com.example.finalprojectdanielasamuil.classSubscription;

import com.example.finalprojectdanielasamuil.TestCreationFactory;
import com.example.finalprojectdanielasamuil.model.ClassSubscription;
import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.repository.ClassSubscriptionRepository;
import com.example.finalprojectdanielasamuil.repository.FitnessClassRepository;
import com.example.finalprojectdanielasamuil.repository.UserRepository;
import com.example.finalprojectdanielasamuil.service.ClassSubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClassSubscriptionIntegrationTest {
    @Autowired
    private ClassSubscriptionService classSubscriptionService;

    @Autowired
    private ClassSubscriptionRepository classSubscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FitnessClassRepository fitnessClassRepository;

    @BeforeEach
    public void setup() {
        classSubscriptionRepository.deleteAll();
        fitnessClassRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void createAll() {

        int nr = 10;
        List<ClassSubscription> classSubscriptions = new ArrayList<>();
        for (int i = 0; i < nr; i++) {
            User user = User.builder()
                    .username("User " + i)
                    .password(UUID.randomUUID().toString())
                    .email("user" + i + "@gmail.com")
                    .amountOfMoney(0)
                    .isLoyal(false)
                    .nrOfSubscriptionsSoFar(5)
                    .build();
            userRepository.save(user);

            FitnessClass fitnessClass = FitnessClass.builder()
                    .name("Name")
                    .price(10)
                    .trainer(user)
                    .build();

            fitnessClassRepository.save(fitnessClass);

            ClassSubscription classSubscription = ClassSubscription.builder()
                    .fitnessClass(fitnessClass)
                    .customer(user)
                    .build();

            classSubscriptions.add(classSubscription);

            classSubscriptionRepository.save(classSubscription);
        }

        List<ClassSubscriptionDto> classSubscriptionDtos = classSubscriptionService.findAll();

        for (int i = 0; i < nr; i++) {
            assertEquals(classSubscriptions.get(i).getId(), classSubscriptionDtos.get(i).getId());}
    }

    @Test
    void deleteAll() {
        classSubscriptionRepository.deleteAll();

        List<ClassSubscriptionDto> all = classSubscriptionService.findAll();

        assertEquals(0, all.size());
    }

}
