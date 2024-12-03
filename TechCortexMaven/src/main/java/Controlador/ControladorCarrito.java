package Controlador;

import DAO.CarritoDAO;
import DAO.DetalleCarritoDAO;
import DAO.ProductoDAO;
import DAO.UsuarioDAO;
import Modelo.DetalleCarrito;
import Modelo.Producto;
import Modelo.Usuario;
import java.util.List;
import java.text.DecimalFormat;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorCarrito", urlPatterns = {"/ControladorCarrito"})
public class ControladorCarrito extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControladorCarrito.class.getName());

    private CarritoDAO carritoDAO = new CarritoDAO();
    private DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("carrito".equals(accion)) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            if (username == null) {
                response.sendRedirect("Vista/login.jsp");
                return;
            }

            try {
                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));
                int carrito_id = carritoDAO.obtenerUltimoIdCarritoPorUsuario(usuario);
                List<DetalleCarrito> miDetalle = detalleCarritoDAO.listarDetalleCarritoPorId(carrito_id);

                double total = 0.0;
                for (DetalleCarrito detalle : miDetalle) {
                    total += detalle.getDetalle_price() * detalle.getDetalle_cant();
                }

                DecimalFormat df = new DecimalFormat("#.00");
                String totalFormateado = df.format(total);

                request.setAttribute("total", totalFormateado);
                request.setAttribute("miDetalle", miDetalle);
                request.getRequestDispatcher("Vista/carrito.jsp").forward(request, response);
                return;
            } catch (IOException | ServletException e) {
                LOGGER.log(Level.SEVERE, "Error al cargar el carrito: ", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el carrito");
            }
        }
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("Vista/login.jsp");
            return;
        }

        CarritoDAO carritoDAO = new CarritoDAO();
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = productoDAO.obtenerPorId(idProducto);
        Date fechaActual = new Date();
        java.sql.Date sqlDate = new java.sql.Date(fechaActual.getTime());
        int carrito_id = carritoDAO.obtenerUltimoIdCarritoPorUsuario(usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username)));
        boolean detalle_creado = detalleCarritoDAO.registrarDetalleCarrito(1, producto.getPrecio(), carritoDAO.obtenerCarritoPorId(carrito_id), producto);
        if (detalle_creado) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Producto agregado correctamente.");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("No se pudo agregar el producto al carrito.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("eliminarProducto".equals(accion)) {
            eliminarProducto(request, response);
        } else if ("aumentarCantidad".equals(accion)) {
            modificarCantidad(request, response, true);
        } else if ("disminuirCantidad".equals(accion)) {
            modificarCantidad(request, response, false);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Acción no válida.");
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int carritoId = Integer.parseInt(request.getParameter("carritoId"));

            boolean eliminado = detalleCarritoDAO.eliminarProducto(carritoDAO.obtenerCarritoPorId(carritoId), productoDAO.obtenerPorId(idProducto));

            if (eliminado) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Producto eliminado correctamente.");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("No se pudo eliminar el producto del carrito.");
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Parámetros inválidos para eliminar producto: ", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: Parámetros inválidos.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar el producto: ", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    private void modificarCantidad(HttpServletRequest request, HttpServletResponse response, boolean aumentar) throws IOException {
        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int carritoId = Integer.parseInt(request.getParameter("carritoId"));
            int cantidadActual = Integer.parseInt(request.getParameter("cantidad"));

            int nuevaCantidad = aumentar ? cantidadActual + 1 : cantidadActual - 1;

            if (nuevaCantidad <= 0) {
                boolean eliminado = detalleCarritoDAO.eliminarProducto(carritoDAO.obtenerCarritoPorId(carritoId), productoDAO.obtenerPorId(idProducto));

                if (eliminado) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Producto eliminado correctamente.");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("No se pudo eliminar el producto del carrito.");
                }
            } else {
                boolean actualizado = detalleCarritoDAO.actualizarCantidad(
                        nuevaCantidad, productoDAO.obtenerPorId(idProducto), carritoDAO.obtenerCarritoPorId(carritoId)
                );

                if (actualizado) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Cantidad " + (aumentar ? "aumentada" : "disminuida") + " correctamente.");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("No se pudo actualizar la cantidad del producto.");
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Parámetros inválidos para modificar cantidad: ", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: Parámetros inválidos.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la cantidad del producto: ", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
