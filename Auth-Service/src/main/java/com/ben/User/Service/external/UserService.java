package com.ben.User.Service.external;

import com.ben.User.Service.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER", url = "http://localhost:8082/api/users/")
public interface UserService {

    @PostMapping("/create")
    public User createUser(@RequestBody User user);

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id);

    @PutMapping("/update")
    public User updateUser(@RequestBody User user);
}
