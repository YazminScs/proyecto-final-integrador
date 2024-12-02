package DAO;

import Conexion.Conexion;
import Modelo.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO implements IMarcaDAO {

    @Override
    public List<Marca> listarMarcas() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT marca_id, marca_nom, marca_url_img FROM marca;";
        List<Marca> lista = new ArrayList<>();

        try {
            cnn = Conexion.getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Marca m = new Marca();

                m.setIdMarca(rs.getInt("marca_id"));
                m.setNombre(rs.getString("marca_nom"));
                m.setUrl_imagen(rs.getString("marca_url_img"));

                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (cnn != null) try {
                cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}