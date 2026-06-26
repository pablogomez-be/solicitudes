package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLEADO")
public class EmpleadoEntity extends UsuarioEntity {
    private String departamentoTemporal;

    private String administradorTemporal;

    private String equipoTemporal;

    public String getEquipoTemporal() {
        return equipoTemporal;
    }

    public void setEquipoTemporal(String equipoTemporal) {
        this.equipoTemporal = equipoTemporal;
    }

    public String getAdministradorTemporal() {
        return administradorTemporal;
    }

    public void setAdministradorTemporal(String administradorTemporal) {
        this.administradorTemporal = administradorTemporal;
    }

    public String getDepartamentoTemporal() {
        return departamentoTemporal;
    }

    public void setDepartamentoTemporal(String departamentoTemporal) {
        this.departamentoTemporal = departamentoTemporal;
    }
}
