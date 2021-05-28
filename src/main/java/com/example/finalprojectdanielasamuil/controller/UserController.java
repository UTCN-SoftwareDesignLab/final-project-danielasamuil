package com.example.finalprojectdanielasamuil.controller;

import com.example.finalprojectdanielasamuil.model.dtos.UserDto;
import com.example.finalprojectdanielasamuil.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.finalprojectdanielasamuil.UrlMapping.*;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping()
    public UserDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }

    @PutMapping(ENTITY)
    public UserDto edit(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @PutMapping(LOYALTY_UPDATES)
    public UserDto updateLoyalty(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.updateLoyalty(id, userDto);
    }

    @DeleteMapping()
    public void deleteAll() {
        userService.deleteAll();
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}
