package com.pm.ms_peliculas.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDTO {

    private Long id;

    @NotBlank(message = "El titulo no puede estar vacio.")
    private String titulo;

    private String sinopsis;

    @Min(value = 1, message = "La duración debe ser mayor a 0")
    private Integer duracionMinutos;

    @NotBlank(message = "La clasificación de edad no puede estar vacía")
    private String clasificacionEdad;
}