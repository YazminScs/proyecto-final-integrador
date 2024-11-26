<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalle de Producto - Mouse Logitech</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- CSS Personalizado -->
        <link href="<%=request.getContextPath()%>/Assets/CSS/producto-detalle.css" rel="stylesheet">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    </head>
    <body class="bg-body">

        <%@include file="header.jsp" %>


        <!-- Contenedor del producto -->
        <div class="container my-5">
            <div class="row">
                <!-- Galería de imágenes -->
                <div class="col-md-6 text-center">
                    <div class="main-image mb-4">
                        <img id="product-main-image" src="${producto.url_imagen}" class="img-fluid product-image">
                    </div>
                </div>

                <!-- Detalles del producto -->
                <div class="col-md-6">
                    <h1 class="product-title">${producto.nombre}</h1>
                    <p class="product-subtitle">Categoria: ${producto.categorias.nombre}</p>
                    <p class="product-price">Precio: S/ ${producto.precio}</p>

                    <!-- Descripción del producto -->
                    <h5 class="section-title">Descripción del Producto</h5>
                    <p class="product-description">
                        ${producto.descripcion}
                    </p>

                    <!-- Botones de acción -->
                    <div class="action-buttons">
                        <button class="btn btn-buy" onclick="addToCart()">Agregar al Carrito</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- JavaScript Personalizado -->
        <script src="${pageContext.request.contextPath}/Assets/JS/producto-detalle.js"></script>
    </body>
</html>
