package com.pm.ms_peliculas.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-suscripciones", url = "http://localhost:8083")
public interface SuscripcionClient {

    @GetMapping("/api/suscripciones/verificar")
    boolean tieneAccesoContenido(@RequestParam("usuarioId") Long usuarioId, @RequestParam("clasificacion") String clasificacion);
}
