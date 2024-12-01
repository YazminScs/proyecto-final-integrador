<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
        <link href="<%=request.getContextPath()%>/Assets/CSS/style-card.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="container">
            <div class="d-flex">+
                <h5 class="g-col-6">Lista de Productos</h5>
                <a class="g-col-6">VER TODO -></a>
            </div>
            <div class="row">
                <c:forEach var="producto" items="${listaProductos}">
                    <div class="col-md-4 cocol-l-sm-6 mb-4">
                        <div class="card card-products bg-dark">
                            <img class="card-img-top img-product" src="${producto.url_imagen}" alt="${producto.nombre}">
                            <div class="card-body">
                                <div class="text-section">
                                    <h5 class="card-title">${producto.nombre}</h5>
                                    <p class="card-text">${producto.descripcion}</p>
                                </div>
                                <div class="cta-section">
                                    <p class="card-text"><strong>Precio:</strong> ${producto.precio}</p>
                                    <a href="#" class="btn btn-light">Comprar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
