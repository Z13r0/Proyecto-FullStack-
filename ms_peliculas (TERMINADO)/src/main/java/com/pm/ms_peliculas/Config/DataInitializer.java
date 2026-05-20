package com.pm.ms_peliculas.Config;


import com.pm.ms_peliculas.Model.Pelicula;
import com.pm.ms_peliculas.Repository.PeliculaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner commandLineRunner(PeliculaRepository peliculaRepository) {
        return args -> {
            if (peliculaRepository.count() == 0) {

                peliculaRepository.saveAll(List.of(
                        new Pelicula(null, "Inception",       "Un ladrón que roba secretos mediante sueños compartidos.",        148, "PG-13"),
                        new Pelicula(null, "Interstellar",    "Un grupo de astronautas viaja más allá de nuestra galaxia.",       169, "PG-13"),
                        new Pelicula(null, "The Dark Knight", "Batman enfrenta al Joker en Gotham City.",                         152, "PG-13"),
                        new Pelicula(null, "Toy Story",       "Los juguetes cobran vida cuando los humanos no están presentes.",   81, "G"),
                        new Pelicula(null, "John Wick",       "Un ex asesino busca venganza tras la muerte de su perro.",         101, "R")
                ));

                System.out.println(">> DB: Películas de prueba cargadas correctamente.");
            } else {
                System.out.println(">> DB: Ya existen películas. No se insertaron datos.");
            }
        };
    }
}
