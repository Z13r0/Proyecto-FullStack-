package com.pm.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de fallback para Circuit Breaker
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, String>> usuariosFallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(Map.of("error", "El servicio de usuarios no está disponible"));
    }

    @GetMapping("/peliculas")
    public ResponseEntity<Map<String, String>> peliculasFallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(Map.of("error", "El servicio de películas no está disponible"));
    }

    @GetMapping("/perfiles")
    public ResponseEntity<Map<String, String>> perfilesFallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(Map.of("error", "El servicio de perfiles no está disponible"));
    }

    @GetMapping("/suscripciones")
    public ResponseEntity<Map<String, String>> suscripcionesFallback() {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(Map.of("error", "El servicio de suscripciones no está disponible"));
    }

}