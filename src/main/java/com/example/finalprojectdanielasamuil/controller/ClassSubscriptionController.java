package com.example.finalprojectdanielasamuil.controller;

import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.service.ClassSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.finalprojectdanielasamuil.UrlMapping.ENTITY;
import static com.example.finalprojectdanielasamuil.UrlMapping.SUBSCRIPTIONS;

@RestController
@RequestMapping(SUBSCRIPTIONS)
@RequiredArgsConstructor
public class ClassSubscriptionController {

    private ClassSubscriptionService classSubscriptionService;

    @GetMapping()
    public List<ClassSubscriptionDto> findAll() {
        return classSubscriptionService.findAll();
    }

    @PostMapping()
    public Boolean create(@RequestBody ClassSubscriptionDto classSubscriptionDto) {
        return classSubscriptionService.create(classSubscriptionDto);
    }

    @DeleteMapping()
    public void deleteAll() {
        classSubscriptionService.deleteAll();
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Integer id) {
        classSubscriptionService.delete(id);
    }
}
