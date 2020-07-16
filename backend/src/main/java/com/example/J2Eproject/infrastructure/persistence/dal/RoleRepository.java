package com.example.J2Eproject.infrastructure.persistence.dal;

import com.example.J2Eproject.infrastructure.persistence.entities.Role;
import com.example.J2Eproject.domain.models.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
