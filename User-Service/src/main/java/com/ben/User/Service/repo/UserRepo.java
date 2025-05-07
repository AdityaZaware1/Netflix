package com.ben.User.Service.repo;

import com.ben.User.Service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User getUserById(Long id);
}
