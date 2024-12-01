<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <!--MENU-->
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-titta sidebar sidebar-dark accordion" id="accordionSidebar" style="background-color: #1630BE !important;
            background-size: cover; color: white !important;">
            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="admin.jsp">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">
                    <img src="../imagenes/logo_g.jpg" style="width: 75%;" alt="" />
                </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0" />

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" th:href="@{/admin}">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />

            <!-- Heading -->
            <div class="sidebar-heading">Tablas</div>
            <!-- Divider -->
            <hr class="sidebar-divider" />
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ControladorAdmin?accion=paginaUsuarios">
                    <i class="fas fa-user-circle"></i>
                    <span>Usuarios</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ControladorAdmin?accion=paginaProductos">
                    <i class="fas fa-apple-alt"></i>
                    <span>Productos</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />       

            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ControladorAdmin?accion=paginaCategorias">
                    <i class="fas fa-calendar-check"></i>
                    <span>Categorias</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />

            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ControladorAdmin?accion=paginaMarcas">
                    <i class="fas fa-calendar-check"></i>
                    <span>Marcas</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" href="@{/categorias}">
                    <i class="fas fa-list"></i>
                    <span>Ordenes</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />

            <li class="nav-item">
                <a class="nav-link" th:href="@{/reservas}">
                    <i class="fas fa-calendar-check"></i>
                    <span>Metodos de Pago</span></a>
            </li>


            <!-- Divider -->
            <hr class="sidebar-divider" />

            <li class="nav-item">
                <a class="nav-link" th:href="@{/reservas}">
                    <i class="fas fa-calendar-check"></i>
                    <span>Detalles - Carrito</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />

            <li class="nav-item">
                <a class="nav-link" th:href="@{/reservas}">
                    <i class="fas fa-calendar-check"></i>
                    <span>Carrito</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider" />

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
        </ul>
    </body>
</html>
