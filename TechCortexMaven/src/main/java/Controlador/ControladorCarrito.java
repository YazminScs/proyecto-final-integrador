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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorCarrito", urlPatterns = {"/ControladorCarrito"})
public class ControladorCarrito extends HttpServlet {

    private CarritoDAO carritoDAO = new CarritoDAO();
    private DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        // Verificar si la acción es "carrito"
        if ("carrito".equals(accion)) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            if (username == null) {
                response.sendRedirect("Vista/login.jsp");
                return;
            }

            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));
            int carrito_id = carritoDAO.obtenerUltimoIdCarritoPorUsuario(usuario);

            List<DetalleCarrito> miDetalle = detalleCarritoDAO.listarDetalleCarritoPorId(carrito_id);

            // Calcular el total
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
        }

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("Vista/login.jsp");
            return;
        }

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = productoDAO.obtenerPorId(idProducto);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("eliminarProducto".equals(accion)) {
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
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Parámetros inválidos.");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error: " + e.getMessage());
            }
        } else if ("aumentarCantidad".equals(accion)) {
            try {
                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                int carritoId = Integer.parseInt(request.getParameter("carritoId"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                cantidad = cantidad + 1;

                boolean eliminado = detalleCarritoDAO.actualizarCantidad(cantidad, productoDAO.obtenerPorId(idProducto), carritoDAO.obtenerCarritoPorId(carritoId));

                if (eliminado) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Cantidad aumentada correctamente.");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("No se pudo aumentar la cantidad del producto del carrito.");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Parámetros inválidos.");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error: " + e.getMessage());
            }
        } else if ("disminuirCantidad".equals(accion)) {
            try {
                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                int carritoId = Integer.parseInt(request.getParameter("carritoId"));
                int cantidadActual = Integer.parseInt(request.getParameter("cantidad"));

                int nuevaCantidad = cantidadActual - 1;

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
                        response.getWriter().write("Cantidad disminuida correctamente.");
                    } else {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write("No se pudo disminuir la cantidad del producto del carrito.");
                    }
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Error: Parámetros inválidos.");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error: " + e.getMessage());
            }
        }
    }
}
