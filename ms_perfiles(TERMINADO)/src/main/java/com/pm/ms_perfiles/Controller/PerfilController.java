package com.pm.ms_perfiles.Controller;

import com.pm.ms_perfiles.DTO.PerfilRequestDTO;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import com.pm.ms_perfiles.DTO.UsuarioDTO;
import com.pm.ms_perfiles.Model.Perfil;
import com.pm.ms_perfiles.Services.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    // Crear Perfil
    @PostMapping
    public ResponseEntity<PerfilResponseDTO> crear(@Valid @RequestBody PerfilRequestDTO dto) {
        PerfilResponseDTO response = perfilService.crearPerfil(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Listar todos los perfiles
    @GetMapping
    public ResponseEntity<List<PerfilResponseDTO>> listarTodos() {
        return ResponseEntity.ok(perfilService.listarTodos());
    }

    // Listar perfiles por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PerfilResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(perfilService.listarPorUsuario(usuarioId));
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.buscarPorId(id));
    }
    }

