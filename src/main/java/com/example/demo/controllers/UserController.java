package com.example.demo.controllers;

import com.example.demo.data.repositories.UserRepository;
import com.example.demo.data.repositories.UserRoleRepository;
import com.example.demo.models.User;
import com.example.demo.models.UserRole;
import com.example.demo.utils.enums.UserRoleRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public String post(@RequestHeader(value="authEmail") String authEmail, @RequestBody User user) {
        String success = Integer.toString(-1);
        if (isAuthorized(authEmail, UserRoleRank.ADMIN)) {
            if (userRepository.findByEmail(user.getEmail()) == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setStatus(1);
                success = userRepository.save(user).getId();
            }
        }
        return success;
    }

    @PutMapping("/{id}")
    public User update(@RequestHeader(value="authEmail") String authEmail,
                       @PathVariable String id, @RequestBody User user) {
        if (isAuthorized(authEmail, UserRoleRank.ADMIN)) {
            return userRepository.findById(id)
                    .map(dbUser -> {
                        dbUser.setFirstName(user.getFirstName());
                        dbUser.setLastName(user.getLastName());
                        return userRepository.save(dbUser);
                    })
                    .orElseGet(() -> {
                        user.setId(id);
                        return userRepository.save(user);
                    });
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public int delete(@RequestHeader(value="authEmail") String authEmail,
                      @PathVariable String id) {
        int success = 0;
        if (isAuthorized(authEmail, UserRoleRank.ADMIN)) {
            Optional<User> optional = userRepository.findById(id);
            if (optional.isPresent()) {
                User dbUser = optional.get();
                dbUser.setStatus(0);
                userRepository.save(dbUser);
                success = 1;
            }
        }
        return success;
    }

    @RequestMapping("/{id}")
    public User getOne(@RequestHeader(value="authEmail") String authEmail,
                       @PathVariable String id) {
        if (isAuthorized(authEmail, UserRoleRank.ADMIN)) {
            Optional<User> optional = userRepository.findById(id);
            return optional.orElse(null);
        }
        return null;
    }

    @RequestMapping("/")
    public List<User> get(@RequestHeader(value="authEmail") String authEmail) {
        if (isAuthorized(authEmail, UserRoleRank.ADMIN)) {
            return userRepository.findAll();
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
