/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.time.LocalDateTime;

/**
 *
 * @author Joshua Romo
 */
public class Cita {
    private int NCita;
    private int NPeticion;
    private String user;
    private LocalDateTime fechaCita;
    private String estadoCita;
    private String notas;
    
    public Cita(Citas c, Usuarios u){
        this.NCita = c.getPk_cita();
        this.NPeticion = c.getFk_solicitud();
        this.user = u.getNombre() + " " + u.getApellido() + " / " + u.getCorreo();
        this.fechaCita = c.getFechaCita();
        this.estadoCita = c.getEstadoCita();
        this.notas = c.getNotas();
    }

    public int getNCita() {
        return NCita;
    }

    public void setNCita(int NCita) {
        this.NCita = NCita;
    }

    public int getNPeticion() {
        return NPeticion;
    }

    public void setNPeticion(int NPeticion) {
        this.NPeticion = NPeticion;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
    
    
}
