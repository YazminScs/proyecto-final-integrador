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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorAdmin", urlPatterns = {"/ControladorAdmin"})
public class ControladorAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            request.setAttribute("mensaje", "Error: La acción no fue especificada.");
            request.getRequestDispatcher("/Vista/error.jsp").forward(request, response);
            return;
        }
        if (accion.equals("paginaProductos")) {
            cargarProductos(request);
            request.getRequestDispatcher("/vista/admin-productos.jsp").forward(request, response);
        } else if (accion.equals("paginaCategorias")) {
            cargarCategorias(request);
            request.getRequestDispatcher("/vista/admin-categorias.jsp").forward(request, response);
        }else if (accion.equals("paginaMarcas")) {
            cargarMarcas(request);
            request.getRequestDispatcher("/vista/admin-marcas.jsp").forward(request, response);
        }else if (accion.equals("paginaUsuarios")) {
            cargarUsuarios(request);
            request.getRequestDispatcher("/vista/admin-usuarios.jsp").forward(request, response);
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
        List<Producto> listaProductos = new ProductoDAO().listarProductos();
        request.setAttribute("listaProductos", listaProductos);
    }

    private void cargarCategorias(HttpServletRequest request) {
        List <Categoria> listaCategorias = new CategoriaDAO().listarCategoria();
        request.setAttribute("listaCategorias", listaCategorias);
    }

    private void cargarMarcas(HttpServletRequest request) {
        List<Marca> listaMarcas = new MarcaDAO().listarMarcas();
        request.setAttribute("listaMarcas", listaMarcas);
    }
    
    private void cargarUsuarios(HttpServletRequest request) {
        List<Usuario> listaUsuarios = new UsuarioDAO().obtenerUsuarios();
        request.setAttribute("listaUsuarios", listaUsuarios);
    }
}