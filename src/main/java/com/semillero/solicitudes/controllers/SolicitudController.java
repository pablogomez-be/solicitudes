package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.services.interfaces.ISolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SolicitudController {

    private final ISolicitud solicitudService;

    @Autowired
    public SolicitudController(ISolicitud solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/api/v1/empleados/{empleadoId}/solicitudes")
    public ResponseEntity<SolicitudEntity> crearSolicitudVacaciones(
            @PathVariable Long empleadoId,
            @RequestBody SolicitudEntity nuevaSolicitud) {

        SolicitudEntity solicitudCreada = solicitudService.crearSolicitudVacaciones(empleadoId, nuevaSolicitud);

        return new ResponseEntity<>(solicitudCreada, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/empleados/{empleadoId}/solicitudes")
    public ResponseEntity<List<SolicitudEntity>> obtenerSolicitudesPorEmpleado(@PathVariable Long empleadoId) {

        List<SolicitudEntity> solicitudes = solicitudService.obtenerSolicitudesPorEmpleado(empleadoId);

        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/api/v1/solicitudes")
    public ResponseEntity<List<SolicitudEntity>> getAll() {
        return ResponseEntity.ok(solicitudService.getAll());
    }
}
