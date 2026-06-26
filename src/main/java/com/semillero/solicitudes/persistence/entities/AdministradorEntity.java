package com.semillero.solicitudes.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ADMINISTRADOR")
public class AdministradorEntity extends UsuarioEntity {
    private String empleadosTemporal;

    public String getEmpleadosTemporal() {
        return empleadosTemporal;
    }

    public void setEmpleadosTemporal(String empleadosTemporal) {
        this.empleadosTemporal = empleadosTemporal;
    }
}
