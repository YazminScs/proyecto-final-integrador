package Controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorProductos", urlPatterns = {"/ControladorProductos"})
public class ControladorProductos extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControladorProductos.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Método vacío en este caso
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            LOGGER.log(Level.WARNING, "Acción no especificada en la solicitud.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada.");
            return;
        }

        if ("paginaInicial".equals(accion)) {
            try {
                processRequest(request, response);
                cargarDatos(request);
                request.getRequestDispatcher("vista/menu.jsp").forward(request, response);
            } catch (IOException | ServletException e) {
                LOGGER.log(Level.SEVERE, "Error al cargar la página inicial", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar la página inicial.");
            }
        } else if ("catalogo".equals(accion)) {
            try {
                cargarDatos(request);
                request.getRequestDispatcher("Vista/catalogo.jsp").forward(request, response);
            } catch (IOException | ServletException e) {
                LOGGER.log(Level.SEVERE, "Error al cargar el catálogo de productos", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el catálogo.");
            }
        } else {
            LOGGER.log(Level.WARNING, "Acción no reconocida: {0}", accion);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void cargarDatos(HttpServletRequest request) throws ServletException {
        try {
            ProductoDAO productoDAO = new ProductoDAO();
            List<Producto> listaProductos = productoDAO.listarProductos();
            request.setAttribute("listaProductos", listaProductos);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar los productos", e);
            throw new ServletException("Error al cargar los productos", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para manejar las acciones de productos en la vista.";
    }
}
