package DAOTest;

import ConexionTest.ConexionTest;
import Modelo.Usuario;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTest {

    public boolean registrarUsuario(Usuario usuario) {
        UsuarioValidator.validarUsuario(usuario);
        System.out.println("Validaciones completadas para el usuario: " + usuario.getUsername());

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
            System.out.println("Usuario registrado: " + usuario.getUsername() + ". Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + usuario.getUsername());
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT usuario_id, usuario_nom, usuario_email, usuario_pass, usuario_dir, usuario_tel, usuario_rol FROM usuario";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("usuario_id"));
                us.setUsername(rs.getString("usuario_nom"));
                us.setEmail(rs.getString("usuario_email"));
                us.setPassword(rs.getString("usuario_pass"));
                us.setAddress(rs.getString("usuario_dir"));
                us.setPhone(rs.getInt("usuario_tel"));
                us.setRol(rs.getString("usuario_rol"));

                usuarios.add(us);
                System.out.println("Usuario recuperado: " + us.getUsername());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios.");
            e.printStackTrace();
        }

        System.out.println("Total de usuarios recuperados: " + usuarios.size());
        return usuarios;
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        String sql = "SELECT usuario_id, usuario_nom, usuario_email, usuario_pass, usuario_dir, usuario_tel, usuario_rol "
                + "FROM usuario WHERE usuario_id = ?";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("usuario_id"));
                    usuario.setUsername(rs.getString("usuario_nom"));
                    usuario.setEmail(rs.getString("usuario_email"));
                    usuario.setPassword(rs.getString("usuario_pass"));
                    usuario.setAddress(rs.getString("usuario_dir"));
                    usuario.setPhone(rs.getInt("usuario_tel"));
                    usuario.setRol(rs.getString("usuario_rol"));

                    System.out.println("Usuario encontrado: " + usuario.getUsername());
                    return usuario;
                } else {
                    System.out.println("No se encontró ningún usuario con ID: " + idUsuario);
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por ID: " + idUsuario);
            e.printStackTrace();
            return null;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET usuario_nom = ?, usuario_email = ?, usuario_pass = ?, usuario_dir = ?, usuario_tel = ?, usuario_rol = ? "
                + "WHERE usuario_id = ?";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
            ps.setString(4, usuario.getAddress());
            ps.setInt(5, usuario.getPhone());
            ps.setString(6, usuario.getRol());
            ps.setInt(7, usuario.getId());

            int rowsAffected = ps.executeUpdate();
            System.out.println("Usuario actualizado: " + usuario.getUsername() + ". Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario con ID: " + usuario.getId());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM usuario WHERE usuario_id = ?";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Usuario eliminado con ID: " + idUsuario + ". Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario con ID: " + idUsuario);
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> obtenerUsuariosPorRol(String rol) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT usuario_id, usuario_nom, usuario_email, usuario_pass, usuario_dir, usuario_tel, usuario_rol "
                + "FROM usuario WHERE usuario_rol = ?";

        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, rol);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("usuario_id"));
                    usuario.setUsername(rs.getString("usuario_nom"));
                    usuario.setEmail(rs.getString("usuario_email"));
                    usuario.setPassword(rs.getString("usuario_pass"));
                    usuario.setAddress(rs.getString("usuario_dir"));
                    usuario.setPhone(rs.getInt("usuario_tel"));
                    usuario.setRol(rs.getString("usuario_rol"));

                    usuarios.add(usuario);
                    System.out.println("Usuario recuperado con rol '" + rol + "': " + usuario.getUsername());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios por rol: " + rol);
            e.printStackTrace();
        }

        System.out.println("Total de usuarios con rol '" + rol + "': " + usuarios.size());
        return usuarios;
    }
}
