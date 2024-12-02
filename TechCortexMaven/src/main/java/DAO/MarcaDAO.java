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

    // Método para obtener todas las marcas
    public List<Marca> obtenerMarcas() {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT id, nombre FROM marca";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
                marcas.add(marca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return marcas;
    }

    // Método para obtener una marca por su ID
    public Marca getMarcaById(int id) {
        Marca marca = null;
        String sql = "SELECT id, nombre FROM marca WHERE id = ?";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                marca = new Marca();
                marca.setIdMarca(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return marca;
    }

    // Método para agregar una nueva marca
    public boolean agregarMarca(Marca marca) {
        String sql = "INSERT INTO marca (marca_nom, marca_url_img) VALUES (?, ?)";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, marca.getNombre());
            stmt.setString(2, marca.getUrl_imagen());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para eliminar una marca
    public boolean eliminarMarca(int idMarca) {
        String sql = "DELETE FROM marca WHERE marca_id = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMarca);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
