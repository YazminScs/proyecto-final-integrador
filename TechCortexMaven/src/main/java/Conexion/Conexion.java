package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion() {
        Connection conn = null;
        String baseDatos = "tiendita_foley";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String user = "root";
        String password = "root";
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