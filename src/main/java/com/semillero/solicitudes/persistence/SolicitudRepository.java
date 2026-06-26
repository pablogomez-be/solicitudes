package com.semillero.solicitudes.persistence;

import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long> {
    List<SolicitudEntity> findByEmpleadoIdOrderByIdDesc(Long empleadoId);
}
