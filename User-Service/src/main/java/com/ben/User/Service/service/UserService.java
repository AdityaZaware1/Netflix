package com.ben.User.Service.service;

import com.ben.User.Service.entity.User;

public interface UserService {

    User getUser(String email);

    User getUserById(Long id);

    User upadeUser(User user, Long id);

    void deleteUser(Long id);

    User getUserProfileByJwt(String jwt);
}
