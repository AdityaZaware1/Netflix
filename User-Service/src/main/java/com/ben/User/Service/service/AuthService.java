package com.ben.User.Service.service;

import com.ben.User.Service.entity.User;

public interface AuthService {

    public User registerUser(User user);

    public User signInUser(User user);
}
