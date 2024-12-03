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

                        <div class="d-flex justify-content-start">
                            <button type="button" class="btn btn-primary mb-3 me-2" data-bs-toggle="modal" data-bs-target="#registrarModal">
                                <i class="bi bi-person-plus"></i>Registrar Usuario
                            </button>

                            <form action="<%=request.getContextPath()%>/ControladorAdmin" method="GET" style="display: inline;">
                                <input type="hidden" name="accion" value="generarExcelUsuarios">
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
                                                <th class="text-light">Correo</th>
                                                <th class="text-light">Contraseña</th>
                                                <th class="text-light">Domicilio</th>
                                                <th class="text-light">Celular</th>
                                                <th class="text-light">Rol</th>
                                                <th class="text-light">Acciones</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th class="text-light">Id</th>
                                                <th class="text-light">Nombre</th>
                                                <th class="text-light">Correo</th>
                                                <th class="text-light">Contraseña</th>
                                                <th class="text-light">Domicilio</th>
                                                <th class="text-light">Celular</th>
                                                <th class="text-light">Rol</th>
                                                <th class="text-light">Acciones</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach var="usuario" items="${listaUsuarios}">
                                                <tr>
                                                    <td class="text-truncate" style="max-width: 100px;">${usuario.id}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${usuario.username}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${usuario.email}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${usuario.password}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${usuario.address}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${usuario.phone}</td>
                                                    <td class="text-truncate" style="max-width: 100px;">${usuario.rol}</td>
                                                    <td>
                                                        <!-- Botón para abrir el modal -->
                                                        <button type="button" 
                                                                class="btn btn-outline-success" 
                                                                data-bs-toggle="modal" 
                                                                data-bs-target="#editarModal" 
                                                                data-id="${usuario.id}" 
                                                                data-username="${usuario.username}" 
                                                                data-email="${usuario.email}" 
                                                                data-password="${usuario.password}" 
                                                                data-address="${usuario.address}" 
                                                                data-phone="${usuario.phone}" 
                                                                data-rol="${usuario.rol}">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"></path>
                                                            </svg>
                                                            Editar
                                                        </button>

                                                        <form action="<%=request.getContextPath()%>/ControladorAdmin?accion=eliminarUsuario" method="GET" style="display: inline-block;">
                                                            <input type="hidden" name="id" value="${usuario.id}">
                                                            <button type="submit" name="accion" value="eliminarUsuario" class="btn btn-outline-danger">
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

            <!-- Modal para editar usuario -->
            <div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editarModalLabel">Editar Usuario</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="<%=request.getContextPath()%>/ControladorAdmin?accion=editarUsuario" method="POST">
                            <div class="modal-body">
                                <input type="hidden" id="editar-id" name="id">

                                <div class="mb-3">
                                    <label for="editar-username" class="form-label">Nombre de Usuario</label>
                                    <input type="text" class="form-control" id="editar-username" name="username" required>
                                </div>

                                <div class="mb-3">
                                    <label for="editar-email" class="form-label">Correo Electrónico</label>
                                    <input type="email" class="form-control" id="editar-email" name="email" required>
                                </div>

                                <div class="mb-3">
                                    <label for="editar-address" class="form-label">Dirección</label>
                                    <input type="text" class="form-control" id="editar-address" name="address" required>
                                </div>

                                <div class="mb-3">
                                    <label for="editar-phone" class="form-label">Teléfono</label>
                                    <input type="number" class="form-control" id="editar-phone" name="phone" required>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal para el registro -->
            <div class="modal fade" id="registrarModal" tabindex="-1" aria-labelledby="registrarModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/ControladorAdmin?accion=registrarUsuario" method="POST">
                            <div class="modal-header">
                                <h5 class="modal-title" id="registrarModalLabel">Registrar Nuevo Usuario</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="username" class="form-label">Nombre de Usuario</label>
                                    <input type="text" class="form-control" id="username" name="username" required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Correo Electrónico</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Contraseña</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Dirección</label>
                                    <input type="text" class="form-control" id="address" name="address" required>
                                </div>
                                <div class="mb-3">
                                    <label for="phone" class="form-label">Teléfono</label>
                                    <input type="number" class="form-control" id="phone" name="phone" required>
                                </div>
                                <div class="mb-3">
                                    <label for="rol" class="form-label">Rol</label>
                                    <select class="form-select" id="rol" name="rol" required>
                                        <option value="Administrador">Administrador</option>
                                        <option value="Usuario">Usuario</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary" name="accion" value="registrarUsuario">Registrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@include file="admin-footer.jsp" %>

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
        <script src="${pageContext.request.contextPath}/Assets/JS/admin-usuario-edit.js" type="text/javascript"></script>
    </body>
</html>
