package com.example.finalprojectdanielasamuil.service;

import com.example.finalprojectdanielasamuil.mapper.UserMapper;
import com.example.finalprojectdanielasamuil.model.Role;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.UserDto;
import com.example.finalprojectdanielasamuil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public List<UserDto> findAll() {

        return userRepository.findAll().stream().map(
                userMapper::toDto)
                .collect(toList());

    }

    public void delete(int id) {

        userRepository.deleteById(id);
    }

    public void deleteAll() {

        userRepository.deleteAll();
    }

    public UserDto create(UserDto userDto) {

        Set<Role> roles = new HashSet<>();

        User user = userMapper.fromDto(userDto);

        user.setUsername(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRoles(roles);
        user.setIsLoyal(false);
        user.setAmountOfMoney(userDto.getAmountOfMoney());
        user.setNrOfSubscriptionsSoFar(0);

        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto update(Integer id, UserDto userDto) {

        User user = findById(id);

        if(user.getNrOfSubscriptionsSoFar()>=5)
            user.setIsLoyal(true);
        else
            user.setIsLoyal(userDto.getLoyalty());

        user.setUsername(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setAmountOfMoney(userDto.getAmountOfMoney());
        user.setNrOfSubscriptionsSoFar(userDto.getNrOfSubscriptionsSoFar());

        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    public List<UserDto> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto)
                .collect(toList());
    }
}
