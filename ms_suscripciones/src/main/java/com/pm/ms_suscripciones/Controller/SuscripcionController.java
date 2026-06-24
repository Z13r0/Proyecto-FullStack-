package com.pm.ms_suscripciones.Controller;

import com.pm.ms_suscripciones.DTO.SuscripcionRequestDTO;
import com.pm.ms_suscripciones.DTO.SuscripcionResponseDTO;
import com.pm.ms_suscripciones.Services.SuscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Suscripciones", description = "Operaciones relacionadas con las suscripciones de usuarios")
@RestController
@RequestMapping("/suscripciones")
public class SuscripcionController {

    private static final Logger log = LoggerFactory.getLogger(SuscripcionController.class);

    @Autowired
    private SuscripcionService service;

    @Operation(summary = "Crear una nueva suscripción", description = "Crea una suscripción para un usuario dado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Suscripción creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario ya tiene suscripción activa")
    })
    @PostMapping
    public ResponseEntity<SuscripcionResponseDTO> crear(@Valid @RequestBody SuscripcionRequestDTO dto) {
        log.info("Recibida solicitud POST para crear suscripción");
        SuscripcionResponseDTO response = service.crearSuscripcion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Listar todas las suscripciones", description = "Obtiene una lista de todas las suscripciones registradas")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<SuscripcionResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Listar suscripciones por usuario", description = "Obtiene todas las suscripciones de un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SuscripcionResponseDTO>> listarPorUsuario(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @Operation(summary = "Buscar suscripción por ID", description = "Obtiene una suscripción específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suscripción encontrada"),
            @ApiResponse(responseCode = "404", description = "Suscripción no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionResponseDTO> buscar(
            @Parameter(description = "ID de la suscripción", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "Cancelar una suscripción", description = "Cancela una suscripción activa por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Suscripción cancelada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Suscripción no encontrada")
    })
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(
            @Parameter(description = "ID de la suscripción a cancelar", required = true)
            @PathVariable Long id) {
        log.info("Solicitud de cancelación de suscripción ID: {}", id);
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
