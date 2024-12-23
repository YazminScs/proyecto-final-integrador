package DAO;

import Conexion.Conexion;
import Modelo.Categoria;
import Modelo.Marca;
import Modelo.Producto;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements IProductoDAO {

    @Override
    public List<Producto> listarProductos() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.producto_id, p.producto_nom, p.producto_des, p.producto_price, p.producto_stock, p.producto_url_img, "
                + "c.categoria_id AS idCategoria, c.categoria_nom AS NombreCategoria, "
                + "m.marca_id AS idMarca, m.marca_nom AS NombreMarca, m.marca_url_img AS urlImagenMarca "
                + "FROM producto p "
                + "INNER JOIN categoria c ON p.categoria_id = c.categoria_id "
                + "INNER JOIN marca m ON p.marca_id = m.marca_id "
                + "ORDER BY p.producto_id; ";
        List<Producto> lista = new ArrayList<>();
        try {
            cnn = new Conexion().getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("producto_id"),
                        rs.getString("producto_nom"),
                        rs.getString("producto_des"),
                        rs.getDouble("producto_price"),
                        rs.getInt("producto_stock"),
                        rs.getString("producto_url_img"),
                        new Categoria(rs.getInt("idCategoria"), rs.getString("NombreCategoria")),
                        new Marca(rs.getInt("idMarca"), rs.getString("NombreMarca"), rs.getString("urlImagenMarca"))
                );
                lista.add(p);

            }
        } catch (SQLException e) {
            out.println("Error: " + e.getMessage());
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

    @Override
    public List<Producto> listarTeclados() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.producto_id, p.producto_nom, p.producto_des, p.producto_price, p.producto_stock, p.producto_url_img, "
                + "c.categoria_id AS idCategoria, c.categoria_nom AS NombreCategoria, "
                + "m.marca_id AS idMarca, m.marca_nom AS NombreMarca, m.marca_url_img AS urlImagenMarca "
                + "FROM producto p "
                + "INNER JOIN categoria c ON p.categoria_id = c.categoria_id "
                + "INNER JOIN marca m ON p.marca_id = m.marca_id "
                + "WHERE c.categoria_id = 1 "
                + "ORDER BY p.producto_id; ";
        List<Producto> lista = new ArrayList<>();
        try {
            cnn = new Conexion().getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("producto_id"),
                        rs.getString("producto_nom"),
                        rs.getString("producto_des"),
                        rs.getDouble("producto_price"),
                        rs.getInt("producto_stock"),
                        rs.getString("producto_url_img"),
                        new Categoria(rs.getInt("idCategoria"), rs.getString("NombreCategoria")),
                        new Marca(rs.getInt("idMarca"), rs.getString("NombreMarca"), rs.getString("urlImagenMarca"))
                );
                lista.add(p);

            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cnn != null) try {
                cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public List<Producto> listarMonitores() {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT p.producto_id, p.producto_nom, p.producto_des, p.producto_price, p.producto_stock, p.producto_url_img, "
                + "c.categoria_id AS idCategoria, c.categoria_nom AS NombreCategoria, "
                + "m.marca_id AS idMarca, m.marca_nom AS NombreMarca, m.marca_url_img AS urlImagenMarca "
                + "FROM producto p "
                + "INNER JOIN categoria c ON p.categoria_id = c.categoria_id "
                + "INNER JOIN marca m ON p.marca_id = m.marca_id "
                + "WHERE c.categoria_id = 4 "
                + "ORDER BY RAND() LIMIT 8;";

        List<Producto> lista = new ArrayList<>();
        try {
            cnn = new Conexion().getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("producto_id"),
                        rs.getString("producto_nom"),
                        rs.getString("producto_des"),
                        rs.getDouble("producto_price"),
                        rs.getInt("producto_stock"),
                        rs.getString("producto_url_img"),
                        new Categoria(rs.getInt("idCategoria"), rs.getString("NombreCategoria")),
                        new Marca(rs.getInt("idMarca"), rs.getString("NombreMarca"), rs.getString("urlImagenMarca"))
                );
                lista.add(p);

            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cnn != null) try {
                cnn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public boolean actualizarProducto(Producto p) {
        boolean resultado = false;
        Connection cnn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE producto SET producto_nom=?, producto_des=?, producto_price=?, producto_stock=?, producto_url_imagen=?, categoria_id=?, marca_id=? WHERE producto_id=?";
        try {
            cnn = Conexion.getConexion();
            ps = cnn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getUrl_imagen());
            ps.setInt(6, p.getCategorias().getIdCategoria());
            ps.setInt(7, p.getMarcas().getIdMarca());
            ps.setInt(8, p.getIdProducto());
            int cto = ps.executeUpdate();
            if (cto > 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            out.println("Error: " + e.getMessage());
        } finally {
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
        return resultado;
    }

    @Override
    public Producto obtenerPorId(int id) {
        Producto producto = null;

        String sql = "SELECT p.producto_id, p.producto_nom, p.producto_des, p.producto_price, p.producto_stock, p.producto_url_img, "
                + "c.categoria_id AS categoria_id, c.categoria_nom AS categoria_nom, "
                + "m.marca_id AS marca_id, m.marca_nom AS marca_nom "
                + "FROM producto p "
                + "JOIN categoria c ON p.categoria_id = c.categoria_id "
                + "JOIN marca m ON p.marca_id = m.marca_id "
                + "WHERE p.producto_id = ?";

        try (Connection conexion = Conexion.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Crear y llenar el producto
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("producto_id"));
                    producto.setNombre(rs.getString("producto_nom"));
                    producto.setDescripcion(rs.getString("producto_des"));
                    producto.setPrecio(rs.getDouble("producto_price"));
                    producto.setStock(rs.getInt("producto_stock"));
                    producto.setUrl_imagen(rs.getString("producto_url_img"));

                    // Crear y llenar la categoría
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(rs.getInt("categoria_id"));
                    categoria.setNombre(rs.getString("categoria_nom"));
                    producto.setCategorias(categoria);

                    // Crear y llenar la marca
                    Marca marca = new Marca();
                    marca.setIdMarca(rs.getInt("marca_id"));
                    marca.setNombre(rs.getString("marca_nom"));
                    producto.setMarcas(marca);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public boolean actualizarStock(int producto_stock, int producto_id) {
        String sql = "UPDATE producto SET producto_stock = ? WHERE producto_id = ?";
        try (Connection conexion = Conexion.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, producto_stock);
            ps.setInt(2, producto_id);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un producto
    public boolean actualizarProductos(Producto producto) {
        String sql = "UPDATE producto SET producto_nom = ?, producto_des = ?, producto_price = ?, producto_stock = ?, producto_url_img = ?, categoria_id = ?, marca_id = ? WHERE producto_id = ?";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los valores para cada parámetro de la consulta
            stmt.setString(1, producto.getNombre()); // nombre del producto
            stmt.setString(2, producto.getDescripcion()); // descripción del producto
            stmt.setDouble(3, producto.getPrecio()); // precio del producto
            stmt.setInt(4, producto.getStock()); // stock del producto
            stmt.setString(5, producto.getUrl_imagen()); // URL de la imagen
            stmt.setInt(6, producto.getCategorias().getIdCategoria()); // ID de la categoría
            stmt.setInt(7, producto.getMarcas().getIdMarca()); // ID de la marca
            stmt.setInt(8, producto.getIdProducto()); // ID del producto para la condición WHERE

            // Ejecutar la actualización
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Devuelve true si se actualizó el producto, false si no
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // En caso de error, devuelve false
        }
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM producto WHERE producto_id = ?";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProducto);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrarProducto(Producto producto) {
        String sql = "INSERT INTO producto (producto_nom, producto_des, producto_price, producto_stock, producto_url_img, categoria_id, marca_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setString(5, producto.getUrl_imagen());
            stmt.setInt(6, producto.getCategorias().getIdCategoria());
            stmt.setInt(7, producto.getMarcas().getIdMarca());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int contarProductos() {
        String sql = "SELECT COUNT(*) FROM producto";
        int count = 0;

        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al contar productos", e);
        }

        return count;
    }
}
