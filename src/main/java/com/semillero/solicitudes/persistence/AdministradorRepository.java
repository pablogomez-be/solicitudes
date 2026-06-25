package com.semillero.solicitudes.persistence;

import com.semillero.solicitudes.persistence.entities.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<AdministradorEntity, Long> {
}
