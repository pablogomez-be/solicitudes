package com.semillero.solicitudes.persistence;

import com.semillero.solicitudes.persistence.entities.SupervisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<SupervisorEntity, Long> {
}
