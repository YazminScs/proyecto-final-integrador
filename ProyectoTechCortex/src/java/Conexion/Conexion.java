package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion() {
        Connection conn = null;
        var baseDatos = "tiendita_foley";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var user = "root";
        var password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Se realizo la conexion");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectarse a la bd: " + e.getMessage());
        }
        return conn;
    }
}