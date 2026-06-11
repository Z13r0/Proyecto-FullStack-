package com.pm.ms_usuarios.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 10, max = 100, message = "El nombre debe tener entre 10 y 100 caracteres")
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez García")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    @Schema(description = "Email único del usuario", example = "juan@example.com")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 65, message = "La contraseña debe tener entre 8 y 65 caracteres")
    @Schema(description = "Contraseña del usuario (será encriptada)", example = "MiContraseña123")
    private String contrasena;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 8, max = 400, message = "La dirección debe tener entre 8 y 400 caracteres")
    @Schema(description = "Dirección del usuario", example = "Calle Principal 123, Apartamento 4B")
    private String direccion;

    @NotBlank(message = "El país no puede estar vacío")
    @Size(min = 2, max = 70, message = "El país debe tener entre 2 y 70 caracteres")
    @Schema(description = "País del usuario", example = "España")
    private String pais;

}