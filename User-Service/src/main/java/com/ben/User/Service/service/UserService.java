package com.ben.User.Service.service;

import com.ben.User.Service.entity.User;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    User getUserById(Long id);

    User upadeUser(User user, Long id);
}
