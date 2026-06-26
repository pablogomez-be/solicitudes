package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "SUPERVISOR")
public class SupervisorEntity extends UsuarioEntity {

    private String equipoTemporal;

    public String getEquipoTemporal() {
        return equipoTemporal;
    }

    public void setEquipoTemporal(String equipoTemporal) {
        this.equipoTemporal = equipoTemporal;
    }
}
