package Controlador;

import DAO.MarcaDAO;
import DAO.ProductoDAO;
import Modelo.Marca;
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

@WebServlet(name = "ControladorCarrusel", urlPatterns = {"/ControladorCarrusel"})
public class ControladorCarrusel extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControladorCarrusel.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LOGGER.info("Procesando solicitud.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String accion = request.getParameter("accion");
        LOGGER.log(Level.INFO, "Acci\u00f3n recibida: {0}", accion);

        if ("carrusel".equals(accion)) {
            try {
                cargarProductos(request);
                cargarTeclados(request);
                cargarMonitores(request);
                cargarMarcas(request);
                LOGGER.info("Datos cargados correctamente. Redirigiendo a menú.");
                request.getRequestDispatcher("/Vista/menu.jsp").forward(request, response);
            } catch (IOException | ServletException e) {
                LOGGER.log(Level.SEVERE, "Error al cargar datos para el carrusel.", e);
                request.setAttribute("mensaje", "Error al cargar los datos. Intente nuevamente.");
                request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            }
        } else {
            LOGGER.warning("Acción no especificada o inválida.");
            request.setAttribute("mensaje", "Acción no especificada.");
            request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void cargarProductos(HttpServletRequest request) {
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            List<Producto> listaProductos = productoDAO.listarProductos();
            request.setAttribute("listaProductos", listaProductos);
            LOGGER.info("Productos cargados exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar productos.", e);
        }
    }

    private void cargarMarcas(HttpServletRequest request) {
        MarcaDAO marcasDAO = new MarcaDAO();
        try {
            List<Marca> listaMarcas = marcasDAO.listarMarcas();
            request.setAttribute("listaMarcas", listaMarcas);
            LOGGER.info("Marcas cargadas exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar marcas.", e);
        }
    }

    private void cargarTeclados(HttpServletRequest request) {
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            List<Producto> listaTeclados = productoDAO.listarTeclados();
            request.setAttribute("listaTeclados", listaTeclados);
            LOGGER.info("Teclados cargados exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar teclados.", e);
        }
    }

    private void cargarMonitores(HttpServletRequest request) {
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            List<Producto> listaMonitores = productoDAO.listarMonitores();
            request.setAttribute("listaMonitores", listaMonitores);
            LOGGER.info("Monitores cargados exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar monitores.", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para manejar el carrusel de productos.";
    }
}
