package DAO;

import Conexion.Conexion;
import Modelo.Carrito;
import Modelo.MetodosPago;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class MetodoPagoDAO implements IMetodoPagoDAO {

    @Override
    public MetodosPago obtenerPagoPorId(int pago_id) {
        MetodosPago pago = null;
        String sql = "SELECT * FROM metodos_pago WHERE pago_id = ?";
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setInt(1, pago_id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String pago_nom = resultSet.getString("pago_nom");

                pago = new MetodosPago(pago_id, pago_nom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pago;
    }
}
