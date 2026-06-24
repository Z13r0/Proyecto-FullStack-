package com.pm.ms_peliculas.Service;

import com.pm.ms_peliculas.Client.UsuarioClient;
import com.pm.ms_peliculas.Client.PerfilClient;
import com.pm.ms_peliculas.Client.SuscripcionClient;
import com.pm.ms_peliculas.DTO.UsuarioDTO;
import com.pm.ms_peliculas.Model.Pelicula;
import com.pm.ms_peliculas.Repository.PeliculaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PeliculaService {

    private UsuarioClient usuarioClient;

    private PerfilClient perfilClient;

    private SuscripcionClient suscripcionClient;

    private PeliculaRepository peliculaRepository;

    public UsuarioDTO obtenerUsuario(Long id) {
        return usuarioClient.buscarUsuarioPorId(id);
    }

    public List<Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

    public Pelicula findById(Long id) {
        return peliculaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pelicula no encontrada con id: " + id));
    }

    public Pelicula save(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void delete(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new NoSuchElementException("Pelicula no encontrada con id: " + id);
        }
        peliculaRepository.deleteById(id);
    }

    public List<Pelicula> findByTitulo(String titulo) {
        return peliculaRepository.findByTitulo(titulo);
    }

    public List<Pelicula> findByClasificacionEdad(String clasificacion) {
        return peliculaRepository.findByClasificacionEdad(clasificacion);
    }
}