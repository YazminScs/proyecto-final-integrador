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

        <!-- Estilo css (tabla) -->
        <link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />
        
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

                        <div class="d-flex mb-3 justify-content-start">
                            <!-- Botón para abrir el modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#agregarMarcaModal">
                                Agregar Marca
                            </button>
                            <form action="<%=request.getContextPath()%>/ControladorAdmin" method="GET" style="display: inline;">
                                <input type="hidden" name="accion" value="generarExcelMarcas">
                                <button type="submit" class="btn btn-success">
                                    Descargar Excel
                                </button>
                            </form>
                        </div>

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
                                                <th class="text-light">Imagen</th>
                                                <th class="text-light">Acciones</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th class="text-light">Id</th>
                                                <th class="text-light">Nombre</th>
                                                <th class="text-light">Imagen</th>
                                                <th class="text-light">Acciones</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="marca" items="${listaMarcas}">
                                                <tr>
                                                    <td class="text-truncate" style="max-width: 100px;">${marca.idMarca}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${marca.nombre}</td>
                                                    <td><img src="${marca.url_imagen}" alt="${marca.nombre}" width="100" height="50"></td>
                                                    <td>
                                                        <form action="<%=request.getContextPath()%>/ControladorAdmin?accion=eliminarMarca" method="GET" style="display: inline-block;">
                                                            <input type="hidden" name="idMarca" value="${marca.idMarca}">
                                                            <button type="submit" name="accion" value="eliminarMarca" class="btn btn-outline-danger">
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
            <!-- Modal para agregar una nueva marca -->
            <div class="modal fade" id="agregarMarcaModal" tabindex="-1" aria-labelledby="agregarMarcaModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="agregarMarcaModalLabel">Agregar Marca</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="<%=request.getContextPath()%>/ControladorAdmin" method="POST">
                            <input type="hidden" name="accion" value="registrarMarca">
                            <div class="modal-body">
                                <!-- Campo para el nombre de la marca -->
                                <div class="mb-3">
                                    <label for="nombreMarca" class="form-label">Nombre de la Marca</label>
                                    <input type="text" class="form-control" id="nombreMarca" name="nombre" required>
                                </div>

                                <!-- Campo para la URL de la imagen -->
                                <div class="mb-3">
                                    <label for="urlImagenMarca" class="form-label">URL de Imagen</label>
                                    <input type="text" class="form-control" id="urlImagenMarca" name="url_imagen" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-primary">Agregar Marca</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@include file="admin-footer.jsp" %>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>

    <!-- DataTable custom configuration -->
    <script>
        $(document).ready(function () {
            $('#dataTable').DataTable({
                "paging": true,
                "pageLength": 5,
                "lengthMenu": [5, 10, 25, 50],
                "dom": '<"top"i>rt<"bottom"flp><"clear">'
            });
        });
    </script>
    
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
