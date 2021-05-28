package com.example.finalprojectdanielasamuil.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
