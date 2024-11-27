/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Mascotas;

/**
 *
 * @author Rigo
 */
public class MascotasDAO extends Adopciones {

    public MascotasDAO() {
        super();
    }

    public void insert(Mascotas m) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "INSERT INTO ´mascotas´ (Nombre, Edad, Sexo, Descripcion, FechaIngreso, FK_Categoria, Estado) VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getEdad());
            ps.setString(3, m.getSexo());
            ps.setString(4, m.getDescripcion());
            ps.setDate(5, java.sql.Date.valueOf(m.getFechaIngreso()));
            ps.setInt(6, m.getFk_categoria());
            ps.setString(7, m.getEstado());
            ps.execute();
            System.out.println("Mascota insertada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar mascota: " + e.getMessage());
        }
    }

    public ArrayList<Mascotas> select() {
        ArrayList<Mascotas> m = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `mascotas`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mascotas mascota = new Mascotas();
                mascota.setPk_mascota(rs.getInt("PK_Mascota"));
                mascota.setNombre(rs.getString("Nombre"));
                mascota.setEdad(rs.getInt("Edad"));
                mascota.setSexo(rs.getString("Sexo"));
                mascota.setDescripcion(rs.getString("Descripcion"));
                mascota.setImagen(rs.getString("ImagenURL"));
                Date fecha = rs.getDate("FechaIngreso");
                if (fecha != null) {
                    mascota.setFechaIngreso(fecha.toLocalDate());
                } else {
                    mascota.setFechaIngreso(null);
                }
                mascota.setFk_categoria(rs.getInt("FK_Categoria"));
                mascota.setEstado(rs.getString("Estado"));
                m.add(mascota);
            }
        } catch (Exception e) {
        }
        return m;
    }
    
    public Mascotas select_mascota(int fkmascota) {
        Mascotas mascota = new Mascotas();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `mascotas` WHERE PK_Mascota = ?");
            ps.setInt(1, fkmascota);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mascota.setPk_mascota(rs.getInt("PK_Mascota"));
                mascota.setNombre(rs.getString("Nombre"));
                mascota.setEdad(rs.getInt("Edad"));
                mascota.setSexo(rs.getString("Sexo"));
                mascota.setDescripcion(rs.getString("Descripcion"));
                mascota.setImagen(rs.getString("ImagenURL"));
                Date fecha = rs.getDate("FechaIngreso");
                if (fecha != null) {
                    mascota.setFechaIngreso(fecha.toLocalDate());
                } else {
                    mascota.setFechaIngreso(null);
                }
                mascota.setFk_categoria(rs.getInt("FK_Categoria"));
                mascota.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
        }
        return mascota;
    }

    public Mascotas obtenerMascota(int pk_mascota) {
        Mascotas m = new Mascotas();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `mascotas` WHERE PK_Mascota = ?");
            ps.setInt(1, pk_mascota);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m.setPk_mascota(rs.getInt("PK_Mascota"));
                m.setNombre(rs.getString("Nombre"));
                m.setEdad(rs.getInt("Edad"));
                m.setSexo(rs.getString("Sexo"));
                m.setDescripcion(rs.getString("Descripcion"));
                m.setImagen(rs.getString("ImagenURL"));
                Date fecha = rs.getDate("FechaIngreso");
                if (fecha != null) {
                    m.setFechaIngreso(fecha.toLocalDate());
                } else {
                    m.setFechaIngreso(null);
                }
                m.setFk_categoria(rs.getInt("FK_Categoria"));
                m.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
        }
        return m;
    }

    public boolean update(Mascotas m) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "UPDATE ´mascotas´ SET Nombre = ?, Edad = ?, Sexo = ?, Descripcion = ?, FechaIngreso = ?, FK_Categoria = ?, Estado = ? WHERE PK_Mascota = ?"
            );
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getEdad());
            ps.setString(3, m.getSexo());
            ps.setString(4, m.getDescripcion());
            ps.setDate(5, java.sql.Date.valueOf(m.getFechaIngreso()));
            ps.setInt(6, m.getFk_categoria());
            ps.setString(7, m.getEstado());
            ps.setInt(8, m.getPk_mascota());
            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void delete(int pk_mascota) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "DELETE FROM ´mascotas´ WHERE PK_Mascota = ?"
            );
            ps.setInt(1, pk_mascota);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mascota eliminada correctamente.");
            } else {
                System.out.println("No se encontró una mascota con el ID especificado.");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar mascota: " + e.getMessage());
        }
    }

}
