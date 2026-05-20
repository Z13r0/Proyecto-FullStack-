package com.pm.ms_peliculas.Client;

import com.pm.ms_peliculas.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name = nombre del microservicio, url = su dirección
@FeignClient(name = "ms-usuarios", url = "http://localhost:8080")
public interface UsuarioClient {

    @GetMapping("/api/usuarios/{id}")
    UsuarioDTO buscarUsuarioPorId(@PathVariable Long id);
}
