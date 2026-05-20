package com.pm.ms_usuarios.Repository;

import com.pm.ms_usuarios.Model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Este metodo permite buscar a los usuarios a traves del Nombre
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    List<Usuario> findByNombre(@Param("nombre") String nombre);

    // Este metodo permite buscar por el Correo
    @Query(value = "SELECT * FROM Usuario WHERE email= :email", nativeQuery = true)
    Usuario findByCorreo(@Param("email") String email);
}
