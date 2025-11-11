package com.shop.users_service.dtos;

import java.time.Instant;
import java.util.UUID;

public record LoginResponseDTO(
        String accessToken,
        Instant expiresAt,
        UUID userId,
        String email,
        String firstName,
        String lastName
) {}
