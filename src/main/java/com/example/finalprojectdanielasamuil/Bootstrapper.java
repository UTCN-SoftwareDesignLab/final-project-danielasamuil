package com.example.finalprojectdanielasamuil;

import com.example.finalprojectdanielasamuil.model.*;
import com.example.finalprojectdanielasamuil.repository.ClassSubscriptionRepository;
import com.example.finalprojectdanielasamuil.repository.FitnessClassRepository;
import com.example.finalprojectdanielasamuil.repository.RoleRepository;
import com.example.finalprojectdanielasamuil.repository.UserRepository;
import com.example.finalprojectdanielasamuil.security.AuthService;
import com.example.finalprojectdanielasamuil.security.dto.SignupRequest;
import com.example.finalprojectdanielasamuil.service.FitnessClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final FitnessClassRepository fitnessClassRepository;

    private final ClassSubscriptionRepository classSubscriptionRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
            fitnessClassRepository.deleteAll();
            classSubscriptionRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("danielasamuil@email.com")
                    .username("daniela")
                    .password("Password1")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex@email.com")
                    .username("alex")
                    .password("Password2")
                    .roles(Set.of("TRAINER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("secretary@email.com")
                    .username("secretary")
                    .password("Password3")
                    .roles(Set.of("USER"))
                    .build());

            User trainer = User.builder()
                    .email("trainer@email.com")
                    .password("TrainerPassword")
                    .username("trainer")
                    .build();

            Role trainerRole = roleRepository.findByName(ERole.TRAINER)
                    .orElseThrow(() -> new RuntimeException("Cannot find TRAINER role"));
            trainer.setRoles(Set.of(trainerRole));

            userRepository.save(trainer);

            User customer = User.builder()
                    .email("customer@email.com")
                    .password("CustomerPassword")
                    .username("someUser")
                    .nrOfSubscriptionsSoFar(0)
                    .amountOfMoney(100)
                    .isLoyal(false)
                    .build();

            Role customerRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Cannot find USER role"));
            customer.setRoles(Set.of(customerRole));

            userRepository.save(customer);

            FitnessClass fitnessClass = FitnessClass.builder()
                    .trainer(trainer)
                    .price(20)
                    .name("Tabata")
                    .build();

            fitnessClassRepository.save(fitnessClass);

            ClassSubscription classSubscription = ClassSubscription.builder()
                    .fitnessClass(fitnessClass)
                    .customer(customer)
                    .build();

            classSubscriptionRepository.save(classSubscription);
        }
    }
}
