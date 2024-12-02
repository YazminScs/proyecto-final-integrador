package DAO;

import Conexion.Conexion;
import Modelo.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICategoriaDAO {

    @Override
    public List<Categoria> listarCategoria() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT categoria_id, categoria_nom FROM categoria;";
        List<Categoria> lista = new ArrayList<>();

        try {
            cnn = new Conexion().getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("categoria_id"));
                c.setNombre(rs.getString("categoria_nom"));
                lista.add(c);
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

    // Método para obtener todas las categorías
    public List<Categoria> obtenerCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre FROM categoria";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    // Método para obtener una categoría por su ID
    public Categoria getCategoriaById(int id) {
        Categoria categoria = null;
        String sql = "SELECT id, nombre FROM categoria WHERE id = ?";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    private static final String ACTUALIZAR = "UPDATE categoria SET categoria_nom = ? WHERE categoria_id = ?";
    private static final String ELIMINAR = "DELETE FROM categoria WHERE categoria_id = ?";
    private static final String LISTAR = "SELECT * FROM categoria";

    // Agregar Categoría
    public boolean agregarCategoria(Categoria categoria) {

        String sql = "INSERT INTO categoria (categoria_nom) VALUES (?)";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Editar Categoría
    public boolean actualizarCategoria(Categoria categoria) {
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getIdCategoria());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar Categoría
    public boolean eliminarCategoria(int idCategoria) {
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(ELIMINAR)) {
            stmt.setInt(1, idCategoria);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar Categorías
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(LISTAR); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("categoria_id"));
                categoria.setNombre(rs.getString("categoria_nom"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
