package com.ben.Subscription.Service.external;

import com.ben.Subscription.Service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER", url = "${user.service.url}")
public interface UserService {

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable Long id);
}
