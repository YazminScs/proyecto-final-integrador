package DAOTest;

import DAO.UsuarioDAO;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Configura la conexión H2 en memoria para el test
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");

        // Ejecuta el script para crear la tabla de usuario
        RunScript.execute(connection, new StringReader(
                "CREATE TABLE usuario ("
                + "usuario_nom VARCHAR(50), "
                + "usuario_email VARCHAR(50), "
                + "usuario_pass VARCHAR(255), "
                + "usuario_dir VARCHAR(100), "
                + "usuario_tel INT, "
                + "usuario_rol VARCHAR(20));"
        ));

        // Cierra la conexión inicial
        connection.close();

        // Instancia la clase UsuarioDAO
        usuarioDAO = new UsuarioDAO() {
            public Connection getConnection() throws SQLException {
                // Proporciona la conexión H2 para los métodos del DAO
                return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            }
        };
    }

    @Test
    public void testRegistrarUsuario() {
        // Prueba registrando un usuario
        boolean resultado = usuarioDAO.registrarUsuario("Juan", "juan@example.com", "password123", "Direccion 123", 123456789, "Administrador");

        // Verifica que el usuario se haya registrado correctamente
        assertTrue(resultado, "El usuario debería haberse registrado correctamente");
    }

    @Test
    public void testRegistrarUsuarioDuplicado() {
        // Intenta registrar el mismo usuario dos veces
        usuarioDAO.registrarUsuario("Juan", "juan@example.com", "password123", "Direccion 123", 123456789, "Administrador");
        boolean resultado = usuarioDAO.registrarUsuario("Juan", "juan@example.com", "password123", "Direccion 123", 123456789, "Administrador");

        // Verifica que el segundo registro falle si tienes restricción de unicidad
        assertFalse(resultado, "El registro duplicado debería fallar");
    }
}
