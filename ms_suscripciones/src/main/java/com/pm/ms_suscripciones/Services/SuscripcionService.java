package com.pm.ms_suscripciones.Services;

import com.pm.ms_suscripciones.Client.UsuarioClient;
import com.pm.ms_suscripciones.DTO.SuscripcionRequestDTO;
import com.pm.ms_suscripciones.DTO.SuscripcionResponseDTO;
import com.pm.ms_suscripciones.DTO.UsuarioDTO;
import com.pm.ms_suscripciones.Model.Suscripcion;
import com.pm.ms_suscripciones.Repository.SuscripcionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SuscripcionService {

    private static final Logger log = LoggerFactory.getLogger(SuscripcionService.class);

    @Autowired
    private SuscripcionRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    public SuscripcionResponseDTO crearSuscripcion(SuscripcionRequestDTO dto) {
        log.info("=== Iniciando creación de suscripción para usuarioId: {} ===", dto.getUsuarioId());

        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorId(dto.getUsuarioId());
        if (usuario == null || usuario.getId() == null) {
            log.error("Usuario no encontrado: {}", dto.getUsuarioId());
            throw new RuntimeException("El usuario con ID " + dto.getUsuarioId() + " no existe");
        }

        // Regla de negocio: Solo una suscripción activa por usuario
        boolean tieneActiva = repository.findByUsuarioId(dto.getUsuarioId()).stream()
                .anyMatch(s -> "ACTIVO".equalsIgnoreCase(s.getEstado()));

        if (tieneActiva) {
            log.warn("Usuario {} ya tiene suscripción activa", dto.getUsuarioId());
            throw new RuntimeException("El usuario ya tiene una suscripción activa");
        }

        Suscripcion sus = new Suscripcion();
        sus.setUsuarioId(dto.getUsuarioId());
        sus.setPlanId(dto.getPlanId().toUpperCase());
        sus.setMonto(dto.getMonto());
        sus.setFechaInicio(dto.getFechaInicio() != null ? dto.getFechaInicio() : LocalDate.now());
        sus.setEstado("ACTIVO");

        Suscripcion saved = repository.save(sus);
        log.info("Suscripción creada exitosamente - ID: {}", saved.getId());

        return convertirAResponse(saved);
    }

    public List<SuscripcionResponseDTO> listarTodos() {
        log.info("Listando todas las suscripciones");
        return repository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<SuscripcionResponseDTO> listarPorUsuario(Long usuarioId) {
        log.info("Listando suscripciones del usuario {}", usuarioId);
        return repository.findByUsuarioId(usuarioId).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public SuscripcionResponseDTO buscarPorId(Long id) {
        log.info("Buscando suscripción ID: {}", id);
        Suscripcion sus = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));
        return convertirAResponse(sus);
    }

    public void cancelar(Long id) {
        log.info("Cancelando suscripción ID: {}", id);
        Suscripcion sus = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));

        sus.setEstado("CANCELADO");
        sus.setFechaFin(LocalDate.now());
        repository.save(sus);
        log.info("Suscripción cancelada correctamente");
    }

    private SuscripcionResponseDTO convertirAResponse(Suscripcion sus) {
        SuscripcionResponseDTO dto = new SuscripcionResponseDTO();
        dto.setId(sus.getId());
        dto.setUsuarioId(sus.getUsuarioId());
        dto.setPlanId(sus.getPlanId());
        dto.setFechaInicio(sus.getFechaInicio());
        dto.setFechaFin(sus.getFechaFin());
        dto.setEstado(sus.getEstado());
        dto.setMonto(sus.getMonto());
        return dto;
    }
    // Listar por estado (ACTIVO, CANCELADO, PENDIENTE)
    public List<SuscripcionResponseDTO> listarPorEstado(String estado) {
        log.info("Listando suscripciones por estado: {}", estado);
        return repository.findByEstado(estado).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    // Listar por plan (BASIC, PREMIUM, FAMILIAR)
    public List<SuscripcionResponseDTO> listarPorPlan(String planId) {
        log.info("Listando suscripciones por plan: {}", planId);
        return repository.findByPlanId(planId.toUpperCase()).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    // Contar suscripciones de un usuario
    public Long contarPorUsuario(Long usuarioId) {
        log.info("Contando suscripciones del usuario: {}", usuarioId);
        return repository.countByUsuarioId(usuarioId);

        }
}