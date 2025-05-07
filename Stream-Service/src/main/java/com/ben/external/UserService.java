package com.ben.external;

import com.ben.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER", url = "http://localhost:8082/api/users/")
public interface UserService {

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable Long id);
}
