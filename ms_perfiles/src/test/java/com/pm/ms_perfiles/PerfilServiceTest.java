package com.pm.ms_perfiles;

import com.pm.ms_perfiles.Client.UsuarioClient;
import com.pm.ms_perfiles.DTO.PerfilRequestDTO;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import com.pm.ms_perfiles.DTO.UsuarioDTO;
import com.pm.ms_perfiles.Model.Perfil;
import com.pm.ms_perfiles.Repository.PerfilRepository;
import com.pm.ms_perfiles.Services.PerfilService;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PerfilServiceTest {

    private final Faker faker = new Faker();

    @Mock
    private PerfilRepository perfilRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @InjectMocks
    private PerfilService perfilService;

    private Perfil perfil;
    private UsuarioDTO usuarioDTO;
    private PerfilRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        perfil = new Perfil();
        perfil.setId(1L);
        perfil.setUsuarioId(1L);
        perfil.setNombrePerfil(faker.name().fullName());
        perfil.setUrlAvatar(faker.internet().url());
        perfil.setEsInfantil(faker.bool().bool());
        perfil.setActivo(true);

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNombre(faker.name().fullName());

        requestDTO = new PerfilRequestDTO();
        requestDTO.setUsuarioId(1L);
        requestDTO.setNombrePerfil(faker.name().fullName());
        requestDTO.setUrlAvatar(faker.internet().url());
        requestDTO.setEsInfantil(faker.bool().bool());
    }

    @Test
    void crearPerfil_DeberiaCrearPerfilCorrectamente() {
        when(usuarioClient.obtenerUsuarioPorId(1L)).thenReturn(usuarioDTO);
        when(perfilRepository.save(any(Perfil.class))).thenReturn(perfil);

        PerfilResponseDTO response = perfilService.crearPerfil(requestDTO);

        assertNotNull(response);
        assertEquals(requestDTO.getUsuarioId(), response.getUsuarioId());
        verify(usuarioClient, times(1)).obtenerUsuarioPorId(1L);
        verify(perfilRepository, times(1)).save(any(Perfil.class));
    }

    @Test
    void listarPorUsuario_DeberiaRetornarListaDePerfiles() {
        when(perfilRepository.findByUsuarioIdAndActivoTrue(1L)).thenReturn(List.of(perfil));

        List<PerfilResponseDTO> perfiles = perfilService.listarPorUsuario(1L);

        assertNotNull(perfiles);
        assertFalse(perfiles.isEmpty());
        assertEquals(1, perfiles.size());
    }

    @Test
    void eliminarPerfil_DeberiaEliminarPerfilCorrectamente() {
        Long perfilId = 1L;
        when(perfilRepository.findById(perfilId)).thenReturn(Optional.of(perfil));

        perfilService.eliminarPerfil(perfilId);

        verify(perfilRepository).findById(perfilId);
        verify(perfilRepository).deleteById(perfilId);
    }
}
