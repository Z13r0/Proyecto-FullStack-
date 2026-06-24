package com.pm.ms_suscripciones.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Entidad que representa una suscripción")
@Data
@Entity
@Table(name = "Suscripcion")
@AllArgsConstructor
@NoArgsConstructor
public class Suscripcion {
    @Schema(description = "ID autogenerado", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "ID del usuario suscrito", example = "5")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Schema(description = "Plan contratado: BASIC, PREMIUM, FAMILIAR", example = "PREMIUM")
    @Column(nullable = false, length = 50)
    private String planId;

    @Schema(description = "Fecha de inicio de la suscripción", example = "2025-01-01")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de fin de la suscripción")
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Schema(description = "Estado: ACTIVO, CANCELADO, PENDIENTE", example = "ACTIVO")
    @Column(nullable = false, length = 20)
    private String estado;

    @Schema(description = "Monto mensual de la suscripción", example = "15.99")
    private Double monto;
}
