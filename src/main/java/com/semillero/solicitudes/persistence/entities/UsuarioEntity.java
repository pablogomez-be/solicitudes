package com.semillero.solicitudes.persistence.entities;

import com.semillero.solicitudes.persistence.entities.enums.TipoContrato;
import com.semillero.solicitudes.persistence.entities.enums.TipoDocumento;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int documento;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    private String codigo;
    private String nombre;
    private String apellido;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;

    private String direccion;

    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Temporal(TemporalType.DATE)
    private Date fechaSalida;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getDocumento() { return documento; }
    public void setDocumento(int documento) { this.documento = documento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido ) { this.apellido = apellido; }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
