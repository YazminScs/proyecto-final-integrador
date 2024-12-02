<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil || TechCortex</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/Assets/CSS/perfil.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="bg-body">

        <%@include file="header.jsp" %>

        <div class="cont">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="profile-card">
                        <div class="profile-header">
                            <h2>${usuario.username}</h2>
                            <p>${usuario.rol}</p>
                        </div>
                        <div class="profile-content">
                            <h5>Información:</h5>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><strong>Email: </strong>${usuario.email}</li>
                                <li class="list-group-item"><strong>Teléfono: </strong>${usuario.phone}</li>
                                <li class="list-group-item"><strong>Dirección: </strong>${usuario.address}</li>
                            </ul>
                            <div class="mt-4 d-flex justify-content-between">
                                <button class="btn btn-purple" data-bs-toggle="modal" data-bs-target="#editProfileModal">Editar Perfil</button>
                                <a class="btn btn-purple" href="<%=request.getContextPath()%>/ControladorUsuarios?accion=logout">Cerrar sesión</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>

        <!-- Modal -->
        <div class="modal " id="editProfileModal" tabindex="-1" aria-labelledby="editProfileModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="margin-top: 10rem">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editProfileModalLabel">Editar Perfil</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="<%=request.getContextPath()%>/ControladorUsuarios?action=editar" method="post">
                            <div class="mb-3">
                                <label for="username" class="form-label">Nombre de usuario</label>
                                <input type="text" class="form-control" id="username" name="username" value="${usuario.username}" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" required>
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label">Teléfono</label>
                                <input type="text" class="form-control" id="phone" name="phone" value="${usuario.phone}" required>
                            </div>
                            <div class="mb-3">
                                <label for="address" class="form-label">Dirección</label>
                                <input type="text" class="form-control" id="address" name="address" value="${usuario.address}" required>
                            </div>
                            <input type="hidden" name="accion" value="editarPerfil">
                            <button type="submit" class="btn btn-purple">Guardar cambios</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
        <script>
            alert("<%= message%>");
        </script>
        <%
                session.removeAttribute("message");
            }
        %>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
