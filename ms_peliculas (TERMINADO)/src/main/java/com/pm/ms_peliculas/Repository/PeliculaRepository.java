package com.pm.ms_peliculas.Repository;


import com.pm.ms_peliculas.Model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    // Se hace un metodo de busqueda por titulo
    @Query("SELECT p FROM Pelicula p WHERE p.titulo= :titulo")
    List<Pelicula> findByTitulo(String titulo);

    // Se hace un metodo que busque la pelicula según su clasificacion de edad
    @Query("SELECT p FROM Pelicula p WHERE p.clasificacionEdad= :clasificacionEdad ")
    List<Pelicula> findByClasificacionEdad(String clasificacionEdad);
}
