package com.example.finalprojectdanielasamuil.mapper;

import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FitnessClassMapper {

    FitnessClassDto toDto(FitnessClass fitnessClass);

    FitnessClass fromDto(FitnessClassDto fitnessClassDto);
}
