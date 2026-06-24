package com.pm.ms_perfiles.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String direccion;
    private String pais;
}
