package com.ben.User.Service.controller;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.repo.UserRepo;
import com.ben.User.Service.response.AuthResponse;
import com.ben.User.Service.security.CustomUserDetailsService;
import com.ben.User.Service.security.JwtProvider;
import com.ben.User.Service.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final JwtProvider jwtProvider;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) throws Exception {

        User newUser = userRepo.findByEmail(user.getEmail());

        if (newUser != null) {
            throw new Exception("User already exists");
        }

        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        String pass = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(pass);
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

    @GetMapping("/validate")
    public String validate(@RequestHeader("Authorization") String token) {
        jwtProvider.validateToken(token);
        return "Token is valid";
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
