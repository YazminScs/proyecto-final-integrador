package Test;

import ConexionTest.ConexionTest;
import DAOTest.UsuarioDAOTest;
import Modelo.Usuario;
import java.sql.*;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioTest {

    private ConexionTest conexion;
    private UsuarioDAOTest usuarioDAO;

    @BeforeEach
    public void setup() throws SQLException {
        conexion = new ConexionTest();
        usuarioDAO = new UsuarioDAOTest();
        
        try (Connection conn = conexion.getConexion()) {
            String createTableSql = "CREATE TABLE IF NOT EXISTS usuario ("
                    + "usuario_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "usuario_nom VARCHAR(255), "
                    + "usuario_email VARCHAR(255) UNIQUE, "
                    + "usuario_pass VARCHAR(255), "
                    + "usuario_dir VARCHAR(255), "
                    + "usuario_tel INT, "
                    + "usuario_rol VARCHAR(50));";
            conn.createStatement().execute(createTableSql);
        }
    }

    @Test
    public void testRegistrarUsuario() {

        boolean resultado = usuarioDAO.registrarUsuario("Juan Pérez", "juan.perez@email.com",
                "contraseña123", "Calle Falsa 123", 123456789, "Administrador");

        assertTrue(resultado, "El usuario debería haber sido registrado exitosamente.");
    }

    @Test
    public void testListarUsuarios() {

        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();

        assertTrue(usuarios.size() > 0, "La lista de usuarios no debería estar vacía.");

        System.out.println("Lista de Usuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getUsername()
                    + ", Email: " + usuario.getEmail() + ", Rol: " + usuario.getRol()
                    + ", Dirección: " + usuario.getAddress() + ", Teléfono: " + usuario.getPhone());
        }

    }

}
