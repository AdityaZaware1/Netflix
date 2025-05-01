package com.ben.external;

import com.ben.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8081")
public interface UserService {

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id);
}
