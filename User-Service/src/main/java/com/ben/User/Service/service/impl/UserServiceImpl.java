package com.ben.User.Service.service.impl;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.repo.UserRepo;
import com.ben.User.Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User createUser(User user) {
//        User newUser = userRepo.getUserById(user.getId());
//
//        if(newUser != null) {
//            throw new RuntimeException("User already exists");
//        }
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getPassword());
//        newUser.setEmail(user.getEmail());
//        newUser.setSubscribed(user.getSubscribed());
//        newUser.setRole(user.getRole());
//
//        User savedUser = userRepo.save(newUser);
//        return savedUser;

        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = userRepo.save(user);
        return updatedUser;
    }

    @Override
    public User getUserById(Long id) {

        User user = userRepo.getUserById(id);
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
        updatedUser.setSubscribed(user.getSubscribed());
        updatedUser.setRole(user.getRole());

        User savedUser = userRepo.save(updatedUser);

        return savedUser;
    }
}
