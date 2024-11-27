/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Citas;

/**
 *
 * @author Rigo
 */
public class CitasDAO extends Adopciones {

    public CitasDAO() {
        super();
    }

    public void insert(Citas cita) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "INSERT INTO `citas` (FK_Solicitud, FechaCita, EstadoCita, Notas) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, cita.getFk_solicitud());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(cita.getFechaCita()));
            ps.setString(3, cita.getEstadoCita());
            ps.setString(4, cita.getNotas());
            ps.execute();
            System.out.println("Cita insertada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar cita: " + e.getMessage());
        }
    }

    public ArrayList<Citas> select() {
        ArrayList<Citas> citas = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `citas`");
            ResultSet rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                Citas cita = new Citas();
                cita.setPk_cita(rs.getInt("PK_Cita"));
                cita.setFk_solicitud(rs.getInt("FK_Solicitud"));
                cita.setFechaCita(rs.getTimestamp("FechaCita").toLocalDateTime());
                cita.setEstadoCita(rs.getString("EstadoCita"));
                cita.setNotas(rs.getString("Notas"));
                citas.add(cita);
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return citas;
    }
    
    public ArrayList<Citas> select_from_usr(int pk_user) {
        ArrayList<Citas> citas = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `citas` WHERE FK_Solicitud = (SELECT PK_Solicitud FROM solicitudesadopcion WHERE FK_Usuario = ?);");
            ps.setInt(1, pk_user);
            ResultSet rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                Citas cita = new Citas();
                cita.setPk_cita(rs.getInt("PK_Cita"));
                cita.setFk_solicitud(rs.getInt("FK_Solicitud"));
                cita.setFechaCita(rs.getTimestamp("FechaCita").toLocalDateTime());
                cita.setEstadoCita(rs.getString("EstadoCita"));
                cita.setNotas(rs.getString("Notas"));
                citas.add(cita);
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return citas;
    }
    
    public int getUserFromCita(int pk_cita){
        int pk_user = 0;
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT solicitudesadopcion.FK_Usuario FROM solicitudesadopcion, citas WHERE solicitudesadopcion.PK_Solicitud = ? AND citas.FK_Solicitud = solicitudesadopcion.PK_Solicitud;");
            ps.setInt(1, pk_cita);
            ResultSet rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                pk_user = rs.getInt("FK_Usuario");
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return pk_user;
    }

    public void update(Citas cita) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "UPDATE `citas` SET FK_Solicitud = ?, FechaCita = ?, EstadoCita = ?, Notas = ? WHERE PK_Cita = ?"
            );
            ps.setInt(1, cita.getFk_solicitud());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(cita.getFechaCita()));
            ps.setString(3, cita.getEstadoCita());
            ps.setString(4, cita.getNotas());
            ps.setInt(5, cita.getPk_cita());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cita actualizada correctamente.");
            } else {
                System.out.println("No se encontró una cita con el ID especificado.");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
        }
    }

    public void delete(int pk_cita) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement("DELETE FROM `citas` WHERE PK_Cita = ?");
            ps.setInt(1, pk_cita);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cita eliminada correctamente.");
            } else {
                System.out.println("No se encontró una cita con el ID especificado.");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
        }
    }
}
