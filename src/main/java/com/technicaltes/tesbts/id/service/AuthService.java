package com.technicaltes.tesbts.id.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.technicaltes.tesbts.id.config.JwtService;
import com.technicaltes.tesbts.id.dto.AuthRequest;
import com.technicaltes.tesbts.id.dto.AuthResponse;
import com.technicaltes.tesbts.id.dto.RegisterRequest;
import com.technicaltes.tesbts.id.entity.User;
import com.technicaltes.tesbts.id.exception.ResourceNotFoundException;
import com.technicaltes.tesbts.id.repository.UserRepo;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    // REGISTER
    public String register(RegisterRequest request) {

        // cek password confirmation
        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            throw new RuntimeException("Password confirmation does not match");
        }

        // cek username sudah ada atau belum
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }

    // LOGIN
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // cek password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
