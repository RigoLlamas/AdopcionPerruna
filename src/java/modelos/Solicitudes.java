/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.time.LocalDate;

/**
 *
 * @author Rigo
 */
public class Solicitudes {
    private String estadoSolicitud,
            comentarios;
    private int pk_solicitud,
            fk_usuario,
            fk_mascota;
    private LocalDate fechaSolicitud;
    
    public int getPk_solicitud() {
        return pk_solicitud;
    }

    public void setPk_solicitud(int pk_solicitud) {
        this.pk_solicitud = pk_solicitud;
    }

    public int getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public int getFk_mascota() {
        return fk_mascota;
    }

    public void setFk_mascota(int fk_mascota) {
        this.fk_mascota = fk_mascota;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
