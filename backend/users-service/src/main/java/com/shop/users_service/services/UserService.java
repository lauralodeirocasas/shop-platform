package com.shop.users_service.services;

import com.shop.users_service.dtos.UserCreateDTO;
import com.shop.users_service.models.User;
import com.shop.users_service.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // para cifrar contraseñas
    }

    public User registerUser(UserCreateDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        User user = new User();
        user.setEmail(dto.email());
        user.setPasswordHash(passwordEncoder.encode(dto.password())); // ciframos la contraseña
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmailVerified(false);
        user.setEmailVerifiedAt(null);

        return userRepository.save(user);
    }
}
