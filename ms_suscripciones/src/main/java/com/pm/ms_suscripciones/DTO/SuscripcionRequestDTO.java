package com.pm.ms_suscripciones.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SuscripcionRequestDTO {

    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;

    @NotBlank(message = "El planId es obligatorio")
    private String planId;           // BASIC, PREMIUM, FAMILIAR, etc.

    @Positive(message = "El monto debe ser mayor a 0")
    private Double monto;

    private LocalDate fechaInicio;

}
