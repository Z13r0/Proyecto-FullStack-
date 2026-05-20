package com.pm.ms_usuarios.Controller;

import com.pm.ms_usuarios.Model.Usuario;
import com.pm.ms_usuarios.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
// URL: http://localhost:8080/api/usuarios
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ==================== LISTAR TODOS ====================
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // ==================== GUARDAR ====================
    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }

    // ==================== BUSCAR POR ID ====================
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(Long.valueOf(id));
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ==================== ELIMINAR ====================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.delete(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ==================== ACTUALIZAR ====================
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            Usuario usu = usuarioService.findById(Long.valueOf(id));

            usu.setNombre(usuario.getNombre());
            usu.setEmail(usuario.getEmail());
            usu.setContrasena(usuario.getContrasena());
            usu.setDireccion(usuario.getDireccion());
            usu.setPais(usuario.getPais());

            usuarioService.save(usu);
            return ResponseEntity.ok(usu);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
