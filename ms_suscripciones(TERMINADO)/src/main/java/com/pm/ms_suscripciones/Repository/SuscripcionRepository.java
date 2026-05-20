package com.pm.ms_suscripciones.Repository;

import com.pm.ms_suscripciones.Model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {

    List<Suscripcion> findByUsuarioId(Long usuarioId);
    List<Suscripcion> findByUsuarioIdAndEstado(Long usuarioId, String estado);
}
