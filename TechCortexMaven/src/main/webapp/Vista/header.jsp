<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/CSS/style-header.css?v=1.0">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg fixed">
                <div class="container-fluid">
                    <a class="navbar-brand container-logo me-auto fs-3" href="<%=request.getContextPath()%>/index.jsp">
                        <img
                            src="https://github.com/EduardoVargasZumaeta/Tiendita_Foley/blob/main/techcortex-logo.jpeg?raw=true"
                            alt="Web Development"
                            class="icon-logo"
                            />
                        TechCortex
                    </a>
                    <div
                        class="offcanvas offcanvas-end"
                        tabindex="-1"
                        id="offcanvasNavbar"
                        aria-labelledby="offcanvasNavbarLabel"
                        >
                        <div class="offcanvas-header">
                            <a class="navbar-brand container-logo me-auto fs-3 offcanvas-title" id="offcanvasNavbarLabel" href="#">
                                <img
                                    src="https://github.com/EduardoVargasZumaeta/Tiendita_Foley/blob/main/techcortex-logo.jpeg?raw=true"
                                    alt="Web Development"
                                    class="icon-logo"
                                    />
                                TechCortex
                            </a>
                            <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="offcanvas"
                                aria-label="Close"
                                ></button>
                        </div>
                        <div class="offcanvas-body">
                            <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
                                <li class="nav-item">
                                    <a class="nav-link dropdown " aria-current="page" href="<%=request.getContextPath()%>/index.jsp">HomeTech</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link dropdown " aria-current="page" href="${pageContext.request.contextPath}/ControladorProductos?accion=catalogo">CatalogTech</a>
                                </li>
                                <li class="nav-item ">
                                    <a class="nav-link" href="<%=request.getContextPath()%>/Vista/nosotros.jsp">AboutTech</a>
                                </li>
                                <li class="nav-item ">
                                    <a class="nav-link" href="<%=request.getContextPath()%>/Vista/ubicaciones.jsp">LocationTech</a>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!--Login-->
                    <%@ page session="true" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                    <div class="text-center justify-content-center">
                        <c:choose>
                            <c:when test="${not empty sessionScope.username}">
                                <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/ControladorCarrito?accion=carrito">
                                            <i class="fa fa-shopping-cart"></i>
                                        </a>                                    
                                    </li>
                                    <li class="nav-item dropdown abajo">
                                        <a
                                            class="nav-link awn" 
                                            role="button" 
                                            aria-expanded="false"
                                            >
                                            Bienvenido, <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Usuario"%>
                                        </a>
                                        <ul class="dropdown-menu abajo">
                                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ControladorUsuarios?accion=perfil">Perfil</a></li>
                                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ControladorUsuarios?accion=compras">Mis compras</a></li>
                                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ControladorUsuarios?accion=logout">Cerrar sesión</a></li>
                                        </ul>
                                    </li>
                                </ul>

                            </c:when>
                            <c:otherwise>
                                <a href="<%=request.getContextPath()%>/Vista/login.jsp" class="login-button">Login</a>
                                <a href="<%=request.getContextPath()%>/Vista/login.jsp" class="login-button">Sign-up</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <!--Login-->
                    <button
                        class="navbar-toggler ms-1 p-1"
                        type="button"
                        data-bs-toggle="offcanvas"
                        data-bs-target="#offcanvasNavbar"
                        aria-controls="offcanvasNavbar"
                        aria-label="Toggle navigation"
                        >
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
            </nav>
        </header>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
