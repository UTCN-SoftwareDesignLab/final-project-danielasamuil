package com.example.finalprojectdanielasamuil.user;

import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.UserDto;
import com.example.finalprojectdanielasamuil.repository.ClassSubscriptionRepository;
import com.example.finalprojectdanielasamuil.repository.FitnessClassRepository;
import com.example.finalprojectdanielasamuil.repository.UserRepository;
import com.example.finalprojectdanielasamuil.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassSubscriptionRepository classSubscriptionRepository;

    @Autowired
    private FitnessClassRepository fitnessClassRepository;

    @BeforeEach
    public void setup() {
        classSubscriptionRepository.deleteAll();
        fitnessClassRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAll() {
        int nrUsers = 10;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < nrUsers; i++) {
            User user = User.builder()
                    .username("User " + i)
                    .password(UUID.randomUUID().toString())
                    .email("user" + i + "@gmail.com")
                    .amountOfMoney(0)
                    .isLoyal(false)
                    .nrOfSubscriptionsSoFar(0)
                    .build();
            users.add(user);
            userRepository.save(user);
        }

        List<UserDto> userMinimalDTOS = userService.allUsersMinimal();

        for (int i = 0; i < nrUsers; i++) {
            assertEquals(users.get(i).getId(), userMinimalDTOS.get(i).getId());
            assertEquals(users.get(i).getUsername(), userMinimalDTOS.get(i).getName());
        }
    }

    @Test
    void updateAll() {
        int nrUsers = 10;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < nrUsers; i++) {
            User user = User.builder()
                    .username("User " + i)
                    .password(UUID.randomUUID().toString())
                    .email("user" + i + "@gmail.com")
                    .amountOfMoney(0)
                    .isLoyal(false)
                    .nrOfSubscriptionsSoFar(0)
                    .build();
            users.add(user);

            user.setEmail("new_user" + i +"@gmail.com");
            userRepository.save(user);
        }

        List<UserDto> userMinimalDTOS = userService.allUsersMinimal();

        for (int i = 0; i < nrUsers; i++) {
            assertEquals(users.get(i).getId(), userMinimalDTOS.get(i).getId());
            assertEquals(users.get(i).getUsername(), userMinimalDTOS.get(i).getName());
        }
    }

    @Test
    void createAll() {
        int nrUsers = 10;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < nrUsers; i++) {
            User user = User.builder()
                    .username("User " + i)
                    .password(UUID.randomUUID().toString())
                    .email("user" + i + "@gmail.com")
                    .amountOfMoney(i)
                    .isLoyal(false)
                    .nrOfSubscriptionsSoFar(i)
                    .build();
            users.add(user);
            userRepository.save(user);
        }

        List<UserDto> userMinimalDTOS = userService.allUsersMinimal();

        for (int i = 0; i < nrUsers; i++) {
            assertEquals(users.get(i).getId(), userMinimalDTOS.get(i).getId());
            assertEquals(users.get(i).getUsername(), userMinimalDTOS.get(i).getName());
        }
    }

    @Test
    void deleteAll() {
        userRepository.deleteAll();

        List<UserDto> all = userService.allUsersMinimal();

        assertEquals(0, all.size());
    }

}
