package com.pm.ms_perfiles.Services;

import com.pm.ms_perfiles.Client.UsuarioClient;
import com.pm.ms_perfiles.DTO.PerfilRequestDTO;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import com.pm.ms_perfiles.DTO.UsuarioDTO;
import com.pm.ms_perfiles.Model.Perfil;
import com.pm.ms_perfiles.Repository.PerfilRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public PerfilResponseDTO crearPerfil(PerfilRequestDTO dto) {
        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorId(dto.getUsuarioId());

        if (usuario == null || usuario.getId() == null) {
            throw new RuntimeException("El usuario no existe");
        }

        Perfil perfil = new Perfil();
        perfil.setUsuarioId(usuario.getId());
        perfil.setNombrePerfil(dto.getNombrePerfil());
        perfil.setUrlAvatar(dto.getUrlAvatar());
        perfil.setEsInfantil(dto.getEsInfantil() != null ? dto.getEsInfantil() : false);

        return convertirAResponse(perfilRepository.save(perfil));
    }

    public List<PerfilResponseDTO> listarTodos() {
        return perfilRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public List<PerfilResponseDTO> listarPorUsuario(Long usuarioId) {
        return perfilRepository.findByUsuarioIdAndActivoTrue(usuarioId)
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public PerfilResponseDTO buscarPorId(Long id) {
        Perfil perfil = perfilRepository.findById(id).orElse(null);
        return perfil != null ? convertirAResponse(perfil) : null;
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
