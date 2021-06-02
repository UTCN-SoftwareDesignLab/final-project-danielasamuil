package com.example.finalprojectdanielasamuil.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassSubscriptionDto {

    private Integer id;
    private Integer fitnessClassId;
    private Integer customerId;
}
