package com.pm.ms_usuarios.Configuracion;

import com.pm.ms_usuarios.Model.Usuario;
import com.pm.ms_usuarios.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository) {

        return args -> {

            if (usuarioRepository.count() == 0) {

                Usuario usuario1 = new Usuario(
                        null,
                        "Juan García López",
                        "juan@gmail.com",
                        "contrasena123",
                        "Calle Pepito 123",
                        "Colombia"
                );

                Usuario usuario2 = new Usuario(
                        null,
                        "Eduardo Martínez",
                        "eduardo@gmail.com",
                        "contrasena456",
                        "Calle Antonio 456",
                        "México"
                );

                usuarioRepository.saveAll(List.of(usuario1, usuario2));
                System.out.println(">> DB: Usuarios de prueba cargados correctamente.");
            } else {
                System.out.println(">> DB: Ya existen usuarios. No se insertaron datos.");
            }
        };
    }

}
