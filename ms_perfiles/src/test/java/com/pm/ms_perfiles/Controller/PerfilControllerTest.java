package com.pm.ms_perfiles.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pm.ms_perfiles.DTO.PerfilRequestDTO;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import com.pm.ms_perfiles.Services.PerfilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PerfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PerfilService perfilService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    // ── Test 1: GET /perfiles retorna lista ──────────────────────────────────
    @Test
    void testListar_retorna200() throws Exception {
        PerfilResponseDTO dto = new PerfilResponseDTO();
        dto.setId(1L);
        dto.setNombrePerfil("Mi Perfil");
        dto.setUsuarioId(1L);
        dto.setEsInfantil(false);
        dto.setActivo(true);

        when(perfilService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/perfiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombrePerfil").value("Mi Perfil"));
    }

    // ── Test 2: GET /perfiles/{id} retorna perfil ─────────────────────────────
    @Test
    void testBuscar_retorna200() throws Exception {
        PerfilResponseDTO dto = new PerfilResponseDTO();
        dto.setId(1L);
        dto.setNombrePerfil("Mi Perfil");
        dto.setUsuarioId(1L);

        when(perfilService.buscarPorId(1L)).thenReturn(dto);

        mockMvc.perform(get("/perfiles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePerfil").value("Mi Perfil"));
    }

    // ── Test 3: POST /perfiles crea y retorna 201 ─────────────────────────────
    @Test
    void testCrear_retorna201() throws Exception {
        PerfilRequestDTO request = new PerfilRequestDTO();
        request.setUsuarioId(1L);
        request.setNombrePerfil("Nuevo Perfil");
        request.setEsInfantil(false);

        PerfilResponseDTO response = new PerfilResponseDTO();
        response.setId(1L);
        response.setUsuarioId(1L);
        response.setNombrePerfil("Nuevo Perfil");
        response.setActivo(true);

        when(perfilService.crearPerfil(any())).thenReturn(response);

        mockMvc.perform(post("/perfiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombrePerfil").value("Nuevo Perfil"));
    }

    // ── Test 4: DELETE /perfiles/{id} retorna 204 ─────────────────────────────
    @Test
    void testEliminar_retorna204() throws Exception {
        mockMvc.perform(delete("/perfiles/1"))
                .andExpect(status().isNoContent());
    }
}
