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
                                <button class="btn btn-purple">Editar Perfil</button>
                                <button class="btn btn-purple">Cerrar Sesión</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
