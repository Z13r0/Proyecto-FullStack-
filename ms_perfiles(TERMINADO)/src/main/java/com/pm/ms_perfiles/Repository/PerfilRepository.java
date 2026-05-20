package com.pm.ms_perfiles.Repository;

import com.pm.ms_perfiles.Model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    List<Perfil> findByUsuarioId(Long usuarioId);
    List<Perfil> findByUsuarioIdAndActivoTrue(Long usuarioId);
}
