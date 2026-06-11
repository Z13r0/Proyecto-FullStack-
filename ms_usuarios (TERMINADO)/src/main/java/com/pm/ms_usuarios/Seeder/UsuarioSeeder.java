package com.pm.ms_usuarios.Seeder;

import com.pm.ms_usuarios.Model.Usuario;
import com.pm.ms_usuarios.Repository.UsuarioRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Seeder para generar datos de prueba con DataFaker
 */
@Configuration
public class UsuarioSeeder {

    @Bean
    public CommandLineRunner initUsuarios(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Faker faker = new Faker();

                for (int i = 0; i < 10; i++) {
                    Usuario usuario = Usuario.builder()
                        .nombre(faker.name().fullName())
                        .email(faker.internet().emailAddress())
                        .contrasena(passwordEncoder.encode("Password123!"))
                        .direccion(faker.address().fullAddress())
                        .pais(faker.country().name())
                        .build();
                    usuarioRepository.save(usuario);
                }
                System.out.println("✅ Se crearon 10 usuarios de prueba");
            }
        };
    }

}