package com.semillero.solicitudes.services;

import com.semillero.solicitudes.persistence.EmpleadoRepository;
import com.semillero.solicitudes.persistence.SolicitudRepository;
import com.semillero.solicitudes.persistence.entities.EmpleadoEntity;
import com.semillero.solicitudes.persistence.entities.SolicitudEntity;
import com.semillero.solicitudes.services.interfaces.ISolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SolicitudService implements ISolicitud {
    SolicitudRepository solicitudRepository;
    EmpleadoRepository empleadoRepository;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository, EmpleadoRepository empleadoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<SolicitudEntity> getAll() {
        return solicitudRepository.findAll();
    }

    @Override
    public List<SolicitudEntity> obtenerSolicitudesPorEmpleado(Long empleadoId) {
        return solicitudRepository.findByEmpleadoIdOrderByIdDesc(empleadoId);
    }

    @Override
    public SolicitudEntity crearSolicitudVacaciones(Long empleadoId, SolicitudEntity nuevaSolicitud) {
        EmpleadoEntity empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("El empleado no existe"));

        LocalDate hoy = LocalDate.now();
        LocalDate fechaInicio = nuevaSolicitud.getFechaInicio();
        LocalDate fechaFin = nuevaSolicitud.getFechaFin();
        LocalDate fechaIngreso = new java.sql.Date(empleado.getFechaIngreso().getTime()).toLocalDate();

        if (fechaInicio.isBefore(hoy) || fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("Rango de fechas inválido.");
        }

        long mesesLaborados = ChronoUnit.MONTHS.between(fechaIngreso, hoy);
        if (mesesLaborados < 2) {
            throw new IllegalArgumentException("El empleado no ha cumplido el periodo de prueba de 2 meses.");
        }

        long diasAnticipacion = ChronoUnit.DAYS.between(hoy, fechaInicio);
        if (diasAnticipacion < 15) {
            throw new IllegalArgumentException("Las vacaciones deben solicitarse con al menos 15 días de anticipación.");
        }

        double diasDisponibles = calcularDiasVacacionesDisponibles(fechaIngreso, hoy);
        long diasHabilesSolicitados = calcularDiasHabiles(fechaInicio, fechaFin);

        if (diasHabilesSolicitados > diasDisponibles) {
            throw new IllegalArgumentException("Días insuficientes. Días solicitados: " + diasHabilesSolicitados +
                    ". Días disponibles acumulados: " + (int) diasDisponibles);
        }

        if (diasDisponibles >= 6 && diasHabilesSolicitados < 6) {
            throw new IllegalArgumentException("Debe solicitar un mínimo de 6 días hábiles consecutivos por políticas de la empresa.");
        }

        nuevaSolicitud.setEmpleado(empleado);
        nuevaSolicitud.setEstado("PENDIENTE");

        return solicitudRepository.save(nuevaSolicitud);
    }

    private long calcularDiasHabiles(LocalDate inicio, LocalDate fin) {
        long diasHabiles = 0;
        LocalDate fechaActual = inicio;

        while (!fechaActual.isAfter(fin)) {
            DayOfWeek diaSemana = fechaActual.getDayOfWeek();
            if (diaSemana != DayOfWeek.SATURDAY && diaSemana != DayOfWeek.SUNDAY) {
                diasHabiles++;
            }
            fechaActual = fechaActual.plusDays(1);
        }
        return diasHabiles;
    }

    private double calcularDiasVacacionesDisponibles(LocalDate fechaIngreso, LocalDate hoy) {
        long diasTrabajados = ChronoUnit.DAYS.between(fechaIngreso, hoy);
        return (diasTrabajados * 15.0) / 360.0;
    }

}
