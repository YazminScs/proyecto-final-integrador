package Controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorProductos", urlPatterns = {"/ControladorProductos"})
public class ControladorProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var accion = request.getParameter("accion");
        if (accion.equals("paginaInicial")) {
            processRequest(request, response);
            cargarDatos(request);
            request.getRequestDispatcher("vista/menu.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    private void cargarDatos(HttpServletRequest request) {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> listaProductos = productoDAO.listarProductos();
        request.setAttribute("listaProductos", listaProductos);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}