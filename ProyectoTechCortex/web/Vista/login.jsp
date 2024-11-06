<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Iniciar Sesión / Registrarse</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-login.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <body>
        <div class="container" style="height: 90vh !important;">
            <div class="signin-signup">
                <!-- inicia sesión -->
                <form action="${pageContext.request.contextPath}/ControladorUsuarios" class="sign-in-form" method="post" id="sign-in-form">
                    <input type="hidden" name="action" value="login">
                    <h2 class="title">Inicia Sesión</h2>
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" name="signin-username" id="signin-username" placeholder="Nombre de usuario" required>
                    </div>
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="signin-password" id="signin-password" placeholder="Contraseña" required>
                    </div>
                    <input type="submit" name="btnIngresar" value="Inicia sesión" class="btn">
                    <p class="social-text">O inicia sesión con otra plataforma</p>
                    <div class="social-media">
                        <a href="#" class="social-icon">
                            <i class="fab fa-facebook"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-google"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                    </div>
                    <p class="account-text">No tienes cuenta? <a href="#" id="sign-up-btn2">Regístrate</a></p>
                </form>
                <!-- registro -->
                <form action="${pageContext.request.contextPath}/ControladorUsuarios" method="post" class="sign-up-form" id="sign-up-form">
                    <input type="hidden" name="action" value="register">
                    <h2 class="title">Regístrate</h2>
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" id="signup-username" name="username" placeholder="Nombre de usuario" required>
                    </div>
                    <div class="input-field">
                        <i class="fas fa-envelope"></i>
                        <input type="email" id="signup-email" name="email" placeholder="Correo" required>
                    </div>
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input type="password" id="signup-password" name="password" placeholder="Contraseña" required>
                    </div>
                    <div class="input-field">
                        <i class="fas fa-map-marker-alt"></i>
                        <input type="text" id="signup-password" name="address" placeholder="Dirección" required>
                    </div>
                    <div class="input-field">
                        <i class="fas fa-phone"></i>
                        <input type="int" id="signup-password" name="phone" placeholder="Teléfono" required>
                    </div>
                    <input type="submit" name="btnRegistrar" value="Regístrate" class="btn">
                    <p class="social-text">O regístrate con otra plataforma</p>
                    <div class="social-media">
                        <a href="#" class="social-icon">
                            <i class="fab fa-facebook"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-google"></i>
                        </a>
                        <a href="#" class="social-icon">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                    </div>
                    <p class="account-text">Ya tienes cuenta? <a href="#" id="sign-in-btn2">Inicia sesión</a></p>
                </form>
            </div>
            <div class="panels-container">
                <div class="panel left-panel">
                    <div class="content">
                        <h3>Eres miembro frecuente de nuestra página</h3>
                        <p>Te queremos mucho, no nos dejes pronto :c</p>
                        <button class="btn" id="sign-in-btn">Inicia sesión</button>
                    </div>
                    <img src="signin.svg" alt="" class="image">
                </div>
                <div class="panel right-panel">
                    <div class="content">
                        <h3>Nuevo en nuestra página?</h3>
                        <p>Ven, conócenos y seguro que ya no volver a comprar en otra tienda c:</p>
                        <button class="btn" id="sign-up-btn">Regístrate</button>
                    </div>
                    <img src="signup.svg" alt="" class="image">
                </div>
            </div>
        </div>
    </div>
    <%
        String message = (String) session.getAttribute("message");
        String error = (String) session.getAttribute("error");
        if (message != null) {
    %>
    <script>
        alert("<%= message%>");
    </script>
    <%
        session.removeAttribute("message");
    } else if (error != null) {
    %>
    <script>
        alert("<%= error%>");
    </script>
    <%
            session.removeAttribute("error");
        }
    %>

    <script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>