package com.semillero.solicitudes.services;

import com.semillero.solicitudes.persistence.EmpleadoRepository;
import com.semillero.solicitudes.persistence.entities.EmpleadoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<EmpleadoEntity> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<EmpleadoEntity> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public EmpleadoEntity guardar(EmpleadoEntity empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

    public EmpleadoEntity actualizar(Long id, EmpleadoEntity datosActualizados) {
        return empleadoRepository.findById(id).map(empleadoExistente -> {
            empleadoExistente.setTipoDocumento(datosActualizados.getTipoDocumento());
            empleadoExistente.setCodigo(datosActualizados.getCodigo());
            empleadoExistente.setNombre(datosActualizados.getNombre());
            empleadoExistente.setApellido(datosActualizados.getApellido());
            empleadoExistente.setTelefono(datosActualizados.getTelefono());
            empleadoExistente.setDireccion(datosActualizados.getDireccion());
            empleadoExistente.setTipoContrato(datosActualizados.getTipoContrato());
            empleadoExistente.setFechaIngreso(datosActualizados.getFechaIngreso());
            empleadoExistente.setDepartamentoTemporal(datosActualizados.getDepartamentoTemporal());

            return empleadoRepository.save(empleadoExistente);
        }).orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    }

}
