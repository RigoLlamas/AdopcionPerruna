/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.time.LocalDateTime;

/**
 *
 * @author Rigo
 */
public class Citas {

    private int pk_cita;
    private int fk_solicitud;
    private LocalDateTime fechaCita;
    private String estadoCita;
    private String notas;

    public int getPk_cita() {
        return pk_cita;
    }

    public void setPk_cita(int pk_cita) {
        this.pk_cita = pk_cita;
    }

    public int getFk_solicitud() {
        return fk_solicitud;
    }

    public void setFk_solicitud(int fk_solicitud) {
        this.fk_solicitud = fk_solicitud;
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
