package com.mco1.TripLang.controller;

import com.mco1.TripLang.ApiResponse;
import com.mco1.TripLang.dto.RegisterRequestDTO;
import com.mco1.TripLang.dto.UserResponseDTO;
import com.mco1.TripLang.entity.User;
import com.mco1.TripLang.entity.Wallet;
import com.mco1.TripLang.repository.UserRepository;
import com.mco1.TripLang.repository.WalletRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> register(@Valid @RequestBody RegisterRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(409, "Email already used"));
        }

        // 1. Create User (credentials)
        User user = new User(
                request.getDisplayName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        userRepository.save(user);

        // 2. Create wallet (1 user = 1 wallet)
        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);

        // Build a safe response without the password hash.
        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getDisplayName(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<UserResponseDTO>(201, "User registered successfully", response));
    }
}
