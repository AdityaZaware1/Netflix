package com.ben.User.Service.service.impl;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.repo.UserRepo;
import com.ben.User.Service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public User registerUser(User user) {

        User newUser = userRepo.findByEmail(user.getEmail());

        if (newUser != null) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    @Override
    public User signInUser(User user) {
        return null;
    }
}
