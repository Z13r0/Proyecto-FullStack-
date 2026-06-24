package com.pm.ms_perfiles.Controller;

import com.pm.ms_perfiles.Assemblers.PerfilModelAssembler;
import com.pm.ms_perfiles.DTO.PerfilRequestDTO;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import com.pm.ms_perfiles.Services.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Perfiles V2 (HATEOAS)", description = "Versión con HATEOAS de la API de perfiles")
@RestController
@RequestMapping("/v2/perfiles")
public class PerfilControllerV2 {

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private PerfilModelAssembler assembler;

    @Operation(summary = "Listar todos los perfiles con HATEOAS")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<PerfilResponseDTO>> listar() {
        List<EntityModel<PerfilResponseDTO>> lista = perfilService.listarTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(PerfilControllerV2.class).listar()).withSelfRel());
    }

    @Operation(summary = "Buscar perfil por ID con HATEOAS")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<PerfilResponseDTO> buscar(@PathVariable Long id) {
        PerfilResponseDTO dto = perfilService.buscarPorId(id);
        return assembler.toModel(dto);
    }

    @Operation(summary = "Listar perfiles por usuario con HATEOAS")
    @GetMapping(value = "/usuario/{usuarioId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<PerfilResponseDTO>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        List<EntityModel<PerfilResponseDTO>> lista = perfilService.listarPorUsuario(usuarioId)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(PerfilControllerV2.class).listarPorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(PerfilControllerV2.class).listar()).withRel("perfiles"));
    }

    @Operation(summary = "Crear perfil con HATEOAS")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PerfilResponseDTO>> crear(
            @Valid @RequestBody PerfilRequestDTO dto) {
        PerfilResponseDTO response = perfilService.crearPerfil(dto);
        return ResponseEntity
                .created(linkTo(methodOn(PerfilControllerV2.class).buscar(response.getId())).toUri())
                .body(assembler.toModel(response));
    }

    @Operation(summary = "Eliminar perfil con HATEOAS")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        perfilService.eliminarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
