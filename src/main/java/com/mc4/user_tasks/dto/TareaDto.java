package com.mc4.user_tasks.dto;

import lombok.Data;

public class TareaDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private Long usuarioAsignadoId;

    public TareaDto() {
    }

    public TareaDto(Long id, String titulo, String descripcion, String estado, Long usuarioAsignadoId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarioAsignadoId = usuarioAsignadoId;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUsuarioAsignadoId() {
        return usuarioAsignadoId;
    }

    public void setUsuarioAsignadoId(Long usuarioAsignadoId) {
        this.usuarioAsignadoId = usuarioAsignadoId;
    }

    @Override
    public String toString() {
        return "TareaDto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", usuarioAsignadoId=" + usuarioAsignadoId +
                '}';
    }
}
