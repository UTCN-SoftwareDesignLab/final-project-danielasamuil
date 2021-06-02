package com.example.finalprojectdanielasamuil.model.dtos;

import com.example.finalprojectdanielasamuil.model.Role;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Boolean loyalty;
    private Integer amountOfMoney;
    private Integer nrOfSubscriptionsSoFar;
}
