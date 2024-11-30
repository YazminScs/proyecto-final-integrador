package DAO;

import Conexion.Conexion;
import Modelo.Carrito;
import Modelo.DetalleCarrito;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalleCarritoDAO implements IDetalleCarritoDAO {

    @Override
    public boolean registrarDetalleCarrito(int detalle_cant, double detalle_price, Carrito carrito, Producto producto) {
        int carrito_id = carrito.getCarrito_id();
        int producto_id = producto.getIdProducto();

        String sql = "INSERT INTO detalle_carrito (detalle_cant, detalle_price, carrito_id, producto_id) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, detalle_cant);
            ps.setDouble(2, detalle_price);
            ps.setInt(3, carrito_id);
            ps.setInt(4, producto_id);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validarProductoEnCarrito(Carrito carrito, Producto producto) {
        int carrito_id = carrito.getCarrito_id();
        int producto_id = producto.getIdProducto();

        String sql = "SELECT COUNT(*) FROM detalle_carrito WHERE carrito_id = ? AND producto_id = ?";
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);
            ps.setInt(2, producto_id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<DetalleCarrito> listarDetalleCarritoPorId(int carrito_id) {
        List<DetalleCarrito> detalles = new ArrayList<>();
        CarritoDAO carritoDAO = new CarritoDAO();
        ProductoDAO productoDAO = new ProductoDAO();

        String sql = "SELECT * FROM detalle_carrito WHERE carrito_id = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleCarrito detalle = new DetalleCarrito();
                detalle.setCarrito(carritoDAO.obtenerCarritoPorId(carrito_id));
                int producto_id = rs.getInt("producto_id");
                detalle.setProducto(productoDAO.obtenerPorId(producto_id));
                detalle.setDetalle_cant(rs.getInt("detalle_cant"));
                detalle.setDetalle_price(rs.getDouble("detalle_price"));

                detalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new SQLException("Error al obtener los detalles del carrito", e);
            } catch (SQLException ex) {
                Logger.getLogger(DetalleCarritoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return detalles;
    }

    @Override
    public boolean actualizarCantidad(int detalle_cant, Producto producto, Carrito carrito) {
        int carrito_id = carrito.getCarrito_id();
        int producto_id = producto.getIdProducto();

        String sql = "UPDATE detalle_carrito SET detalle_cant = ? WHERE carrito_id = ? AND producto_id = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, detalle_cant);
            ps.setInt(2, carrito_id);
            ps.setInt(3, producto_id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarProducto(Carrito carrito, Producto producto) {
        int carrito_id = carrito.getCarrito_id();
        int producto_id = producto.getIdProducto();

        String sql = "DELETE FROM detalle_carrito WHERE carrito_id = ? AND producto_id = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);
            ps.setInt(2, producto_id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (SQLException ex) {
                Logger.getLogger(DetalleCarritoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public double obtenerTotalPorId(int carrito_id) {
        String sql = "SELECT SUM(detalle_price * detalle_cant) FROM detalle_carrito WHERE carrito_id = ?";
        double total = 0.0;

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public boolean eliminarDetallePorId(int carrito_id) {
        String sql = "DELETE FROM detalle_carrito WHERE carrito_id = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);
            
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
