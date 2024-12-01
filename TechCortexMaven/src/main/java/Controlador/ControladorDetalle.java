package Controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControladorDetalle")
public class ControladorDetalle extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControladorDetalle.class.getName());
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProductoStr = request.getParameter("idProducto");
        
        LOGGER.log(Level.INFO, "Solicitud recibida para obtener detalles de producto. ID proporcionado: {0}", idProductoStr);

        if (idProductoStr == null || idProductoStr.isEmpty()) {
            LOGGER.warning("ID de producto no proporcionado.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Debe proporcionar un ID de producto válido.");
            return;
        }

        try {
            int idProducto = Integer.parseInt(idProductoStr);
            LOGGER.log(Level.INFO, "Intentando obtener el producto con ID: {0}", idProducto);

            Producto producto = productoDAO.obtenerPorId(idProducto);

            if (producto != null) {
                LOGGER.log(Level.INFO, "Producto encontrado: {0}", producto.getNombre());
                request.setAttribute("producto", producto);
                request.getRequestDispatcher("/Vista/detalleProducto.jsp").forward(request, response);
            } else {
                LOGGER.log(Level.WARNING, "Producto no encontrado con ID: {0}", idProducto);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Formato inválido para el ID de producto: " + idProductoStr, e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de ID de producto inválido.");
        } catch (IOException | ServletException e) {
            LOGGER.log(Level.SEVERE, "Error al procesar la solicitud para obtener detalles del producto.", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor.");
        }
    }
}
