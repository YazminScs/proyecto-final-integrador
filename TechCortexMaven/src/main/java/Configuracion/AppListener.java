package Configuracion;

import DAO.CarritoDAO;
import DAO.UsuarioDAO;
import Modelo.Usuario;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            String username = (String) sce.getServletContext().getAttribute("username");

            if (username != null) {
                // Si el username está presente, puedes proceder con la lógica
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                CarritoDAO carritoDAO = new CarritoDAO();

                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));
                int carrito_id = carritoDAO.obtenerUltimoIdCarritoPorUsuario(usuario);

                carritoDAO.eliminarCarritoPorId(carrito_id);
            } else {
                // Si no hay un username, puedes manejarlo como sea necesario
                System.out.println("No se encontró el username en el contexto");
            }
        } catch (Exception e) {
            e.printStackTrace();  // O registra el error en un archivo de log
        }
    }

}
