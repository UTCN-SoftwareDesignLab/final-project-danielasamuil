package com.example.finalprojectdanielasamuil.mapper;

import com.example.finalprojectdanielasamuil.model.ClassSubscription;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassSubscriptionMapper {

    ClassSubscriptionDto toDto(ClassSubscription classSubscription);

    ClassSubscription fromDto(ClassSubscriptionDto classSubscriptionDto);
}
