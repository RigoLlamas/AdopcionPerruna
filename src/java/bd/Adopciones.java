/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rigo y Joshua :)
 */
public class Adopciones {

    private Connection db;

    public Adopciones() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.db = DriverManager.getConnection("jdbc:mysql://localhost/adopcionesperrunas?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    public Connection getConnection() {
        return db;
    }
}
