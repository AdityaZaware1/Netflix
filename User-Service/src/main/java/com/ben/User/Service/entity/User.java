package com.ben.User.Service.entity;

import com.ben.User.Service.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

    @Email
    private String email;

    private Boolean subscribed = false;

    @Enumerated(EnumType.STRING)
    private Role role;
}
