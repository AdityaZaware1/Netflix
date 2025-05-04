package com.ben.User.Service.service;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.request.JwtRequest;

public interface AuthService {

    public User registerUser(JwtRequest jwtRequest);

    public User signInUser(User user);
}
