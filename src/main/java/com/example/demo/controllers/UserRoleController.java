package com.example.demo.controllers;

import com.example.demo.data.repositories.UserRoleRepository;
import com.example.demo.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userRoles")
public class UserRoleController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping("/")
    public String post(@RequestBody UserRole userRole) {
        String success = Integer.toString(-1);
        userRole.setStatus(1);
        success = userRoleRepository.save(userRole).getId();
        return success;
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable String id) {
        int success = 0;
        Optional<UserRole> optional = userRoleRepository.findById(id);
        if (optional.isPresent()) {
            UserRole dbUserRole = optional.get();
            dbUserRole.setStatus(0);
            userRoleRepository.save(dbUserRole);
            success = 1;
        }
        return success;
    }

    @RequestMapping("/{id}")
    public UserRole getOne(@PathVariable String id) {
        Optional<UserRole> optional = userRoleRepository.findById(id);
        return optional.orElse(null);
    }

    @RequestMapping("/")
    public List<UserRole> get() {
        return userRoleRepository.findAll();
    }
}
