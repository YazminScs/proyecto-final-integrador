<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/Assets/CSS/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/Assets/CSS/style-table.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/Assets/CSS/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">
            <%@include file="sidebar-admin.jsp" %>
            <!-- fin - menu -->

            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <%@include file="topbar-admin.jsp" %>

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Productos</h1>

                        <!-- Botón Registrar Producto -->
                        <a class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addProductModal">
                            <i class="fas fa-plus"></i> Registrar Producto
                        </a>

                        <!-- Tabla de Productos -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 fw-bold text-primary">Tabla de Productos</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-dark" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th class="text-light">Id</th>
                                                <th class="text-light">Nombre</th>
                                                <th class="text-light">Descripción</th>
                                                <th class="text-light">Precio</th>
                                                <th class="text-light">Stock</th>
                                                <th class="text-light">Imagen</th>
                                                <th class="text-light">Categoría</th>
                                                <th class="text-light">Marca</th>
                                                <th class="text-light">Acciones</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th class="text-light">Id</th>
                                                <th class="text-light">Nombre</th>
                                                <th class="text-light">Descripción</th>
                                                <th class="text-light">Precio</th>
                                                <th class="text-light">Stock</th>
                                                <th class="text-light">Imagen</th>
                                                <th class="text-light">Categoría</th>
                                                <th class="text-light">Marca</th>
                                                <th class="text-light">Acciones</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="producto" items="${listaProductos}">
                                                <tr>
                                                    <td class="text-truncate" style="max-width: 100px;">${producto.idProducto}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${producto.nombre}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${producto.descripcion}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${producto.precio}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${producto.stock}</td>
                                                    <td><img src="${producto.url_imagen}" alt="Imagen" width="50" height="50"></td>
                                                    <td class="text-truncate" style="max-width: 100px;">${producto.categorias.nombre}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${producto.marcas.nombre}</td>
                                                    <td>
                                                        <!-- Botón para abrir el modal -->
                                                        <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#editarModal" 
                                                                data-id="${producto.idProducto}" 
                                                                data-nombre="${producto.nombre}" 
                                                                data-descripcion="${producto.descripcion}" 
                                                                data-precio="${producto.precio}"
                                                                data-stock="${producto.stock}" 
                                                                data-imagen="${producto.url_imagen}"
                                                                data-categoria="${producto.categorias.idCategoria}" 
                                                                data-marca="${producto.marcas.idMarca}">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"></path>
                                                            </svg>
                                                            Editar
                                                        </button>

                                                        <form action="<%=request.getContextPath()%>/ControladorAdmin" method="GET" style="display: inline-block;">
                                                            <input type="hidden" name="codigo" value="${material.idMaterial}">
                                                            <button type="submit" name="accion" value="eliminar" class="btn btn-outline-danger">
                                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"></path>
                                                                <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"></path>
                                                                </svg>
                                                                Eliminar
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@include file="admin-footer.jsp" %>

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            Listo para irte?
                        </h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Selecciona cerrar sesión para salir de la actual sesión.
                    </div>
                    <div class="modal-footer">
                        <form th:action="@{/logout}" method="post">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">
                                Cancelar
                            </button>
                            <button type="submit" class="btn btn-danger">Cerrar Sesión</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/Assets/JS/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Assets/JS/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/Assets/JS/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/Assets/JS/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/Assets/JS/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/Assets/JS/chart-area-demo.js"></script>
    <script src="${pageContext.request.contextPath}/Assets/JS/chart-pie-demo.js"></script>

</body>
</html>
