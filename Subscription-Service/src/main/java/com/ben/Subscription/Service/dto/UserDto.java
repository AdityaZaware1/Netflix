package com.ben.Subscription.Service.dto;

import com.ben.Subscription.Service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private Boolean subscribed;
    private Role role;

}
