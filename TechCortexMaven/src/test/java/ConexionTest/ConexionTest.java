package ConexionTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionTest {

    // Método para obtener una conexión a la base de datos en memoria H2
    public Connection getConexion() throws SQLException {
        // Conexión a la base de datos en memoria H2
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";  // Base de datos en memoria
        return DriverManager.getConnection(url, "sa", "");
    }

}
