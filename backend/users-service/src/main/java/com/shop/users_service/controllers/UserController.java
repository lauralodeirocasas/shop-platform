package com.shop.users_service.controllers;

import com.shop.users_service.dtos.UserCreateDTO;
import com.shop.users_service.models.User;
import com.shop.users_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 👇 Inyectamos el servicio (Spring lo hace automáticamente)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     * Se llama con un POST a /api/users/register
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserCreateDTO dto) {
        User user = userService.registerUser(dto);
        return ResponseEntity.ok(user);
    }
}
