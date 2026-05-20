package com.pm.ms_suscripciones.Services;

import com.pm.ms_suscripciones.Client.UsuarioClient;
import com.pm.ms_suscripciones.DTO.SuscripcionRequestDTO;
import com.pm.ms_suscripciones.DTO.SuscripcionResponseDTO;
import com.pm.ms_suscripciones.DTO.UsuarioDTO;
import com.pm.ms_suscripciones.Model.Suscripcion;
import com.pm.ms_suscripciones.Repository.SuscripcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SuscripcionService {

    @Autowired
    private SuscripcionRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    public SuscripcionResponseDTO crearSuscripcion(SuscripcionRequestDTO dto) {
        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorId(dto.getUsuarioId());

        if (usuario == null || usuario.getId() == null) {
            throw new RuntimeException("El usuario no existe");
        }

        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setUsuarioId(usuario.getId());
        suscripcion.setPlanId(dto.getPlanId());
        suscripcion.setMonto(dto.getMonto());
        suscripcion.setFechaInicio(dto.getFechaInicio() != null ? dto.getFechaInicio() : LocalDate.now());
        suscripcion.setEstado("ACTIVO");

        return convertirAResponse(repository.save(suscripcion));
    }

    public List<SuscripcionResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<SuscripcionResponseDTO> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public SuscripcionResponseDTO buscarPorId(Long id) {
        Suscripcion sus = repository.findById(id).orElse(null);
        return sus != null ? convertirAResponse(sus) : null;
    }

    public void cancelar(Long id) {
        Suscripcion sus = repository.findById(id).orElse(null);
        if (sus != null) {
            sus.setEstado("CANCELADO");
            sus.setFechaFin(LocalDate.now());
            repository.save(sus);
        }
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
}
