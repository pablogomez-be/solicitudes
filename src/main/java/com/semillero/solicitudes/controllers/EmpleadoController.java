package com.semillero.solicitudes.controllers;

import com.semillero.solicitudes.persistence.entities.EmpleadoEntity;
import com.semillero.solicitudes.services.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoEntity>> obtenerTodos() {
        List<EmpleadoEntity> empleados = empleadoService.listarTodos();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> obtenerPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id)
                .map(empleado -> ResponseEntity.ok(empleado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpleadoEntity> crear(@RequestBody EmpleadoEntity nuevoEmpleado) {
        EmpleadoEntity empleadoCreado = empleadoService.guardar(nuevoEmpleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> actualizar(@PathVariable Long id, @RequestBody EmpleadoEntity empleado) {
        return ResponseEntity.ok(empleadoService.actualizar(id, empleado));
    }

}
