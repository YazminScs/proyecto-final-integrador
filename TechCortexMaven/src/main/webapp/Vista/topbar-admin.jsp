<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Assets/CSS/style-header.css?v=1.0">
    </head>
    <body>
        <!--Topbar-->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            <!-- Sidebar Toggle (Topbar) -->
            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                <i class="fa fa-bars"></i>
            </button>

            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">
                <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                <li class="nav-item dropdown no-arrow d-sm-none">
                    <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-search fa-fw"></i>
                    </a>
                    <!-- Dropdown - Messages -->
                    <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                         aria-labelledby="searchDropdown">
                        <form class="form-inline mr-auto w-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                       aria-label="Search" aria-describedby="basic-addon2" />
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </li>
                <li class="nav-item dropdown no-arrow">
                    <!--Login-->
                    <%@ page session="true" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

                    <div class="text-center justify-content-center">
                        <c:choose>
                            <c:when test="${not empty sessionScope.username}">
                                <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
                                    <li class="nav-item dropdown">
                                        <a
                                            class="nav-link dropdown text-black" 
                                            role="button" 
                                            aria-expanded="false"

                                            >
                                            Bienvenido, <%= session.getAttribute("username") != null ? session.getAttribute("username") : "Usuario"%>
                                        </a>
                                        <ul class="dropdown-menu">
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
                </li>
            </ul>
        </nav>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</html>
