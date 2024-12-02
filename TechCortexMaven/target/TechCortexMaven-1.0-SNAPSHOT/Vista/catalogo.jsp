<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo || TechCortex</title>
        <link href="<%=request.getContextPath()%>/Assets/CSS/catalogo.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
    </head>
    <body class="bg-body">

        <%@include file="header.jsp" %>

        <div class="container my-5">
            <h1 class="text-center mb-4" style="color: black; font-size: 3rem; font-weight: bold;">Catálogo de Productos</h1>

            <!-- Botones de Filtro -->
            <div class="d-flex justify-content-center mb-4">
                <button class="btn btn-outline-primary mx-2 filter-btn active" data-category="all">Todos</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Mouses">Mouses</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Teclados">Teclados</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Auriculares">Auriculares</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Monitores">Monitores</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Sillas Gaming">Sillas Gaming</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Webcams">Webcams</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Alfombrillas">Alfombrillas</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Parlantes">Parlantes</button>
                <button class="btn btn-outline-primary mx-2 filter-btn" data-category="Microfonos">Microfonos</button>
            </div>

            <!-- Productos -->
            <div class="row product-grid">
                <c:forEach var="producto" items="${listaProductos}">
                    <div class="col-md-4 mb-4 product-card" data-category="${producto.categorias.nombre}">
                        <div class="card shadow-sm animate">
                            <img src="${producto.url_imagen}" class="card-img-top" style="width: 414.39px !important; height: 310.79px !important;" alt="${producto.nombre}">
                            <div class="card-body text-center">
                                <h5 class="card-title">${producto.nombre}</h5>
                                <p class="card-text">Precio: S/${producto.precio}</p>
                                <a href="${pageContext.request.contextPath}/ControladorDetalle?idProducto=${producto.idProducto}" class="btn btn-light" style="background-color: #9D79AE !important;">Comprar <i class="fa fa-cart-arrow-down"></i></a>                               
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/Assets/JS/catalogo.js" type="text/javascript"></script>
    </body>
</html>
