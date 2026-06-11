package com.pm.ms_usuarios.Controller;

import com.pm.ms_usuarios.DTO.UsuarioDTO;
import com.pm.ms_usuarios.Model.Usuario;
import com.pm.ms_usuarios.Service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests unitarios para UsuarioController con JUnit 5
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test Suite: UsuarioController")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
            .id(1L)
            .nombre("Juan Carlos Pérez")
            .email("juan@example.com")
            .contrasena("Password123!")
            .direccion("Calle Principal 123, Apartamento 4B")
            .pais("España")
            .build();

        usuarioDTO = UsuarioDTO.builder()
            .id(1L)
            .nombre("Juan Carlos Pérez")
            .email("juan@example.com")
            .contrasena("Password123!")
            .direccion("Calle Principal 123, Apartamento 4B")
            .pais("España")
            .build();
    }

    @Test
    @DisplayName("Debe listar todos los usuarios")
    void testListarUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", notNullValue()));

        verify(usuarioService, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe retornar 204 cuando no hay usuarios")
    void testListarUsuariosVacio() throws Exception {
        when(usuarioService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener usuario por ID")
    void testBuscarUsuarioPorId() throws Exception {
        when(usuarioService.findById(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.email", equalTo("juan@example.com")))
            .andExpect(jsonPath("$.content.nombre", equalTo("Juan Carlos Pérez")));

        verify(usuarioService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar 404 cuando usuario no existe")
    void testBuscarUsuarioNoExistente() throws Exception {
        when(usuarioService.findById(999L)).thenThrow(new RuntimeException("Usuario no encontrado"));

        mockMvc.perform(get("/api/usuarios/999")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(usuarioService, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Debe crear un nuevo usuario")
    void testCrearUsuario() throws Exception {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(usuarioDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.content.email", equalTo("juan@example.com")));

        verify(usuarioService, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Debe actualizar un usuario existente")
    void testActualizarUsuario() throws Exception {
        usuario.setNombre("Juan Actualizado");
        when(usuarioService.findById(1L)).thenReturn(usuario);
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        usuarioDTO.setNombre("Juan Actualizado");

        mockMvc.perform(put("/api/usuarios/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(usuarioDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.nombre", equalTo("Juan Actualizado")));

        verify(usuarioService, times(1)).findById(1L);
        verify(usuarioService, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Debe eliminar un usuario")
    void testEliminarUsuario() throws Exception {
        doNothing().when(usuarioService).delete(1L);

        mockMvc.perform(delete("/api/usuarios/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).delete(1L);
    }

    @Test
    @DisplayName("Debe retornar 404 al eliminar usuario inexistente")
    void testEliminarUsuarioInexistente() throws Exception {
        doThrow(new RuntimeException("Usuario no encontrado")).when(usuarioService).delete(999L);

        mockMvc.perform(delete("/api/usuarios/999")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(usuarioService, times(1)).delete(999L);
    }

}