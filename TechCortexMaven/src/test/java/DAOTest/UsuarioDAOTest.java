package DAOTest;

import ConexionTest.ConexionTest;
import Modelo.Usuario;
import java.sql.*;
import java.util.*;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAOTest implements IUsuarioDAO {

    @Override
    public boolean registrarUsuario(String usuario_nom, String usuario_email, String usuario_pass,
            String usuario_dir, int usuario_tel, String usuario_rol) {
        String sql = "INSERT INTO usuario (usuario_nom, usuario_email, usuario_pass, usuario_dir, usuario_tel, usuario_rol) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(usuario_pass, BCrypt.gensalt());
        try (Connection cnn = new ConexionTest().getConexion(); PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setString(1, usuario_nom);
            ps.setString(2, usuario_email);
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario_dir);
            ps.setInt(5, usuario_tel);
            ps.setString(6, usuario_rol);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Filas afectadas: " + rowsAffected);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}
