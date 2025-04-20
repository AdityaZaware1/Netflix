package com.ben.User.Service.controller;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.repo.UserRepo;
import com.ben.User.Service.response.AuthResponse;
import com.ben.User.Service.security.CustomUserDetailsService;
import com.ben.User.Service.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepo userRepo;
    private final CustomUserDetailsService customUserDetailsService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) throws Exception {

        User newUser = userRepo.findByEmail(user.getEmail());

        if (newUser != null) {
            throw new Exception("User already exists");
        }

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());

        User savedUser = userRepo.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), savedUser.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);
        authResponse.setStatus(true);
        authResponse.setMessage("User registered successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signInUser(@RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        User authUser = userRepo.findByEmail(email);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("User login successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }

        if(!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
}
