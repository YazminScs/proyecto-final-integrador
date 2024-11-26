package Controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorDetalle")
public class ControladorDetalle extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProductoStr = request.getParameter("idProducto");
        try {
            int idProducto = Integer.parseInt(idProductoStr);

            Producto producto = productoDAO.obtenerPorId(idProducto);

            if (producto != null) {
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("/Vista/detalleProducto.jsp").forward(request, response);
            } else {
                System.out.println("Producto no encontrado con ID: " + idProducto);  // Mensaje de depuración
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto inválido");
        }
    }

}
