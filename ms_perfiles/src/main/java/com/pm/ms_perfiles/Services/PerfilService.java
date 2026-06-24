package com.pm.ms_perfiles.Services;

import com.pm.ms_perfiles.Client.UsuarioClient;
import com.pm.ms_perfiles.DTO.PerfilRequestDTO;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import com.pm.ms_perfiles.DTO.UsuarioDTO;
import com.pm.ms_perfiles.Model.Perfil;
import com.pm.ms_perfiles.Repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private static final Logger log = LoggerFactory.getLogger(PerfilService.class);
    private static final int MAX_PERFILES_POR_USUARIO = 4;

    private final PerfilRepository perfilRepository;
    private final UsuarioClient usuarioClient;

    @Transactional
    public PerfilResponseDTO crearPerfil(PerfilRequestDTO request) {
        log.info("=== Iniciando creación de perfil para usuarioId: {} ===", request.getUsuarioId());

        // Regla de negocio 1: Validar que el usuario exista
        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorId(request.getUsuarioId());
        if (usuario == null) {
            log.error("Usuario con ID {} no encontrado", request.getUsuarioId());
            throw new RuntimeException("Usuario no encontrado");
        }

        // Regla de negocio 2: Máximo de perfiles por usuario
        long cantidadPerfiles = perfilRepository.countByUsuarioId(request.getUsuarioId());
        if (cantidadPerfiles >= MAX_PERFILES_POR_USUARIO) {
            log.warn("Usuario {} ha alcanzado el máximo de {} perfiles", request.getUsuarioId(), MAX_PERFILES_POR_USUARIO);
            throw new RuntimeException("Máximo de perfiles alcanzado (" + MAX_PERFILES_POR_USUARIO + ")");
        }

        Perfil perfil = new Perfil();
        perfil.setUsuarioId(request.getUsuarioId());
        perfil.setNombrePerfil(request.getNombrePerfil());
        perfil.setUrlAvatar(request.getUrlAvatar());
        perfil.setEsInfantil(request.getEsInfantil() != null ? request.getEsInfantil() : false);
        perfil.setActivo(true);
        perfil.setCreatedAt(LocalDateTime.now());

        Perfil perfilGuardado = perfilRepository.save(perfil);

        log.info("Perfil creado exitosamente con ID: {}", perfilGuardado.getId());
        return convertirAResponse(perfilGuardado);
    }

    public List<PerfilResponseDTO> listarTodos() {
        log.info("Listando todos los perfiles");
        return perfilRepository.findAll().stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<PerfilResponseDTO> listarPorUsuario(Long usuarioId) {
        log.info("Listando perfiles del usuario {}", usuarioId);
        return perfilRepository.findByUsuarioIdAndActivoTrue(usuarioId).stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public PerfilResponseDTO buscarPorId(Long id) {
        log.info("Buscando perfil por ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));
        return convertirAResponse(perfil);
    }

    @Transactional
    public PerfilResponseDTO actualizarPerfil(Long id, PerfilRequestDTO request) {
        log.info("Actualizando perfil ID: {}", id);
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        perfil.setNombrePerfil(request.getNombrePerfil());
        perfil.setUrlAvatar(request.getUrlAvatar());
        if (request.getEsInfantil() != null) {
            perfil.setEsInfantil(request.getEsInfantil());
        }

        Perfil perfilActualizado = perfilRepository.save(perfil);
        log.info("Perfil ID {} actualizado correctamente", id);
        return convertirAResponse(perfilActualizado);
    }

    @Transactional
    public void eliminarPerfil(Long id) {
        log.info("Eliminando perfil con ID: {}", id);

        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));

        perfilRepository.deleteById(id);
        log.info("Perfil ID {} eliminado correctamente", id);
    }

    private PerfilResponseDTO convertirAResponse(Perfil perfil) {
        PerfilResponseDTO dto = new PerfilResponseDTO();
        dto.setId(perfil.getId());
        dto.setUsuarioId(perfil.getUsuarioId());
        dto.setNombrePerfil(perfil.getNombrePerfil());
        dto.setUrlAvatar(perfil.getUrlAvatar());
        dto.setEsInfantil(perfil.getEsInfantil());
        dto.setActivo(perfil.getActivo());
        dto.setCreatedAt(perfil.getCreatedAt());
        return dto;
    }
}
