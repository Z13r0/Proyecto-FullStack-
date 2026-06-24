package com.pm.ms_suscripciones.Controller;

import com.pm.ms_suscripciones.Assemblers.SuscripcionModelAssembler;
import com.pm.ms_suscripciones.DTO.SuscripcionRequestDTO;
import com.pm.ms_suscripciones.DTO.SuscripcionResponseDTO;
import com.pm.ms_suscripciones.Services.SuscripcionService;
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



@Tag(name = "Suscripciones V2 (HATEOAS)", description = "Versión con HATEOAS de la API de suscripciones")
@RestController
@RequestMapping("/v2/suscripciones")
public class SuscripcionControllerV2 {

    @Autowired
    private SuscripcionService service;

    @Autowired
    private SuscripcionModelAssembler assembler;

    // ── GET todas ──────────────────────────────────────────────────────────────
    @Operation(summary = "Listar todas las suscripciones con HATEOAS")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<SuscripcionResponseDTO>> listarTodos() {
        List<EntityModel<SuscripcionResponseDTO>> lista = service.listarTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(SuscripcionControllerV2.class).listarTodos()).withSelfRel());
    }

    // ── GET por ID ─────────────────────────────────────────────────────────────
    @Operation(summary = "Buscar suscripción por ID con HATEOAS")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<SuscripcionResponseDTO> buscar(@PathVariable Long id) {
        SuscripcionResponseDTO dto = service.buscarPorId(id);
        return assembler.toModel(dto);
    }

    // ── GET por usuarioId ──────────────────────────────────────────────────────
    @Operation(summary = "Listar suscripciones por usuario con HATEOAS")
    @GetMapping(value = "/usuario/{usuarioId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<SuscripcionResponseDTO>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        List<EntityModel<SuscripcionResponseDTO>> lista = service.listarPorUsuario(usuarioId)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(SuscripcionControllerV2.class).listarPorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(SuscripcionControllerV2.class).listarTodos()).withRel("suscripciones"));
    }

    // ── GET por estado ─────────────────────────────────────────────────────────
    @Operation(summary = "Listar suscripciones por estado con HATEOAS")
    @GetMapping(value = "/estado/{estado}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<SuscripcionResponseDTO>> listarPorEstado(
            @PathVariable String estado) {
        List<EntityModel<SuscripcionResponseDTO>> lista = service.listarPorEstado(estado)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(SuscripcionControllerV2.class).listarPorEstado(estado)).withSelfRel(),
                linkTo(methodOn(SuscripcionControllerV2.class).listarTodos()).withRel("suscripciones"));
    }

    // ── GET por plan ───────────────────────────────────────────────────────────
    @Operation(summary = "Listar suscripciones por plan con HATEOAS")
    @GetMapping(value = "/plan/{planId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<SuscripcionResponseDTO>> listarPorPlan(
            @PathVariable String planId) {
        List<EntityModel<SuscripcionResponseDTO>> lista = service.listarPorPlan(planId)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lista,
                linkTo(methodOn(SuscripcionControllerV2.class).listarPorPlan(planId)).withSelfRel(),
                linkTo(methodOn(SuscripcionControllerV2.class).listarTodos()).withRel("suscripciones"));
    }

    // ── GET total por usuario ──────────────────────────────────────────────────
    @Operation(summary = "Contar suscripciones de un usuario con HATEOAS")
    @GetMapping(value = "/usuario/{usuarioId}/total", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Long> contarPorUsuario(@PathVariable Long usuarioId) {
        Long total = service.contarPorUsuario(usuarioId);
        return EntityModel.of(total,
                linkTo(methodOn(SuscripcionControllerV2.class).contarPorUsuario(usuarioId)).withSelfRel(),
                linkTo(methodOn(SuscripcionControllerV2.class).listarPorUsuario(usuarioId)).withRel("suscripciones-usuario"));
    }

    // ── POST crear ─────────────────────────────────────────────────────────────
    @Operation(summary = "Crear suscripción con HATEOAS")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<SuscripcionResponseDTO>> crear(
            @Valid @RequestBody SuscripcionRequestDTO dto) {
        SuscripcionResponseDTO response = service.crearSuscripcion(dto);
        return ResponseEntity
                .created(linkTo(methodOn(SuscripcionControllerV2.class).buscar(response.getId())).toUri())
                .body(assembler.toModel(response));
    }

    // ── PUT cancelar ───────────────────────────────────────────────────────────
    @Operation(summary = "Cancelar suscripción con HATEOAS")
    @PutMapping(value = "/{id}/cancelar", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
