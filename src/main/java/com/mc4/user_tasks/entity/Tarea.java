package com.mc4.user_tasks.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_tarea", nullable = false, unique = false)
    private EstadoTarea estadoTarea = EstadoTarea.PENDIENTE;

    @Column(name = "estado")
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioAsignado;

    public enum EstadoTarea {
        PENDIENTE, EN_PROGRESO, COMPLETADA
    }

    public void avanzarEstado() {
        if (this.estadoTarea == EstadoTarea.PENDIENTE) {
            this.estadoTarea = EstadoTarea.EN_PROGRESO;
        } else if (this.estadoTarea == EstadoTarea.EN_PROGRESO) {
            this.estadoTarea = EstadoTarea.COMPLETADA;
        } else {
            throw new IllegalStateException("No se puede avanzar más el estado.");
        }
    }

    public void retrocederEstado() {
        if (this.estadoTarea == EstadoTarea.COMPLETADA) {
            this.estadoTarea = EstadoTarea.EN_PROGRESO;
        } else if (this.estadoTarea == EstadoTarea.EN_PROGRESO) {
            this.estadoTarea = EstadoTarea.PENDIENTE;
        } else {
            throw new IllegalStateException("No se puede retroceder más el estado.");
        }
    }

    public Tarea() {
    }

    public Tarea(Long id, String titulo, String descripcion, EstadoTarea estadoTarea, Usuario usuarioAsignado, boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoTarea = estadoTarea;
        this.usuarioAsignado = usuarioAsignado;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoTarea getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(EstadoTarea estadoTarea) {
        this.estadoTarea = estadoTarea;
    }


    public Usuario getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(Usuario usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", usuarioAsignado=" + usuarioAsignado +
                '}';
    }
}
