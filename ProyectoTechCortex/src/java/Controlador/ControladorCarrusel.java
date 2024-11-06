package Controlador;

import DAO.MarcaDAO;
import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorCarrusel", urlPatterns = {"/ControladorCarrusel"})
public class ControladorCarrusel extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        var accion = request.getParameter("accion");

        if ("carrusel".equals(accion)) {
            cargarProductos(request);
            cargarTeclados(request);
            cargarMonitores(request);
            cargarMarcas(request);
            request.getRequestDispatcher("/Vista/menu.jsp").forward(request, response);
        } else {
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
        List<Producto> listaProductos = productoDAO.listarProductos();
        request.setAttribute("listaProductos", listaProductos);
    }

    private void cargarMarcas(HttpServletRequest request) {
        MarcaDAO marcasDAO = new MarcaDAO();
        var listaMarcas = marcasDAO.listarMarcas();
        request.setAttribute("listaMarcas", listaMarcas);
    }
    
    private void cargarTeclados(HttpServletRequest request) {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> listaTeclados = productoDAO.listarTeclados();
        request.setAttribute("listaTeclados", listaTeclados);
    }
    
    private void cargarMonitores(HttpServletRequest request) {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> listaMonitores = productoDAO.listarMonitores();
        request.setAttribute("listaMonitores", listaMonitores);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}