package DAO;

import Conexion.Conexion;
import Modelo.Carrito;
import Modelo.MetodosPago;
import Modelo.Orden;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdenDAO implements IOrdenDAO {

    @Override
    public boolean registrarOrden(Carrito carrito, MetodosPago pago, String orden_estado) {
        int carrito_id = carrito.getCarrito_id();
        int pago_id = pago.getPago_id();

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

    @Override
    public Orden obtenerOrdenPorCarrito(Carrito carrito) {
        Orden orden = null;
        int carrito_id = carrito.getCarrito_id();
        MetodoPagoDAO pagoDAO = new MetodoPagoDAO();
        String sql = "SELECT * FROM orden WHERE carrito_id = ?";
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int orden_id = rs.getInt("orden_id");
                int pago_id = rs.getInt("pago_id");
                String orden_estado = rs.getString("orden_estado");

                orden = new Orden(orden_id, carrito, pagoDAO.obtenerPagoPorId(pago_id), orden_estado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orden;
    }
}
