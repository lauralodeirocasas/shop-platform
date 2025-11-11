package com.shop.users_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuración global de seguridad.
 * Define qué endpoints son públicos y cómo se gestionan las sesiones.
 */
@Configuration
public class SecurityConfig {

    //Registra un bean (objeto gestionado por Spring) llamado SecurityFilterChain.
    //Sprin pasa por esta cadena de seguridad acda peticion http antes de llegra a su controlador
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // API REST sin CSRF y sin sesiones
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS))

                // Rutas públicas: registro y login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/auth/login").permitAll()
                        .anyRequest().authenticated() // el resto requerirá JWT (cuando lo valides)
                )

                // ¡OJO! Aquí ya NO hay httpBasic ni formLogin
                .build();
    }
}
