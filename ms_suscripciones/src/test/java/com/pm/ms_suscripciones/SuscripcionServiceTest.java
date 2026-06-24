package com.pm.ms_suscripciones;

import com.pm.ms_suscripciones.Client.UsuarioClient;
import com.pm.ms_suscripciones.DTO.SuscripcionRequestDTO;
import com.pm.ms_suscripciones.DTO.SuscripcionResponseDTO;
import com.pm.ms_suscripciones.DTO.UsuarioDTO;
import com.pm.ms_suscripciones.Model.Suscripcion;
import com.pm.ms_suscripciones.Repository.SuscripcionRepository;
import com.pm.ms_suscripciones.Services.SuscripcionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuscripcionServiceTest {

    // Inyecta el servicio real a probar
    @Autowired
    private SuscripcionService suscripcionService;

    // Mock del repositorio para simular la BD
    @MockitoBean
    private SuscripcionRepository suscripcionRepository;

    // Mock del cliente Feign para simular ms-usuarios
    @MockitoBean
    private UsuarioClient usuarioClient;

    // ── Test 1: listar todas ───────────────────────────────────────────────────
    @Test
    void testListarTodos() {
        // Define el comportamiento del mock
        Suscripcion sus = new Suscripcion(1L, 1L, "PREMIUM",
                LocalDate.now(), null, "ACTIVO", 15.99);
        when(suscripcionRepository.findAll()).thenReturn(List.of(sus));

        // Llama al método del servicio
        List<SuscripcionResponseDTO> resultado = suscripcionService.listarTodos();

        // Verifica el resultado
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("PREMIUM", resultado.get(0).getPlanId());
    }

    // ── Test 2: buscar por ID existente ───────────────────────────────────────
    @Test
    void testBuscarPorId_existe() {
        Suscripcion sus = new Suscripcion(1L, 2L, "BASIC",
                LocalDate.now(), null, "ACTIVO", 8.99);
        when(suscripcionRepository.findById(1L)).thenReturn(Optional.of(sus));

        SuscripcionResponseDTO resultado = suscripcionService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("BASIC", resultado.getPlanId());
        assertEquals("ACTIVO", resultado.getEstado());
    }

    // ── Test 3: buscar por ID inexistente lanza excepción ────────────────────
    @Test
    void testBuscarPorId_noExiste() {
        when(suscripcionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> suscripcionService.buscarPorId(99L));
    }

    // ── Test 4: crear suscripción exitosamente ────────────────────────────────
    @Test
    void testCrearSuscripcion_exitoso() {
        // Simula que el usuario existe en ms-usuarios
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        when(usuarioClient.obtenerUsuarioPorId(1L)).thenReturn(usuario);

        // Simula que no tiene suscripción activa
        when(suscripcionRepository.findByUsuarioId(1L)).thenReturn(List.of());

        // Simula el guardado
        Suscripcion saved = new Suscripcion(1L, 1L, "PREMIUM",
                LocalDate.now(), null, "ACTIVO", 15.99);
        when(suscripcionRepository.save(any())).thenReturn(saved);

        SuscripcionRequestDTO request = new SuscripcionRequestDTO();
        request.setUsuarioId(1L);
        request.setPlanId("PREMIUM");
        request.setMonto(15.99);

        SuscripcionResponseDTO resultado = suscripcionService.crearSuscripcion(request);

        assertNotNull(resultado);
        assertEquals("PREMIUM", resultado.getPlanId());
        assertEquals("ACTIVO", resultado.getEstado());
    }

    // ── Test 5: crear suscripción con usuario ya activo lanza excepción ───────
    @Test
    void testCrearSuscripcion_usuarioYaTieneActiva() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        when(usuarioClient.obtenerUsuarioPorId(1L)).thenReturn(usuario);

        // Simula que ya tiene una suscripción activa
        Suscripcion activa = new Suscripcion(1L, 1L, "BASIC",
                LocalDate.now(), null, "ACTIVO", 8.99);
        when(suscripcionRepository.findByUsuarioId(1L)).thenReturn(List.of(activa));

        SuscripcionRequestDTO request = new SuscripcionRequestDTO();
        request.setUsuarioId(1L);
        request.setPlanId("PREMIUM");

        assertThrows(RuntimeException.class,
                () -> suscripcionService.crearSuscripcion(request));
    }

    // ── Test 6: cancelar suscripción ──────────────────────────────────────────
    @Test
    void testCancelar() {
        Suscripcion sus = new Suscripcion(1L, 1L, "PREMIUM",
                LocalDate.now(), null, "ACTIVO", 15.99);
        when(suscripcionRepository.findById(1L)).thenReturn(Optional.of(sus));
        when(suscripcionRepository.save(any())).thenReturn(sus);

        // No debe lanzar excepción
        assertDoesNotThrow(() -> suscripcionService.cancelar(1L));
    }

}
