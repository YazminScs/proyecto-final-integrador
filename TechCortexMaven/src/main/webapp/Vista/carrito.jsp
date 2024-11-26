<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito || TechCortex</title>
        <link href="<%=request.getContextPath()%>/Assets/CSS/carrito.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="bg-body">
        <%@include file="header.jsp" %>

        <div class="container mt-5" style="margin-bottom: 20px !important;">
            <h1 class="text-center text-purple mb-4">Carrito de Compras</h1>

            <!-- Lista de productos en el carrito -->
            <div class="cart-items">
                <ul class="list-group" id="lista-carrito"><li class="list-group-item">
                        <div class="producto-info">
                            <img src="https://via.placeholder.com/50" alt="Producto 1">
                            <span>Producto 1</span>
                        </div>
                        <div class="cantidad-control">
                            <button onclick="cambiarCantidad(0, 0)">-</button>
                            <span>1</span>
                            <button onclick="cambiarCantidad(0, 2)">+</button>
                        </div>
                        <span>$20.00</span>
                        <button class="btn-remove" onclick="eliminarProducto(0)">Eliminar</button>
                    </li><li class="list-group-item">
                        <div class="producto-info">
                            <img src="https://via.placeholder.com/50" alt="Producto 2">
                            <span>Producto 2</span>
                        </div>
                        <div class="cantidad-control">
                            <button onclick="cambiarCantidad(1, 0)">-</button>
                            <span>1</span>
                            <button onclick="cambiarCantidad(1, 2)">+</button>
                        </div>
                        <span>$35.00</span>
                        <button class="btn-remove" onclick="eliminarProducto(1)">Eliminar</button>
                    </li><li class="list-group-item">
                        <div class="producto-info">
                            <img src="https://via.placeholder.com/50" alt="Producto 3">
                            <span>Producto 3</span>
                        </div>
                        <div class="cantidad-control">
                            <button onclick="cambiarCantidad(2, 0)">-</button>
                            <span>1</span>
                            <button onclick="cambiarCantidad(2, 2)">+</button>
                        </div>
                        <span>$15.00</span>
                        <button class="btn-remove" onclick="eliminarProducto(2)">Eliminar</button>
                    </li>
                </ul>
            </div>

            <div class="d-flex justify-content-between mt-3">
                <h4>Total: $<span id="total">70.00</span></h4>
                <a href="<%=request.getContextPath()%>/Vista/proceso-compra.jsp" class="btn btn-primary" id="finalizar-compra">Continuar Compra</a>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script src="<%=request.getContextPath()%>/Assets/JS/carrito.js" type="text/javascript"></script>
    </body>
</html>
