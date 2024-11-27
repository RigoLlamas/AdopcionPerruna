/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Usuarios;

/**
 *
 * @author Rigo
 */
public class UsuariosDAO extends Adopciones {

    public UsuariosDAO() {
        super();
    }

    public void insert(Usuarios u) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "INSERT INTO `usuarios` (Nombre, Apellido, CorreoElectronico, Contrasena, TipoUsuario) VALUES"
                    + "(?,?,?,?,?,?)"
            );
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getContrasena());
            ps.setString(5, u.getContrasena());
            ps.execute();
            System.out.println("Usuario insertado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    public ArrayList<Usuarios> select() {
        ArrayList<Usuarios> u = new ArrayList<Usuarios>();
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "SELECT * FROM `usuarios`"
            );
            ResultSet rs;
            rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                Usuarios usuario = new Usuarios();

                u.add(usuario);
                usuario.setPk_usuario(rs.getInt("PK_Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setCorreo(rs.getString("CorreoElectronico"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("Direccion"));
                usuario.setTipousuario(rs.getString("TipoUsuario"));
                Date fecha = rs.getDate("FechaRegistro");
                if (fecha != null) {
                    usuario.setFecharegistro(fecha.toLocalDate());
                } else {
                    usuario.setFecharegistro(null);
                }
                u.add(usuario);
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return u;
    }
    
    public Usuarios select_user(String username) {
        Usuarios usuario = new Usuarios();
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "SELECT * FROM `usuarios` WHERE CorreoElectronico = ?"
            );
            ps.setString(1, username);
            ResultSet rs;
            rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                usuario.setPk_usuario(rs.getInt("PK_Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setCorreo(rs.getString("CorreoElectronico"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("Direccion"));
                usuario.setTipousuario(rs.getString("TipoUsuario"));
                Date fecha = rs.getDate("FechaRegistro");
                if (fecha != null) {
                    usuario.setFecharegistro(fecha.toLocalDate());
                } else {
                    usuario.setFecharegistro(null);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return usuario;
    }
    
    public Usuarios select_user2(int pkuser) {
        Usuarios usuario = new Usuarios();
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement(
                    "SELECT * FROM `usuarios` WHERE PK_Usuario = ?"
            );
            ps.setInt(1, pkuser);
            ResultSet rs;
            rs = ps.executeQuery();
            System.out.println("Consulta realizada");
            while (rs.next()) {
                usuario.setPk_usuario(rs.getInt("PK_Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setCorreo(rs.getString("CorreoElectronico"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setDireccion(rs.getString("Direccion"));
                usuario.setTipousuario(rs.getString("TipoUsuario"));
                Date fecha = rs.getDate("FechaRegistro");
                if (fecha != null) {
                    usuario.setFecharegistro(fecha.toLocalDate());
                } else {
                    usuario.setFecharegistro(null);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return usuario;
    }

    public String validarUsuario(String email, String contrasena) {
        String tipoUsuario = null;
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT tipoUsuario FROM `usuarios` WHERE CorreoElectronico = ? AND Contrasena = ?"
            );
            ps.setString(1, email);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Recupera el tipo de usuario (por ejemplo, "Administrador" o "Usuario")
                    tipoUsuario = rs.getString("tipoUsuario");
                }
            }
        } catch (Exception e) {
            return tipoUsuario;
        }
        return tipoUsuario;
    }

}
