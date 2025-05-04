package com.ben.User.Service.service.impl;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.repo.UserRepo;
import com.ben.User.Service.security.JwtProvider;
import com.ben.User.Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User getUser(String email) {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepo.findById(id).get();

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }

    @Override
    public User upadeUser(User user, Long id) {
        User updatedUser = userRepo.findById(id).get();

        if (updatedUser == null) {
            throw new RuntimeException("User not found");
        }

        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());

        User savedUser = userRepo.save(updatedUser);

        return savedUser;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id).get();

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        userRepo.delete(user);
    }

}
