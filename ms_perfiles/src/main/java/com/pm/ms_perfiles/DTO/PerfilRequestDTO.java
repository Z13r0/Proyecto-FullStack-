package com.pm.ms_perfiles.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "DTO para crear o actualizar un perfil")
public class PerfilRequestDTO {

    @Schema(description = "ID del usuario al que pertenece el perfil", example = "1", required = true)
    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;

    @Schema(description = "Nombre del perfil", example = "Juan Principal", required = true, minLength = 3, maxLength = 50)
    @NotBlank(message = "El nombre del perfil es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombrePerfil;

    @Schema(description = "URL del avatar del perfil", example = "https://i.imgur.com/avatar.jpg")
    private String urlAvatar;

    @Schema(description = "Indica si el perfil es infantil", example = "false")
    private Boolean esInfantil = false;
}
