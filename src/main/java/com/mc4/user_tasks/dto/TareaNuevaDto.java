package com.mc4.user_tasks.dto;

public class TareaNuevaDto {
    private String titulo;
    private String descripcion;
    private String estadoTarea;
    private String email;

    public TareaNuevaDto() {
    }

    public TareaNuevaDto(String titulo, String descripcion, String estadoTarea, String email) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoTarea = estadoTarea;
        this.email = email;
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

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TareaNuevaDto{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estadoTarea='" + estadoTarea + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
