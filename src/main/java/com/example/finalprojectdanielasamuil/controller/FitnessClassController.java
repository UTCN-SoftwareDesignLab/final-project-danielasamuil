package com.example.finalprojectdanielasamuil.controller;

import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.service.FitnessClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.finalprojectdanielasamuil.UrlMapping.ENTITY;
import static com.example.finalprojectdanielasamuil.UrlMapping.FITNESS_CLASSES;

@RestController
@RequestMapping(FITNESS_CLASSES)
@RequiredArgsConstructor
public class FitnessClassController {

    private FitnessClassService fitnessClassService;

    @GetMapping()
    public List<FitnessClassDto> findAll() {
        return fitnessClassService.findAll();
    }

    @PostMapping()
    public FitnessClassDto create(@RequestBody FitnessClassDto fitnessClassDto) {
        return fitnessClassService.create(fitnessClassDto);
    }

    @PutMapping(ENTITY)
    public FitnessClassDto edit(@PathVariable Integer id, @RequestBody FitnessClassDto fitnessClassDto) {
        return fitnessClassService.update(id, fitnessClassDto);
    }

    @DeleteMapping()
    public void deleteAll() {
        fitnessClassService.deleteAll();
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Integer id) {
        fitnessClassService.delete(id);
    }
}
