package com.example.demo.data.repositories;

import com.example.demo.models.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {
    List<UserRole> findByEmail(@Param("email") String email);
}
