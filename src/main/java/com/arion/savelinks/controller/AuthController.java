package com.arion.savelinks.controller;

import com.arion.savelinks.DTO.LoginRequest;
import com.arion.savelinks.DTO.LoginResponse;
import com.arion.savelinks.DTO.RegisterRequest;
import com.arion.savelinks.entity.User;
import com.arion.savelinks.repository.UserDetailsRepository;
import com.arion.savelinks.util.JWTUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        log.info("hit");
        Authentication authenticate = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = (User) authenticate.getPrincipal();

        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token, user.getUsername(), user.getEmail());
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER"); // You can change this if needed

        userRepo.save(user);
        return "User registered successfully";
    }
}
