package com.semillero.solicitudes.services.interfaces;

import com.semillero.solicitudes.persistence.entities.SolicitudEntity;

import java.util.List;

public interface ISolicitud {
    List<SolicitudEntity> getAll();

    List<SolicitudEntity> obtenerSolicitudesPorEmpleado(Long empleadoId);

    SolicitudEntity crearSolicitudVacaciones(Long empleadoId, SolicitudEntity nuevaSolicitud);
}
