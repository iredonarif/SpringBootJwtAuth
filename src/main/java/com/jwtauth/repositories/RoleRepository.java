package com.jwtauth.repositories;

import com.jwtauth.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
    Boolean existsByName(String name);
}
