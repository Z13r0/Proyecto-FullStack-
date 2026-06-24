package com.pm.ms_peliculas.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-perfiles", url = "http://localhost:8082")
public interface PerfilClient {
    
    @GetMapping("/api/perfiles/validar/{id}")
    boolean validarPerfilActivo(@PathVariable("id") Long id);
}