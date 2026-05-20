package com.pm.ms_usuarios.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 10, max = 100, message = "El nombre debe tener entre 10 y 100 caracteres.")
    private String nombre;

    @Column(unique = true, nullable = false)
    @Size(min = 6, max = 320, message = "El email debe tener entre 6 y 320 caracteres.")
    private String email;

    @Column(nullable = false)
    @Size(min = 10, max = 65, message = "La contraseña debe tener entre 10 y 65 caracteres.")
    private String contrasena;

    @Column(nullable = false)
    @Size(min = 8, max = 400, message = "La dirección debe tener entre 8 y 400 caracteres.")
    private String direccion;

    @Column(nullable = false)
    @Size(min = 1, max = 70, message = "El país debe tener entre 1 y 70 caracteres.")
    private String pais;

}
