package com.example.finalprojectdanielasamuil.fitnessClass;

import com.example.finalprojectdanielasamuil.TestCreationFactory;
import com.example.finalprojectdanielasamuil.model.ClassSubscription;
import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.repository.ClassSubscriptionRepository;
import com.example.finalprojectdanielasamuil.repository.FitnessClassRepository;
import com.example.finalprojectdanielasamuil.repository.UserRepository;
import com.example.finalprojectdanielasamuil.service.FitnessClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FitnessClassServiceIntegrationTest {
    @Autowired
    private FitnessClassService fitnessClassService;

    @Autowired
    private FitnessClassRepository fitnessClassRepository;

    @Autowired
    private ClassSubscriptionRepository classSubscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        classSubscriptionRepository.deleteAll();
        fitnessClassRepository.deleteAll();

    }

    @Test
    void findAll() {
        List<FitnessClass> fitnessClasses = TestCreationFactory.listOf(FitnessClass.class);

        for (FitnessClass fitnessClass : fitnessClasses) {
            fitnessClass.setTrainer(userRepository.save(fitnessClass.getTrainer()));
        }

        fitnessClassRepository.saveAll(fitnessClasses);

        List<FitnessClassDto> fitnessClassDtos = fitnessClassService.findAll();

        int i = 0;

        for (FitnessClass fitnessClass : fitnessClasses) {
            assertEquals(fitnessClass.getPrice(), fitnessClassDtos.get(i).getPrice());
            i++;
        }
    }

    @Test
    void createAll() {

        int nr = 10;
        List<FitnessClass> fitnessClasses1 = new ArrayList<>();
        for (int i = 0; i < nr; i++) {
            User user = User.builder()
                    .username("User " + i + i)
                    .password(UUID.randomUUID().toString())
                    .email("user" + i + i + "@gmail.com")
                    .amountOfMoney(100)
                    .isLoyal(false)
                    .nrOfSubscriptionsSoFar(2)
                    .build();
            userRepository.save(user);

            FitnessClass fitnessClass = FitnessClass.builder()
                    .name("Name " + i)
                    .price(10)
                    .trainer(user)
                    .build();
            fitnessClasses1.add(fitnessClass);

            fitnessClassRepository.save(fitnessClass);
        }

        List<FitnessClassDto> fitnessClassDtos = fitnessClassService.findAll();

        for (int i = 0; i < nr; i++) {
            assertEquals(fitnessClasses1.get(i).getId(), fitnessClassDtos.get(i).getId());
            assertEquals(fitnessClasses1.get(i).getName(), fitnessClassDtos.get(i).getName());
        }
    }

    @Test
    void deleteAll() {
        fitnessClassRepository.deleteAll();

        List<FitnessClassDto> all = fitnessClassService.findAll();

        assertEquals(0, all.size());
    }

    @Test
    void update() {

        User user = User.builder()
                .username("User ")
                .password(UUID.randomUUID().toString())
                .email("user@gmail.com")
                .amountOfMoney(0)
                .isLoyal(false)
                .nrOfSubscriptionsSoFar(0)
                .build();
        userRepository.save(user);

        int nr = 10;
        List<FitnessClass> fitnessClasses1 = new ArrayList<>();
        for (int i = 0; i < nr; i++) {
            FitnessClass fitnessClass = FitnessClass.builder()
                    .name("Name " + i)
                    .price(10)
                    .trainer(user)
                    .build();
            fitnessClasses1.add(fitnessClass);

            fitnessClass.setName("Other name " + i);

            fitnessClassRepository.save(fitnessClass);
        }

        List<FitnessClassDto> fitnessClassDtos = fitnessClassService.findAll();

        for (int i = 0; i < nr; i++) {
            assertEquals(fitnessClasses1.get(i).getId(), fitnessClassDtos.get(i).getId());
            assertEquals(fitnessClasses1.get(i).getName(), fitnessClassDtos.get(i).getName());
        }

    }

}
