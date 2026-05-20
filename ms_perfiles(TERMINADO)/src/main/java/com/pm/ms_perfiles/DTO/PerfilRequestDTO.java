package com.pm.ms_perfiles.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerfilRequestDTO {
    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El nombre del perfil es obligatorio")
    private String nombrePerfil;

    private String urlAvatar;

    private Boolean esInfantil = false;
}
