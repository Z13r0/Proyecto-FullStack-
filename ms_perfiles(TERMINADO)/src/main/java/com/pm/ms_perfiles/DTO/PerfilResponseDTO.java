package com.pm.ms_perfiles.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PerfilResponseDTO {

    private Long id;
    private Long usuarioId;
    private String nombrePerfil;
    private String urlAvatar;
    private Boolean esInfantil;
    private Boolean activo;
    private LocalDateTime createdAt;
}
