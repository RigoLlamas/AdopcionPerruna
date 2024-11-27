/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.time.LocalDate;

/**
 *
 * @author Joshua Romo
 */
public class Peticion {
    private int NSolicitud;
    private String usuario;
    private String mascota;
    private LocalDate fechaSolicitud;
    private String estado;
    private String comentarios;

    public Peticion(Solicitudes solicitud, Mascotas mascota, Usuarios u) {
        this.NSolicitud = solicitud.getPk_solicitud();
        this.usuario = "" + u.getNombre() + " " + u.getApellido() + " / " + u.getCorreo();
        this.mascota = "" + mascota.getNombre();
        this.fechaSolicitud = solicitud.getFechaSolicitud();
        this.estado = solicitud.getEstadoSolicitud();
        this.comentarios = solicitud.getComentarios();
    }

    public int getNSolicitud() {
        return NSolicitud;
    }

    public void setNSolicitud(int NSolicitud) {
        this.NSolicitud = NSolicitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    
    
    
}
