package com.example.demo.controllers;

import com.example.demo.data.repositories.UserRepository;
import com.example.demo.models.User;
import com.example.demo.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WelcomeController welcomeController;

    @PostMapping("/signIn")
    public String signIn(@RequestBody User user) {
        String success = Globals.invalids;
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser != null) {
            if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                success = welcomeController.post(dbUser);
            }
        }
        return success;
    }

}
