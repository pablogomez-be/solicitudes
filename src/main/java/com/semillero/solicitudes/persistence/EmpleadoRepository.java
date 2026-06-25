package com.semillero.solicitudes.persistence;

import com.semillero.solicitudes.persistence.entities.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long>  {
}
