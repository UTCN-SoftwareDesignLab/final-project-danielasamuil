package com.example.finalprojectdanielasamuil.controller;

import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.service.FitnessClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.finalprojectdanielasamuil.UrlMapping.ENTITY;
import static com.example.finalprojectdanielasamuil.UrlMapping.FITNESS_CLASSES;

@RestController
@RequestMapping(FITNESS_CLASSES)
@RequiredArgsConstructor
public class FitnessClassController {

    private final FitnessClassService fitnessClassService;

    @GetMapping()
    public List<FitnessClassDto> findAll() {
        return fitnessClassService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasRole('TRAINER')")
    public FitnessClassDto create(@RequestBody FitnessClassDto fitnessClassDto) {
        return fitnessClassService.create(fitnessClassDto);
    }

    @PutMapping(ENTITY)
    @PreAuthorize("hasRole('TRAINER')")
    public FitnessClassDto edit(@PathVariable Integer id, @RequestBody FitnessClassDto fitnessClassDto) {
        return fitnessClassService.update(id, fitnessClassDto);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('TRAINER')")
    public void deleteAll() {
        fitnessClassService.deleteAll();
    }

    @DeleteMapping(ENTITY)
    @PreAuthorize("hasRole('TRAINER')")
    public void delete(@PathVariable Integer id) {
        fitnessClassService.delete(id);
    }
}
