package DAO;

import Conexion.Conexion;
import Modelo.Carrito;
import Modelo.MetodosPago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenDAO implements IOrdenDAO {

    @Override
    public boolean registrarOrden(Carrito carrito, MetodosPago pago, String orden_estado) {
        int carrito_id = carrito.getCarrito_id();
        int pago_id=pago.getPago_id();
        
        String sql = "INSERT INTO orden (carrito_id, pago_id, orden_estado) "
                + "VALUES (?, ?, ?)";
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);
            ps.setInt(2, pago_id);
            ps.setString(3, "Pendiente");
            
            int rowsAffected = ps.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
