package com.pm.ms_peliculas.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String pais;
}