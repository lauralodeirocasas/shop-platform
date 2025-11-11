package com.shop.users_service.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(name = "uk_users_email", columnNames = "email"))
public class User {

    /** Identificador único del usuario (UUID evita colisiones y no depende de un contador) */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /** Correo electrónico del usuario, se usa para iniciar sesión (debe ser único) */
    @Column(nullable = false, length = 320)
    private String email;

    /** Contraseña cifrada con BCrypt (nunca se guarda en texto plano) */
    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    /** Nombre del usuario */
    @Column(name = "first_name", length = 100)
    private String firstName;

    /** Apellido del usuario */
    @Column(name = "last_name", length = 100)
    private String lastName;

    /** Indica si el usuario ya verificó su correo electrónico */
    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    /** Fecha y hora en la que el usuario verificó su correo (null si no lo ha hecho) */
    @Column(name = "email_verified_at")
    private Instant emailVerifiedAt;

    /** Normaliza el email antes de guardar o actualizar: quita espacios y pasa a minúsculas */
    @PrePersist
    @PreUpdate
    private void normalize() {
        if (this.email != null) this.email = this.email.trim().toLowerCase();
    }

}
