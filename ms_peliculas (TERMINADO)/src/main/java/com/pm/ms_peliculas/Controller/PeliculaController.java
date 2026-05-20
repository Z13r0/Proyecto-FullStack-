package com.pm.ms_peliculas.Controller;

import com.pm.ms_peliculas.Model.Pelicula;
import com.pm.ms_peliculas.Service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
// URL: http://localhost:8081/api/peliculas
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    // ==================== LISTAR TODOS ====================
    @GetMapping
    public ResponseEntity<List<Pelicula>> listar() {
        List<Pelicula> peliculas = peliculaService.findAll();

        if (peliculas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(peliculas);
    }

    // ==================== GUARDAR ====================
    @PostMapping
    public ResponseEntity<Pelicula> guardar(@RequestBody Pelicula pelicula) {
        Pelicula peliculaNueva = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaNueva);
    }

    // ==================== BUSCAR POR ID ====================
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pelicula> buscar(@PathVariable Long id) {
        try {
            Pelicula pelicula = peliculaService.findById(Long.valueOf(id));
            return ResponseEntity.ok(pelicula);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ==================== ELIMINAR ====================
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            peliculaService.delete(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ==================== ACTUALIZAR ====================
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        try {
            Pelicula peli = peliculaService.findById(Long.valueOf(id));

            peli.setTitulo(pelicula.getTitulo());
            peli.setSinopsis(pelicula.getSinopsis());
            peli.setDuracionMinutos(pelicula.getDuracionMinutos());
            peli.setClasificacionEdad(pelicula.getClasificacionEdad());

            peliculaService.save(peli);
            return ResponseEntity.ok(peli);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
