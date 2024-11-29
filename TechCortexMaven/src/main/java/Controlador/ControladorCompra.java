package Controlador;

import DAO.CarritoDAO;
import DAO.DetalleCarritoDAO;
import DAO.MetodoPagoDAO;
import DAO.OrdenDAO;
import DAO.ProductoDAO;
import DAO.UsuarioDAO;
import Modelo.Carrito;
import Modelo.DetalleCarrito;
import Modelo.Orden;
import Modelo.Usuario;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorCompra", urlPatterns = {"/ControladorCompra"})
public class ControladorCompra extends HttpServlet {

    private CarritoDAO carritoDAO = new CarritoDAO();
    private DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private MetodoPagoDAO metodoPagoDAO = new MetodoPagoDAO();
    private OrdenDAO ordenDAO = new OrdenDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("continuarCompra".equals(accion)) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            if (username == null) {
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
            request.getRequestDispatcher("Vista/proceso-compra.jsp").forward(request, response);
        } else if ("finalizarCompra".equals(accion)) {
            int pago_id = Integer.parseInt(request.getParameter("pago_id"));

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            if (username == null) {
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

            boolean registrado = ordenDAO.registrarOrden(carritoDAO.obtenerCarritoPorId(carrito_id), metodoPagoDAO.obtenerPagoPorId(pago_id), "Pendiente");
            request.setAttribute("total", totalFormateado);
            request.setAttribute("miDetalle", miDetalle);
            request.getRequestDispatcher("Vista/final-compra.jsp").forward(request, response);
        }
    }
}
