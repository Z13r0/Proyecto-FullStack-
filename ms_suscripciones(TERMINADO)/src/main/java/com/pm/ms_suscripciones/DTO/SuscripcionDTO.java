package com.pm.ms_suscripciones.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SuscripcionDTO {
    private Long id;
    private Long usuarioId;
    private String planId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Double monto;
}
