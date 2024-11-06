package DAO;

import Conexion.Conexion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT usuario_id, usuario_nom, usuario_email, usuario_pass, usuario_rol, usuario_dir, usuario_tel FROM usuario;";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public boolean autenticar(String usuario_nom, String usuario_pass) {
        String sql = "SELECT usuario_pass FROM usuario WHERE usuario_nom=?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario_nom);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("usuario_pass");
                    String hashPass = BCrypt.hashpw(storedPassword, BCrypt.gensalt());
                    return verificarPass(usuario_pass, hashPass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean verificarPass(String usuario_pass, String pass_enc) {
        return BCrypt.checkpw(usuario_pass, pass_enc);
    }

    @Override
    public boolean registrarUsuario(String usuario_nom, String usuario_email, String usuario_pass, String usuario_dir, int usuario_tel, String usuario_rol) {
        String sql = "INSERT INTO usuario (usuario_nom, usuario_email, usuario_pass, usuario_dir, usuario_tel, usuario_rol) VALUES (?, ?, ?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(usuario_pass, BCrypt.gensalt());
        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario_nom);
            ps.setString(2, usuario_email);
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario_dir);
            ps.setInt(5, usuario_tel);
            ps.setString(6, usuario_rol);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existeNombreUsuario(String usuario_nom) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM usuario WHERE usuario_nom = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario_nom);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public boolean existeEmailUsuario(String usuario_email) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM usuario WHERE usuario_email = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario_email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public String obtenerRol(String usuario_nom) {
        String rol = null;
        String sql = "SELECT usuario_rol FROM usuario WHERE usuario_nom = ?";

        try (Connection cnn = new Conexion().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario_nom);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                rol = resultSet.getString("usuario_rol");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rol;
    }
}