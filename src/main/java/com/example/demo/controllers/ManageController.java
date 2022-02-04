package com.example.demo.controllers;

import com.example.demo.data.repositories.UserRoleRepository;
import com.example.demo.models.UserRole;
import com.example.demo.utils.enums.UserRoleRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping("/")
    public String get(@RequestHeader(value="authEmail") String authEmail) {
        if (isAuthorized(authEmail, UserRoleRank.MANAGE)) {
            return new String("Manage...");
        }
        return null;
    }

    public boolean isAuthorized(String email, int scope) {
        List<UserRole> userRoles = userRoleRepository
                .findByEmail(email).stream()
                .filter(ur -> ur.getUserRoleRank() == scope)
                .collect(Collectors.toList());
        return userRoles.size() > 0;
    }

}
