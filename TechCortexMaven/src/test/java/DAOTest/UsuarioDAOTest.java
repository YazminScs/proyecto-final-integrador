package DAOTest;

import ConexionTest.ConexionTest;
import Modelo.Usuario;
import java.sql.*;
import java.util.*;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAOTest implements IUsuarioDAO {

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (usuario_nom, usuario_email, usuario_pass, usuario_dir, usuario_tel, usuario_rol) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario.getAddress());
            ps.setInt(5, usuario.getPhone());
            ps.setString(6, usuario.getRol());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT usuario_id, usuario_nom, usuario_email, usuario_pass, usuario_rol, usuario_dir, usuario_tel FROM usuario;";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("usuario_id"));
                us.setUsername(rs.getString("usuario_nom"));
                us.setEmail(rs.getString("usuario_email"));
                us.setPassword(rs.getString("usuario_pass"));
                us.setRol(rs.getString("usuario_rol"));
                us.setAddress(rs.getString("usuario_dir"));
                us.setPhone(rs.getInt("usuario_tel"));

                usuarios.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioPorId(int usuario_id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE usuario_id=?";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, usuario_id);  // Mover esto antes de ejecutar la consulta.

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String usuario_nom = rs.getString("usuario_nom");
                    String usuario_email = rs.getString("usuario_email");
                    String usuario_pass = rs.getString("usuario_pass");
                    String usuario_rol = rs.getString("usuario_rol");
                    String usuario_dir = rs.getString("usuario_dir");
                    int usuario_tel = rs.getInt("usuario_tel");

                    usuario = new Usuario(usuario_id, usuario_nom, usuario_email, usuario_pass, usuario_dir, usuario_tel, usuario_rol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET "
                + "usuario_nom = ?, "
                + "usuario_email = ?, "
                + "usuario_pass = ?, "
                + "usuario_rol = ?, "
                + "usuario_dir = ?, "
                + "usuario_tel = ? "
                + "WHERE usuario_id = ?";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getAddress());
            ps.setInt(6, usuario.getPhone());
            ps.setInt(7, usuario.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Usuario> obtenerUsuariosPorRol(String usuario_rol) {
        String sql = "SELECT usuario_id, usuario_nom, usuario_email, usuario_pass, usuario_rol, usuario_dir, usuario_tel "
                + "FROM usuario WHERE usuario_rol = ?";

        List<Usuario> usuarios = new ArrayList<>();

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setString(1, usuario_rol);

            try (ResultSet rs = ps.executeQuery()) {  // Mover el executeQuery después de setear el parámetro
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("usuario_id"));
                    usuario.setUsername(rs.getString("usuario_nom"));
                    usuario.setEmail(rs.getString("usuario_email"));
                    usuario.setPassword(rs.getString("usuario_pass"));
                    usuario.setRol(rs.getString("usuario_rol"));
                    usuario.setAddress(rs.getString("usuario_dir"));
                    usuario.setPhone(rs.getInt("usuario_tel"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public boolean eliminarUsuario(int usuario_id) {
        String sql = "DELETE FROM usuario WHERE usuario_id = ?";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, usuario_id);

            int rowsDeleted = ps.executeUpdate();  // Cambiar a executeUpdate ya que es un DELETE
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}