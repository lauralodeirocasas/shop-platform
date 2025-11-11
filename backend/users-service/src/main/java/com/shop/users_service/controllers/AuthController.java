package com.shop.users_service.controllers;

import com.shop.users_service.dtos.LoginRequestDTO;
import com.shop.users_service.dtos.LoginResponseDTO;
import com.shop.users_service.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que gestiona las rutas de autenticación (login, registro, etc.).
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint para iniciar sesión.
     * @param request credenciales del usuario (email y password)
     * @return datos básicos del usuario autenticado
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
