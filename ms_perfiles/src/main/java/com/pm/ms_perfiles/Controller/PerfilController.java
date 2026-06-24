package com.pm.ms_perfiles.Controller;

import com.pm.ms_perfiles.DTO.PerfilRequestDTO;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import com.pm.ms_perfiles.Services.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/perfiles")
@Tag(name = "Perfiles", description = "API para gestión de perfiles de usuarios")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @Operation(summary = "Crear un nuevo perfil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<PerfilResponseDTO> crearPerfil(@Valid @RequestBody PerfilRequestDTO request) {
        PerfilResponseDTO perfil = perfilService.crearPerfil(request);
        añadirEnlacesHateoas(perfil);
        return new ResponseEntity<>(perfil, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los perfiles")
    @GetMapping
    public ResponseEntity<List<PerfilResponseDTO>> listarTodos() {
        List<PerfilResponseDTO> perfiles = perfilService.listarTodos();
        perfiles.forEach(this::añadirEnlacesHateoas);
        return ResponseEntity.ok(perfiles);
    }

    @Operation(summary = "Obtener perfil por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> buscarPorId(@PathVariable Long id) {
        PerfilResponseDTO perfil = perfilService.buscarPorId(id);
        añadirEnlacesHateoas(perfil);
        return ResponseEntity.ok(perfil);
    }

    @Operation(summary = "Obtener perfiles por usuario")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PerfilResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<PerfilResponseDTO> perfiles = perfilService.listarPorUsuario(usuarioId);
        perfiles.forEach(this::añadirEnlacesHateoas);
        return ResponseEntity.ok(perfiles);
    }

    @Operation(summary = "Actualizar perfil")
    @PutMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> actualizarPerfil(@PathVariable Long id, @Valid @RequestBody PerfilRequestDTO request) {
        PerfilResponseDTO perfil = perfilService.actualizarPerfil(id, request);
        añadirEnlacesHateoas(perfil);
        return ResponseEntity.ok(perfil);
    }

    @Operation(summary = "Eliminar perfil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfil(@PathVariable Long id) {
        perfilService.eliminarPerfil(id);
        return ResponseEntity.noContent().build();
    }

    private void añadirEnlacesHateoas(PerfilResponseDTO dto) {
        Link selfLink = linkTo(methodOn(PerfilController.class).buscarPorId(dto.getId())).withSelfRel();
        Link deleteLink = linkTo(methodOn(PerfilController.class).eliminarPerfil(dto.getId())).withRel("eliminar");
        dto.add(selfLink, deleteLink);
    }
}
