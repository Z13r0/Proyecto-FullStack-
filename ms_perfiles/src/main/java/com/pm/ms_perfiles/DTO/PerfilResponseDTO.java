package com.pm.ms_perfiles.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "DTO de respuesta para perfiles")
public class PerfilResponseDTO extends RepresentationModel<PerfilResponseDTO> {

    @Schema(description = "ID del perfil", example = "1")
    private Long id;

    @Schema(description = "ID del usuario", example = "1")
    private Long usuarioId;

    @Schema(description = "Nombre del perfil", example = "Juan Gaming")
    private String nombrePerfil;

    @Schema(description = "URL del avatar", example = "https://example.com/avatar.jpg")
    private String urlAvatar;

    @Schema(description = "Indica si el perfil es infantil", example = "false")
    private Boolean esInfantil;

    @Schema(description = "Indica si el perfil está activo", example = "true")
    private Boolean activo;

    @Schema(description = "Fecha de creación", example = "2026-06-08T18:00:00")
    private LocalDateTime createdAt;
}
