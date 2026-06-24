package com.pm.ms_suscripciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.ms_suscripciones.DTO.SuscripcionRequestDTO;
import com.pm.ms_suscripciones.DTO.SuscripcionResponseDTO;
import com.pm.ms_suscripciones.Services.SuscripcionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SuscripcionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SuscripcionService suscripcionService;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    // ── Test 1: GET /api/suscripciones retorna lista ──────────────────────────
    @Test
    void testListarTodos_retorna200() throws Exception {
        SuscripcionResponseDTO dto = new SuscripcionResponseDTO();
        dto.setId(1L);
        dto.setPlanId("PREMIUM");
        dto.setEstado("ACTIVO");
        dto.setMonto(15.99);
        dto.setFechaInicio(LocalDate.now());

        when(suscripcionService.listarTodos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/suscripciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].planId").value("PREMIUM"))
                .andExpect(jsonPath("$[0].estado").value("ACTIVO"));
    }

    // ── Test 2: GET /api/suscripciones/{id} retorna suscripción ──────────────
    @Test
    void testBuscarPorId_retorna200() throws Exception {
        SuscripcionResponseDTO dto = new SuscripcionResponseDTO();
        dto.setId(1L);
        dto.setPlanId("BASIC");
        dto.setEstado("ACTIVO");

        when(suscripcionService.buscarPorId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/suscripciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId").value("BASIC"));
    }

    // ── Test 3: POST /api/suscripciones crea y retorna 201 ───────────────────
    @Test
    void testCrearSuscripcion_retorna201() throws Exception {
        SuscripcionRequestDTO request = new SuscripcionRequestDTO();
        request.setUsuarioId(1L);
        request.setPlanId("FAMILIAR");
        request.setMonto(45.00);

        SuscripcionResponseDTO response = new SuscripcionResponseDTO();
        response.setId(1L);
        response.setUsuarioId(1L);
        response.setPlanId("FAMILIAR");
        response.setEstado("ACTIVO");
        response.setMonto(45.00);

        when(suscripcionService.crearSuscripcion(any())).thenReturn(response);

        mockMvc.perform(post("/api/suscripciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.planId").value("FAMILIAR"))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    // ── Test 4: PUT /api/suscripciones/{id}/cancelar retorna 204 ─────────────
    @Test
    void testCancelar_retorna204() throws Exception {
        mockMvc.perform(put("/api/suscripciones/1/cancelar"))
                .andExpect(status().isNoContent());
    }

}
