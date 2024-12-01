package Controlador;

import DAO.CarritoDAO;
import DAO.DetalleCarritoDAO;
import DAO.MetodoPagoDAO;
import DAO.OrdenDAO;
import DAO.ProductoDAO;
import DAO.UsuarioDAO;
import Modelo.DetalleCarrito;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorCompra", urlPatterns = {"/ControladorCompra"})
public class ControladorCompra extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControladorCompra.class.getName());

    private CarritoDAO carritoDAO = new CarritoDAO();
    private DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private MetodoPagoDAO metodoPagoDAO = new MetodoPagoDAO();
    private OrdenDAO ordenDAO = new OrdenDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        LOGGER.log(Level.INFO, "Acci\u00f3n recibida: {0}", accion);

        try {
            if ("continuarCompra".equals(accion)) {
                LOGGER.info("Iniciando proceso de continuar compra.");
                continuarCompra(request, response);
            } else if ("finalizarCompra".equals(accion)) {
                LOGGER.info("Iniciando proceso de finalizar compra.");
                finalizarCompra(request, response);
            } else {
                LOGGER.log(Level.WARNING, "Acci\u00f3n no reconocida: {0}", accion);
                response.sendRedirect("Vista/error.jsp");
            }
        } catch (IOException | ServletException e) {
            LOGGER.log(Level.SEVERE, "Error en el controlador de compra.", e);
            request.setAttribute("mensaje", "Se produjo un error al procesar la solicitud. Intente nuevamente.");
            request.getRequestDispatcher("Vista/error.jsp").forward(request, response);
        }
    }

    private void continuarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            LOGGER.warning("Usuario no autenticado. Redirigiendo a la página de inicio de sesión.");
            response.sendRedirect("Vista/login.jsp");
            return;
        }

        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));
        int carrito_id = carritoDAO.obtenerUltimoIdCarritoPorUsuario(usuario);

        List<DetalleCarrito> miDetalle = detalleCarritoDAO.listarDetalleCarritoPorId(carrito_id);
        double total = 0.0;
        for (DetalleCarrito detalle : miDetalle) {
            total += detalle.getDetalle_price() * detalle.getDetalle_cant();
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String totalFormateado = df.format(total);

        request.setAttribute("usuario", usuario);
        request.setAttribute("total", totalFormateado);
        request.setAttribute("miDetalle", miDetalle);

        LOGGER.log(Level.INFO, "Compra continuada para el usuario: {0} con carrito ID: {1}", new Object[]{username, carrito_id});
        request.getRequestDispatcher("Vista/proceso-compra.jsp").forward(request, response);
    }

    private void finalizarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pago_id = Integer.parseInt(request.getParameter("pago_id"));
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            LOGGER.warning("Usuario no autenticado. Redirigiendo a la página de inicio de sesión.");
            response.sendRedirect("Vista/login.jsp");
            return;
        }

        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));
        int carrito_id = carritoDAO.obtenerUltimoIdCarritoPorUsuario(usuario);

        List<DetalleCarrito> miDetalle = detalleCarritoDAO.listarDetalleCarritoPorId(carrito_id);
        double total = 0.0;
        for (DetalleCarrito detalle : miDetalle) {
            total += detalle.getDetalle_price() * detalle.getDetalle_cant();
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String totalFormateado = df.format(total);

        boolean actualizado = carritoDAO.actualizarCarrito(total, carrito_id);
        LOGGER.log(Level.INFO, "Carrito actualizado: {0} para carrito ID: {1}", new Object[]{actualizado, carrito_id});

        boolean registrado = ordenDAO.registrarOrden(carritoDAO.obtenerCarritoPorId(carrito_id), metodoPagoDAO.obtenerPagoPorId(pago_id), "Pendiente");
        LOGGER.log(Level.INFO, "Orden registrada: {0} para carrito ID: {1}", new Object[]{registrado, carrito_id});

        // Actualizar stock de productos
        for (DetalleCarrito detalle : miDetalle) {
            Producto producto = detalle.getProducto();
            int cantidad = detalle.getDetalle_cant();
            int nuevoStock = producto.getStock() - cantidad;

            boolean stockActualizado = productoDAO.actualizarStock(nuevoStock, producto.getIdProducto());
            LOGGER.log(Level.INFO, "Stock actualizado para producto ID: {0} Nuevo stock: {1} Resultado: {2}", new Object[]{producto.getIdProducto(), nuevoStock, stockActualizado});
        }

        // Registrar un nuevo carrito para el usuario
        boolean carritoRegistrado = carritoDAO.registrarCarrito(usuario);
        LOGGER.log(Level.INFO, "Nuevo carrito registrado: {0} para usuario: {1}", new Object[]{carritoRegistrado, usuario.getNombre()});

        request.setAttribute("carrito_id", carrito_id);
        request.setAttribute("total", totalFormateado);
        request.setAttribute("miDetalle", miDetalle);

        LOGGER.log(Level.INFO, "Compra finalizada para usuario: {0}", username);
        request.getRequestDispatcher("Vista/final-compra.jsp").forward(request, response);
    }
}
