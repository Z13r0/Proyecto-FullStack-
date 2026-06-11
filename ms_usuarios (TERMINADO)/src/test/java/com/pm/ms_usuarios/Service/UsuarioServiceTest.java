package com.pm.ms_usuarios.Service;

import com.pm.ms_usuarios.Model.Usuario;
import com.pm.ms_usuarios.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios para UsuarioService con JUnit 5 y Mockito
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Test Suite: UsuarioService")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
            .id(1L)
            .nombre("Juan Carlos Pérez")
            .email("juan@example.com")
            .contrasena("Password123!")
            .direccion("Calle Principal 123")
            .pais("España")
            .build();
    }

    @Test
    @DisplayName("Debe obtener todos los usuarios")
    void testFindAll() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.findAll();

        assertThat(result).isNotEmpty()
            .hasSize(1)
            .contains(usuario);

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener usuario por ID")
    void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);

        assertThat(result).isNotNull()
            .isEqualTo(usuario);

        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando usuario no existe")
    void testFindByIdNotFound() {
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usuarioService.findById(999L))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Usuario no encontrado");

        verify(usuarioRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Debe guardar un usuario")
    void testSave() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.save(usuario);

        assertThat(result).isNotNull()
            .isEqualTo(usuario);

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Debe eliminar un usuario")
    void testDelete() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.delete(1L);

        verify(usuarioRepository, times(1)).existsById(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar usuario inexistente")
    void testDeleteNotFound() {
        when(usuarioRepository.existsById(999L)).thenReturn(false);

        assertThatThrownBy(() -> usuarioService.delete(999L))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Usuario no encontrado");

        verify(usuarioRepository, times(1)).existsById(999L);
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Debe encontrar usuario por email")
    void testFindByEmail() {
        when(usuarioRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findByEmail("juan@example.com");

        assertThat(result).isNotNull()
            .isEqualTo(usuario);

        verify(usuarioRepository, times(1)).findByEmail("juan@example.com");
    }

}