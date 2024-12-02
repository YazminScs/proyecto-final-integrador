package Controlador;

import DAO.CategoriaDAO;
import DAO.MarcaDAO;
import DAO.ProductoDAO;
import DAO.UsuarioDAO;
import Modelo.Categoria;
import Modelo.Marca;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorAdmin", urlPatterns = {"/ControladorAdmin"})
public class ControladorAdmin extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControladorAdmin.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String accion = request.getParameter("accion");
        LOGGER.info("Acción recibida en el ControladorAdmin: " + accion);

        if (accion == null || accion.isEmpty()) {
            LOGGER.warning("Acción no especificada.");
            request.setAttribute("mensaje", "Error: La acción no fue especificada.");
            request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            return;
        }

        if (accion.equals("paginaProductos")) {
            try {
                LOGGER.info("Cargando página de productos.");
                cargarProductos(request);
                request.getRequestDispatcher("/vista/admin-productos.jsp").forward(request, response);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al cargar productos.", e);
                request.setAttribute("mensaje", "Se produjo un error al cargar los productos.");
                request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            }
        } else if (accion.equals("paginaCategorias")) {
            try {
                LOGGER.info("Cargando página de categorías.");
                cargarCategorias(request);
                request.getRequestDispatcher("/vista/admin-categorias.jsp").forward(request, response);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al cargar categorías.", e);
                request.setAttribute("mensaje", "Se produjo un error al cargar las categorías.");
                request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            }
        } else if (accion.equals("paginaMarcas")) {
            try {
                LOGGER.info("Cargando página de marcas.");
                cargarMarcas(request);
                request.getRequestDispatcher("/vista/admin-marcas.jsp").forward(request, response);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al cargar marcas.", e);
                request.setAttribute("mensaje", "Se produjo un error al cargar las marcas.");
                request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            }
        } else if (accion.equals("paginaUsuarios")) {
            try {
                LOGGER.info("Cargando página de usuarios.");
                cargarUsuarios(request);
                request.getRequestDispatcher("/vista/admin-usuarios.jsp").forward(request, response);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al cargar usuarios.", e);
                request.setAttribute("mensaje", "Se produjo un error al cargar los usuarios.");
                request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            }
        } else {
            LOGGER.warning("Acción no reconocida: " + accion);
            request.setAttribute("mensaje", "Error: Acción no válida.");
            request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void cargarProductos(HttpServletRequest request) {
        try {
            List<Producto> listaProductos = new ProductoDAO().listarProductos();
            request.setAttribute("listaProductos", listaProductos);
            LOGGER.info("Productos cargados exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar productos en el método cargarProductos.", e);
            throw new RuntimeException(e);
        }
    }

    private void cargarCategorias(HttpServletRequest request) {
        try {
            List<Categoria> listaCategorias = new CategoriaDAO().listarCategoria();
            request.setAttribute("listaCategorias", listaCategorias);
            LOGGER.info("Categorías cargadas exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar categorías en el método cargarCategorias.", e);
            throw new RuntimeException(e);
        }
    }

    private void cargarMarcas(HttpServletRequest request) {
        try {
            List<Marca> listaMarcas = new MarcaDAO().listarMarcas();
            request.setAttribute("listaMarcas", listaMarcas);
            LOGGER.info("Marcas cargadas exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar marcas en el método cargarMarcas.", e);
            throw new RuntimeException(e);
        }
    }

    private void cargarUsuarios(HttpServletRequest request) {
        try {
            List<Usuario> listaUsuarios = new UsuarioDAO().obtenerUsuarios();
            request.setAttribute("listaUsuarios", listaUsuarios);
            LOGGER.info("Usuarios cargados exitosamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al cargar usuarios en el método cargarUsuarios.", e);
            throw new RuntimeException(e);
        }
    }
}
