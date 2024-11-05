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
        String sql = "SELECT p.id, p.nombre, p.descripcion, p.precio, p.stock, p.url_imagen, "
                + "c.id AS idCategoria, c.nombre AS NombreCategoria, "
                + "m.id AS idMarca, m.nombre AS NombreMarca, m.url_imagen AS urlImagenMarca "
                + "FROM productos p "
                + "INNER JOIN categorias c ON p.categoria_id = c.id "
                + "INNER JOIN marcas m ON p.marca_id = m.id "
                + "ORDER BY p.id; ";
        List<Producto> lista = new ArrayList<>();
        try {
            cnn = new Conexion().getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("url_imagen"),
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
        String sql = "SELECT p.id, p.nombre, p.descripcion, p.precio, p.stock, p.url_imagen, "
                + "c.id AS idCategoria, c.nombre AS NombreCategoria, "
                + "m.id AS idMarca, m.nombre AS NombreMarca, m.url_imagen AS urlImagenMarca "
                + "FROM productos p "
                + "INNER JOIN categorias c ON p.categoria_id = c.id "
                + "INNER JOIN marcas m ON p.marca_id = m.id "
                + "WHERE c.id = 1 "
                + "ORDER BY p.id; ";
        List<Producto> lista = new ArrayList<>();
        try {
            cnn = new Conexion().getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("url_imagen"),
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
        String sql = "SELECT p.id, p.nombre, p.descripcion, p.precio, p.stock, p.url_imagen, "
                + "c.id AS idCategoria, c.nombre AS NombreCategoria, "
                + "m.id AS idMarca, m.nombre AS NombreMarca, m.url_imagen AS urlImagenMarca "
                + "FROM productos p "
                + "INNER JOIN categorias c ON p.categoria_id = c.id "
                + "INNER JOIN marcas m ON p.marca_id = m.id "
                + "WHERE c.id = 4 "
                + "ORDER BY RAND() LIMIT 8;";

        List<Producto> lista = new ArrayList<>();
        try {
            cnn = new Conexion().getConexion();
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("url_imagen"),
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
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, url_imagen=?, categoria_id=?, marca_id=? WHERE id=?";
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
}