package Controlador;

import DAO.CarritoDAO;
import DAO.UsuarioDAO;
import Modelo.Carrito;
import Modelo.Usuario;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String accion = request.getParameter("accion");

        if ("logout".equals(accion)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("Vista/login.jsp");
        } else if ("perfil".equals(accion)) {
            UsuarioDAO usuarioDAO= new UsuarioDAO();
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            Usuario usuario=usuarioDAO.obtenerUsuarioPorId(usuarioDAO.obtenerIdPorNombreUsuario(username));
            
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("Vista/perfil.jsp").forward(request, response);
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
            return;
        }

        String rol = "Cliente";
        UsuarioDAO userDAO = new UsuarioDAO();

        if (userDAO.existeNombreUsuario(username)) {
            request.getSession().setAttribute("error", "El nombre de usuario ya está en uso.");
            response.sendRedirect("Vista/login.jsp");
            return;
        }

        if (userDAO.existeEmailUsuario(email)) {
            request.getSession().setAttribute("error", "El email ya está en uso.");
            response.sendRedirect("Vista/login.jsp");
            return;
        }

        if (userDAO.registrarUsuario(username, email, password, address, phone, rol)) {
            request.getSession().setAttribute("message", "Registro exitoso!");
            response.sendRedirect("Vista/login.jsp");
        } else {
            request.getSession().setAttribute("error", "Error al registrar usuario.");
            response.sendRedirect("Vista/login.jsp");
        }
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("signin-username");
        String password = request.getParameter("signin-password");
        UsuarioDAO userDAO = new UsuarioDAO();

        if (userDAO.autenticar(username, password)) {
            if ((userDAO.obtenerRol(username)).equals("Administrador")) {
                response.sendRedirect("Vista/admin.jsp");
            } else if ((userDAO.obtenerRol(username)).equals("Cliente")) {
                response.sendRedirect("index.jsp");
            }
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("message", "Inicio de sesión exitoso. ¡Bienvenido, " + username + "!");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            response.sendRedirect("Vista/login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
