package com.example.finalprojectdanielasamuil.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FitnessClassDto {

    private Integer id;
    private String name;
    private Integer price;
    private Integer trainerId;
}
