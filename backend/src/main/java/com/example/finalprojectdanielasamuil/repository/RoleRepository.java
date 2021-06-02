package com.example.finalprojectdanielasamuil.repository;


import com.example.finalprojectdanielasamuil.model.ERole;
import com.example.finalprojectdanielasamuil.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole role);
}