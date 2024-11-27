/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Solicitudes;

/**
 *
 * @author Rigo
 */
public class SolicitudesDAO extends Adopciones {

    public SolicitudesDAO() {
        super();
    }

    public void insert(Solicitudes solicitud) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "INSERT INTO `solicitudesadopcion` (FK_Usuario, FK_Mascota, FechaSolicitud, EstadoSolicitud, Comentarios) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setInt(1, solicitud.getFk_usuario());
            ps.setInt(2, solicitud.getFk_mascota());
            ps.setDate(3, java.sql.Date.valueOf(solicitud.getFechaSolicitud()));
            ps.setString(4, solicitud.getEstadoSolicitud());
            ps.setString(5, solicitud.getComentarios());
            ps.execute();
            System.out.println("Solicitud insertada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar solicitud: " + e.getMessage());
        }
    }

    public ArrayList<Solicitudes> select() {
        ArrayList<Solicitudes> solicitudes = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `solicitudesadopcion`");
            ResultSet rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                Solicitudes solicitud = new Solicitudes();
                solicitud.setPk_solicitud(rs.getInt("PK_Solicitud"));
                solicitud.setFk_usuario(rs.getInt("FK_Usuario"));
                solicitud.setFk_mascota(rs.getInt("FK_Mascota"));
                solicitud.setFechaSolicitud(rs.getDate("FechaSolicitud").toLocalDate());
                solicitud.setEstadoSolicitud(rs.getString("EstadoSolicitud"));
                solicitud.setComentarios(rs.getString("Comentarios"));
                solicitudes.add(solicitud);
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return solicitudes;
    }
    
    public ArrayList<Solicitudes> selectfromuser(int userpk) {
        ArrayList<Solicitudes> solicitudes = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `solicitudesadopcion` WHERE FK_Usuario = ?");
            ps.setInt(1, userpk);
            ResultSet rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                Solicitudes solicitud = new Solicitudes();
                solicitud.setPk_solicitud(rs.getInt("PK_Solicitud"));
                solicitud.setFk_usuario(rs.getInt("FK_Usuario"));
                solicitud.setFk_mascota(rs.getInt("FK_Mascota"));
                solicitud.setFechaSolicitud(rs.getDate("FechaSolicitud").toLocalDate());
                solicitud.setEstadoSolicitud(rs.getString("EstadoSolicitud"));
                solicitud.setComentarios(rs.getString("Comentarios"));
                solicitudes.add(solicitud);
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return solicitudes;
    }

    public void update(Solicitudes solicitud) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "UPDATE `solicitudesadopcion` SET FK_Usuario = ?, FK_Mascota = ?, FechaSolicitud = ?, EstadoSolicitud = ?, Comentarios = ? WHERE PK_Solicitud = ?"
            );
            ps.setInt(1, solicitud.getFk_usuario());
            ps.setInt(2, solicitud.getFk_mascota());
            ps.setDate(3, java.sql.Date.valueOf(solicitud.getFechaSolicitud()));
            ps.setString(4, solicitud.getEstadoSolicitud());
            ps.setString(5, solicitud.getComentarios());
            ps.setInt(6, solicitud.getPk_solicitud());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Solicitud actualizada correctamente.");
            } else {
                System.out.println("No se encontró una solicitud con el ID especificado.");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar solicitud: " + e.getMessage());
        }
    }

}
