package com.ben.User.Service.request;

import com.ben.User.Service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String userName;
    private String email;
    private String password;
    private Role role;
}
