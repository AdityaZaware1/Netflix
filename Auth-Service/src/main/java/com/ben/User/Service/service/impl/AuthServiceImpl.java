package com.ben.User.Service.service.impl;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.external.UserService;
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
    private final UserService userService;
    private final UserRepo userRepo;

    @Override
    public User registerUser(JwtRequest jwtRequest) {

        String password = jwtRequest.getPassword();

        User user = new User();
        user.setUsername(jwtRequest.getUserName());
        user.setEmail(jwtRequest.getEmail());
        user.setPassword(jwtRequest.getPassword());
        user.setRole(jwtRequest.getRole());

        userRepo.save(user);

        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setUsername(jwtRequest.getUserName());
        newUser.setEmail(jwtRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(jwtRequest.getRole());

        userService.createUser(newUser);

        return user;
    }

    @Override
    public User signInUser(User user) {
        return null;
    }
}
