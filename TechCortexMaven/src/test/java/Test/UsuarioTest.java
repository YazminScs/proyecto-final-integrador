package Test;

import ConexionTest.ConexionTest;
import DAOTest.UsuarioDAOTest;
import Modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private UsuarioDAOTest usuarioDAO;
    private ConexionTest conexion;

    @BeforeEach
    public void setup() throws Exception {
        conexion = new ConexionTest();
        usuarioDAO = new UsuarioDAOTest();

        try (Connection conn = conexion.getConexion()) {
            String createTableSql = "CREATE TABLE IF NOT EXISTS usuario(usuario_id INT AUTO_INCREMENT PRIMARY KEY, usuario_nom VARCHAR(255), usuario_email VARCHAR(255) UNIQUE, usuario_pass VARCHAR(255), usuario_dir VARCHAR(255), usuario_tel INT, usuario_rol VARCHAR(50));";
            conn.createStatement().execute(createTableSql);

            String truncateTableSql = "TRUNCATE TABLE usuario;";
            conn.createStatement().execute(truncateTableSql);
        }

        System.out.println("Base de datos preparada para las pruebas.");
    }

    @Test
    public void testRegistrarUsuarioValido() {
        Usuario usuario = new Usuario("Pedro Lopez", "pedro.lopez@email.com",
                "miClaveSegura123", "Avenida Siempre Viva", 987654321, "user");

        System.out.println("Intentando registrar usuario valido: " + usuario.getUsername());
        boolean resultado = usuarioDAO.registrarUsuario(usuario);

        assertTrue(resultado, "El usuario deberia haberse registrado exitosamente.");
    }

    @Test
    public void testObtenerUsuarios() {
        System.out.println("Intentando registrar y listar usuarios.");
        Usuario usuario = new Usuario("Carlos Díaz", "carlos.diaz@email.com",
                "claveSegura123", "Calle Principal", 987654321, "admin");
        usuarioDAO.registrarUsuario(usuario);

        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
        assertEquals(1, usuarios.size(), "Deberia haber exactamente 1 usuario registrado.");
    }

    @Test
    public void testBuscarUsuarioPorId() {
        System.out.println("Probando busqueda de usuario por ID...");

        // Registrar un usuario para la prueba
        Usuario usuario = new Usuario("María Gómez", "maria.gomez@email.com",
                "claveFuerte123", "Calle Segunda", 987654321, "user");
        boolean resultado = usuarioDAO.registrarUsuario(usuario);

        assertTrue(resultado, "El usuario deberia haberse registrado exitosamente.");

        // Obtener el ID del usuario registrado
        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();

        // Verificar que la lista de usuarios no esté vacía
        assertFalse(usuarios.isEmpty(), "La lista de usuarios no debería estar vacía.");

        int idUsuario = usuarios.get(0).getId();  // Obtener el ID del primer usuario registrado

        // Buscar el usuario por ID
        Usuario usuarioEncontrado = usuarioDAO.buscarUsuarioPorId(idUsuario);

        // Verificar que el usuario ha sido encontrado
        assertNotNull(usuarioEncontrado, "El usuario deberia haberse encontrado.");
        assertEquals("María Gómez", usuarioEncontrado.getUsername(), "El nombre del usuario deberia coincidir.");
        assertEquals("maria.gomez@email.com", usuarioEncontrado.getEmail(), "El email del usuario deberia coincidir.");
        assertEquals("Calle Segunda", usuarioEncontrado.getAddress(), "La direccion del usuario deberia coincidir.");
        assertEquals(987654321, usuarioEncontrado.getPhone(), "El telefono del usuario deberia coincidir.");
        assertEquals("user", usuarioEncontrado.getRol(), "El rol del usuario deberia coincidir.");

        System.out.println("Búsqueda completada: " + usuarioEncontrado.getUsername());
    }

    @Test
    public void testActualizarUsuario() {
        Usuario usuario = new Usuario("Ana Lopez", "ana.lopez@email.com",
                "claveSegura123", "Calle Nueva", 987654321, "user");
        usuarioDAO.registrarUsuario(usuario);

        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
        Usuario usuarioActualizado = usuarios.get(0);
        usuarioActualizado.setUsername("Ana Actualizada");
        usuarioActualizado.setAddress("Calle Actualizada");

        boolean resultado = usuarioDAO.actualizarUsuario(usuarioActualizado);
        assertTrue(resultado, "El usuario deberia haberse actualizado exitosamente.");
    }

    @Test
    public void testEliminarUsuario() {
        Usuario usuario = new Usuario("Luis Fernández", "luis.fernandez@email.com",
                "claveSegura123", "Calle Nueva", 987654321, "admin");
        usuarioDAO.registrarUsuario(usuario);

        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
        int idUsuario = usuarios.get(0).getId();

        boolean resultado = usuarioDAO.eliminarUsuario(idUsuario);
        assertTrue(resultado, "El usuario deberia haberse eliminado exitosamente.");
    }

    @Test
    public void testObtenerUsuariosPorRol() {
        System.out.println("Probando obtener usuarios por rol...");

        // Registrar varios usuarios con roles diferentes
        usuarioDAO.registrarUsuario(new Usuario("Juan Admin", "juan.admin@email.com", "claveAdmin123", "Calle 1", 123456789, "admin"));
        usuarioDAO.registrarUsuario(new Usuario("María User", "maria.user@email.com", "claveUser123", "Calle 2", 987654321, "user"));
        usuarioDAO.registrarUsuario(new Usuario("Pedro Admin", "pedro.admin@email.com", "claveAdmin456", "Calle 3", 456123789, "admin"));

        // Obtener usuarios con el rol "admin"
        List<Usuario> admins = usuarioDAO.obtenerUsuariosPorRol("admin");

        // Validar que se encontraron los usuarios correctos
        assertNotNull(admins, "La lista de administradores no deberia ser nula.");
        assertEquals(2, admins.size(), "Deberia haber exactamente 2 administradores.");
        assertTrue(admins.stream().allMatch(usuario -> "admin".equals(usuario.getRol())), "Todos los usuarios deberian tener el rol 'admin'.");

        // Imprimir los usuarios con el rol "admin"
        System.out.println("Usuarios con rol 'admin':");
        for (Usuario admin : admins) {
            System.out.println("ID: " + admin.getId() + ", Nombre: " + admin.getUsername() + ", Email: " + admin.getEmail());
        }
    }
}
