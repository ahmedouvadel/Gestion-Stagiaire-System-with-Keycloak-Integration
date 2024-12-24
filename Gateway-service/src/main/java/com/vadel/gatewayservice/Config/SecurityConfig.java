package com.vadel.gatewayservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Désactiver CSRF
                .authorizeExchange(exchanges -> exchanges
                        // Sécuriser toutes les routes API avec authentification
                        .pathMatchers("/api/stagiaire/**").authenticated()
                        .pathMatchers("/api/project/**").authenticated()
                        .pathMatchers("/api/evaluation/**").authenticated()
                        .pathMatchers("/api/supervisor/**").authenticated()
                        // Autoriser l'accès libre pour Eureka et Actuator
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/eureka/**").permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri("http://localhost:8080/realms/Gestion-Stagiaire/protocol/openid-connect/certs")
                .build();
    }
}
