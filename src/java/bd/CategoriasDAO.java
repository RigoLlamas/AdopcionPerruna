/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Categorias;

/**
 *
 * @author Rigo
 */
public class CategoriasDAO extends Adopciones{
    public CategoriasDAO() {
        super();
    }

    public void insert(Categorias categoria) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                "INSERT INTO `categorias` (TipoMascota, Raza) VALUES (?, ?)"
            );
            ps.setString(1, categoria.getTipoMascota());
            ps.setString(2, categoria.getRaza());
            ps.execute();
            System.out.println("Categoría insertada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar categoría: " + e.getMessage());
        }
    }

    public ArrayList<Categorias> select() {
        ArrayList<Categorias> categorias = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM `categorias`");
            ResultSet rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setPk_categoria(rs.getInt("PK_Categoria"));
                categoria.setTipoMascota(rs.getString("TipoMascota"));
                categoria.setRaza(rs.getString("Raza"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return categorias;
    }

    public void update(Categorias categoria) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                "UPDATE `categorias` SET TipoMascota = ?, Raza = ? WHERE PK_Categoria = ?"
            );
            ps.setString(1, categoria.getTipoMascota());
            ps.setString(2, categoria.getRaza());
            ps.setInt(3, categoria.getPk_categoria());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Categoría actualizada correctamente.");
            } else {
                System.out.println("No se encontró una categoría con el ID especificado.");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar categoría: " + e.getMessage());
        }
    }

    public void delete(int pk_categoria) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement("DELETE FROM `categorias` WHERE PK_Categoria = ?");
            ps.setInt(1, pk_categoria);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Categoría eliminada correctamente.");
            } else {
                System.out.println("No se encontró una categoría con el ID especificado.");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar categoría: " + e.getMessage());
        }
    }
}
