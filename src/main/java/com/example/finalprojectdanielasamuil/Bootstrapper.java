package com.example.finalprojectdanielasamuil;

import com.example.finalprojectdanielasamuil.model.ERole;
import com.example.finalprojectdanielasamuil.model.Role;
import com.example.finalprojectdanielasamuil.repository.RoleRepository;
import com.example.finalprojectdanielasamuil.repository.UserRepository;
import com.example.finalprojectdanielasamuil.security.AuthService;
import com.example.finalprojectdanielasamuil.security.dto.SignupRequest;
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

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
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
        }
    }
}
