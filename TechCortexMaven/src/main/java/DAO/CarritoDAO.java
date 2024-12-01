package DAO;

import Conexion.Conexion;
import Modelo.Carrito;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarritoDAO implements ICarritoDAO {

    DetalleCarritoDAO detalleDAO = new DetalleCarritoDAO();

    @Override
    public boolean registrarCarrito(Usuario usuario) {
        int usuario_id = usuario.getId();
        String sql = "INSERT INTO carrito (usuario_id) "
                + "VALUES (?)";
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, usuario_id);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int obtenerUltimoIdCarritoPorUsuario(Usuario usuario) {
        int carrito_id = 0;
        int usuario_id = usuario.getId();
        String sql = "SELECT carrito_id FROM carrito "
                + "WHERE usuario_id = ? "
                + "ORDER BY carrito_fecha DESC "
                + "LIMIT 1;";
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setInt(1, usuario_id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                carrito_id = resultSet.getInt("carrito_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carrito_id;
    }

    @Override
    public Carrito obtenerCarritoPorId(int carrito_id) {
        Carrito carrito = null;
        String sql = "SELECT * FROM carrito WHERE carrito_id = ?";
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setInt(1, carrito_id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                Date carrito_fecha = resultSet.getDate("carrito_fecha");
                int usuario_id = resultSet.getInt("usuario_id");
                double carrito_total = resultSet.getDouble("carrito_total");

                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuario_id);
                carrito = new Carrito(carrito_id, carrito_fecha, usuario, carrito_total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carrito;
    }

    @Override
    public boolean actualizarCarrito(double carrito_total, int carrito_id) {
        String sql = "UPDATE carrito SET carrito_total = ? WHERE carrito_id = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setDouble(1, carrito_total);
            ps.setInt(2, carrito_id);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCarritoPorId(int carrito_id) {
        String sql = "DELETE FROM carrito WHERE carrito_id = ?";
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, carrito_id);

            detalleDAO.eliminarDetallePorId(carrito_id);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Carrito> obtenerCarritosPorUsuario(Usuario usuario) {
        List<Carrito> carritos = new ArrayList<>();
        int usuario_id = usuario.getId();

        String sql = "SELECT * FROM carrito WHERE usuario_id = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, usuario_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carrito carrito = new Carrito();
                carrito.setCarrito_id(rs.getInt("carrito_id"));
                carrito.setCarrito_fecha(rs.getDate("carrito_fecha"));
                carrito.setCarrito_total(rs.getDouble("carrito_total"));
                carrito.setUsuario(usuario);
                
                carritos.add(carrito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new SQLException("Error al obtener los datos del carrito", e);
            } catch (SQLException ex) {
                Logger.getLogger(CarritoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return carritos;
    }
}
