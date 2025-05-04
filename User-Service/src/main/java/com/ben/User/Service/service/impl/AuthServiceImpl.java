package com.ben.User.Service.service.impl;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.repo.UserRepo;
import com.ben.User.Service.request.JwtRequest;
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
    public User registerUser(JwtRequest jwtRequest) {

        User user = new User();
        user.setUsername(jwtRequest.getUserName());
        user.setEmail(jwtRequest.getEmail());
        user.setPassword(passwordEncoder.encode(jwtRequest.getPassword()));
        user.setRole(jwtRequest.getRole());

        return userRepo.save(user);
    }

    @Override
    public User signInUser(User user) {
        return null;
    }
}
