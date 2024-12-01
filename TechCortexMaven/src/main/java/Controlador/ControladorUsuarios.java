package Controlador;

import DAO.CarritoDAO;
import DAO.DetalleCarritoDAO;
import DAO.OrdenDAO;
import DAO.UsuarioDAO;
import Modelo.Carrito;
import Modelo.CarritoInfo;
import Modelo.DetalleCarrito;
import Modelo.Orden;
import Modelo.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControladorUsuarios.class.getName());

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
            LOGGER.log(Level.WARNING, "Acción no especificada en la solicitud.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no especificada.");
            return;
        }

        if ("logout".equals(accion)) {
            HttpSession ses = request.getSession();
            String username = (String) ses.getAttribute("username");
            HttpSession session = request.getSession(false);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            CarritoDAO carritoDAO = new CarritoDAO();

            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));
            int carrito_id = carritoDAO.obtenerUltimoIdCarritoPorUsuario(usuario);

            if (session != null) {
                carritoDAO.eliminarCarritoPorId(carrito_id);
                session.invalidate();
                LOGGER.log(Level.INFO, "Sesión cerrada para el usuario: {0}", username);
            }

            response.sendRedirect("Vista/login.jsp");
        } else if ("perfil".equals(accion)) {
            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("username");
                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));

                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("Vista/perfil.jsp").forward(request, response);
                LOGGER.log(Level.INFO, "Acceso al perfil del usuario: {0}", username);
            } catch (IOException | ServletException e) {
                LOGGER.log(Level.SEVERE, "Error al cargar perfil del usuario.", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar perfil.");
            }
        } else if ("compras".equals(accion)) {
            try {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                DetalleCarritoDAO detalleCarritoDAO = new DetalleCarritoDAO();
                CarritoDAO carritoDAO = new CarritoDAO();
                OrdenDAO ordenDAO = new OrdenDAO();

                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("username");
                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));

                List<Carrito> carritos = carritoDAO.obtenerCarritosPorUsuario(usuario);

                List<CarritoInfo> carritosInfo = new ArrayList<>();
                for (Carrito carrito : carritos) {
                    List<DetalleCarrito> detalle = detalleCarritoDAO.listarDetalleCarritoPorId(carrito.getCarrito_id());
                    Orden orden = ordenDAO.obtenerOrdenPorCarrito(carrito);
                    CarritoInfo info = new CarritoInfo(carrito, detalle, orden);
                    carritosInfo.add(info);
                }

                request.setAttribute("carritoInfo", carritosInfo);
                request.getRequestDispatcher("Vista/mis-compras.jsp").forward(request, response);
                LOGGER.log(Level.INFO, "Acceso a la sección de compras del usuario: {0}", username);
            } catch (IOException | ServletException e) {
                LOGGER.log(Level.SEVERE, "Error al cargar las compras del usuario.", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar las compras.");
            }
        } else {
            LOGGER.log(Level.WARNING, "Acción no reconocida: {0}", accion);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registrarUsuario(request, response);
        } else if ("login".equals(action)) {
            iniciarSesion(request, response);
        } else if ("editar".equals(action)) {
            editarSesion(request, response);
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        int phone;

        try {
            phone = Integer.parseInt(request.getParameter("phone"));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Número de teléfono inválido.");
            response.sendRedirect("Vista/login.jsp");
            LOGGER.log(Level.WARNING, "Número de teléfono inválido proporcionado: {0}", request.getParameter("phone"));
            return;
        }

        String rol = "Cliente";
        UsuarioDAO userDAO = new UsuarioDAO();

        if (userDAO.existeNombreUsuario(username)) {
            request.getSession().setAttribute("error", "El nombre de usuario ya está en uso.");
            response.sendRedirect("Vista/login.jsp");
            LOGGER.log(Level.WARNING, "El nombre de usuario ya está en uso: {0}", username);
            return;
        }

        if (userDAO.existeEmailUsuario(email)) {
            request.getSession().setAttribute("error", "El email ya está en uso.");
            response.sendRedirect("Vista/login.jsp");
            LOGGER.log(Level.WARNING, "El email ya está en uso: {0}", email);
            return;
        }

        if (userDAO.registrarUsuario(username, email, password, address, phone, rol)) {
            request.getSession().setAttribute("message", "Registro exitoso!");
            response.sendRedirect("Vista/login.jsp");
            LOGGER.log(Level.INFO, "Usuario registrado exitosamente: {0}", username);
        } else {
            request.getSession().setAttribute("error", "Error al registrar usuario.");
            response.sendRedirect("Vista/login.jsp");
            LOGGER.log(Level.SEVERE, "Error al registrar usuario: {0}", username);
        }
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("signin-username");
        String password = request.getParameter("signin-password");
        UsuarioDAO userDAO = new UsuarioDAO();
        CarritoDAO carritoDAO = new CarritoDAO();

        if (userDAO.autenticar(username, password)) {
            if ("Administrador".equals(userDAO.obtenerRol(username))) {
                response.sendRedirect("Vista/admin.jsp");
                LOGGER.log(Level.INFO, "Acceso como Administrador: {0}", username);
            } else if ("Cliente".equals(userDAO.obtenerRol(username))) {
                carritoDAO.registrarCarrito(userDAO.obtenerUsuarioPorUsername(username));
                response.sendRedirect("index.jsp");
                LOGGER.log(Level.INFO, "Acceso como Cliente: {0}", username);
            }

            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("message", "Inicio de sesión exitoso. ¡Bienvenido, " + username + "!");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            response.sendRedirect("Vista/login.jsp");
            LOGGER.log(Level.WARNING, "Intento de inicio de sesión fallido para el usuario: {0}", username);
        }
    }

    private void editarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String usernameSesion = (String) session.getAttribute("username");

        if (usernameSesion == null) {
            session.setAttribute("error", "Debes iniciar sesión para editar tu perfil.");
            response.sendRedirect("Vista/login.jsp");
            LOGGER.log(Level.WARNING, "Usuario no autenticado para editar perfil.");
            return;
        }

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));

        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario usuarioActual = userDAO.obtenerUsuarioPorUsername(usernameSesion);
        usuarioActual.setUsername(username);
        usuarioActual.setEmail(email);
        usuarioActual.setAddress(address);
        usuarioActual.setPhone(phone);

        LOGGER.log(Level.INFO, "Detalles del usuario a editar: {0}", usuarioActual);

        boolean actualizado = userDAO.actualizarUsuario(usuarioActual);

        if (actualizado) {
            session.setAttribute("username", username);
            session.setAttribute("usuario", usuarioActual);
            session.setAttribute("message", "Perfil actualizado exitosamente.");
            response.sendRedirect("Vista/perfil.jsp");
            LOGGER.log(Level.INFO, "Perfil actualizado exitosamente para el usuario: {0}", username);
        } else {
            session.setAttribute("error", "No se pudo actualizar el perfil. Inténtalo nuevamente.");
            response.sendRedirect("Vista/perfil.jsp");
            LOGGER.log(Level.SEVERE, "Error al actualizar perfil para el usuario: {0}", username);
        }
    }
}
