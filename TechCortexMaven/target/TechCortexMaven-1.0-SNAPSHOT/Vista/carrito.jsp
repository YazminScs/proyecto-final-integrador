<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito || TechCortex</title>
        <link href="<%=request.getContextPath()%>/Assets/CSS/carrito.css" rel="stylesheet" type="text/css"/>
        <style>
            /* Estilos personalizados */
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f4f8;
            }

            .text-purple {
                color: #6f42c1; /* Morado */
            }

            /* Efecto de animación para el título */
            h1 {
                font-family: 'Arial', sans-serif;
                font-size: 3rem;
                color: #6f42c1;
                animation: bounceIn 2s ease-in-out;
            }

            /* Animación para el título */
            @keyframes bounceIn {
                0% {
                    transform: translateY(-50px);
                    opacity: 0;
                }
                60% {
                    transform: translateY(30px);
                    opacity: 1;
                }
                100% {
                    transform: translateY(0);
                    opacity: 1;
                }
            }

            .cart-items {
                background-color: white;
                padding: 20px;
                border-radius: 15px;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
                animation: slideIn 1.5s ease-out;
                margin-bottom: 30px;
            }

            /* Animación de deslizamiento para el carrito */
            @keyframes slideIn {
                0% {
                    transform: translateX(100%);
                    opacity: 0;
                }
                100% {
                    transform: translateX(0);
                    opacity: 1;
                }
            }

            .list-group-item {
                display: inline-flex !important;
                align-items: center;
                justify-content: space-between;
                padding: 15px;
                margin-bottom: 10px;
                background-color: #fafafa;
                border-radius: 8px;
                border: none;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease-in-out;
                animation: fadeIn 0.5s ease-in;
            }

            /* Animación para los productos */
            @keyframes fadeIn {
                0% {
                    opacity: 0;
                }
                100% {
                    opacity: 1;
                }
            }

            /* Estilo para el contenedor de imagen y nombre */
            .producto-info {
                display: flex;
                align-items: center;
            }

            .producto-info img {
                width: 50px;
                height: 50px;
                margin-right: 15px;
                border-radius: 5px;
            }

            /* Efecto de hover para los productos */
            .list-group-item:hover {
                transform: translateX(10px);
                background-color: #f1f1f1;
            }

            /* Estilo del botón de eliminar */
            .btn-remove {
                background-color: #e83e8c !important;
                color: white !important;
                border: none !important;
                padding: 8px 15px !important;
                border-radius: 25px !important;
                text-decoration: none !important;
                cursor: pointer !important;
                transition: background-color 0.3s ease !important;
            }

            /* Efecto de hover para el botón de eliminar */
            .btn-remove:hover {
                background-color: #c13584 !important;
                transform: scale(1.1) !important;
            }

            /* Controles de cantidad */
            .cantidad-control {
                display: flex !important;
                align-items: center !important;
            }

            .cantidad-control a {
                text-decoration: none !important;
                background-color: #6f42c1 !important;
                color: white !important;
                border: none !important;
                padding: 8px !important;
                margin: 0 5px !important;
                border-radius: 5px !important;
                cursor: pointer !important;
                transition: background-color 0.3s ease !important;
            }

            /* Efecto de hover para los botones de cantidad */
            .cantidad-control a:hover {
                background-color: #4e2a96 !important;
            }

            /* Estilo del botón de finalizar compra */
            #finalizar-compra {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 12px 25px;
                border-radius: 50px;
                cursor: pointer;
                transition: background-color 0.3s ease, transform 0.2s ease;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            }

            /* Efecto de hover para finalizar compra */
            #finalizar-compra:hover {
                background-color: #218838;
                transform: scale(1.05);
            }

            #total {
                font-size: 1.5rem;
                font-weight: bold;
                color: #6f42c1;
            }
            /* Notificación */
            .notification {
                position: fixed;
                bottom: 20px;
                right: 20px;
                background-color: #6c63ff;
                color: white;
                padding: 15px 20px;
                border-radius: 5px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
                font-size: 1rem;
                opacity: 0;
                visibility: hidden;
                transform: translateY(30px);
                transition: all 0.5s ease;
            }

            .notification.visible {
                opacity: 1;
                visibility: visible;
                transform: translateY(0);
            }
        </style>
    </head>
    <body class="bg-body">
        <%@include file="header.jsp" %>

        <div class="container mt-5" style="margin-bottom: 20px !important;">
            <h1 class="text-center text-purple mb-4">Carrito de Compras</h1>

            <!-- Lista de productos en el carrito -->
            <div class="cart-items">
                <ul class="list-group" id="lista-carrito">
                    <c:forEach var="detalle" items="${miDetalle}">
                        <li class="list-group-item">
                            <div class="producto-info">
                                <img src="${detalle.producto.url_imagen}" alt="${detalle.producto.nombre}">
                                <span>${detalle.producto.nombre}</span>
                            </div>
                            <div class="cantidad-control">
                                <a href="javascript:void(0);" data-cantidad="${detalle.detalle_cant}" data-id="${detalle.producto.idProducto}" data-carrito-id="${detalle.carrito.carrito_id}" onclick="disminuirCantidadElemento(this)">-</a>
                                <span>${detalle.detalle_cant}</span>
                                <a href="javascript:void(0);" data-cantidad="${detalle.detalle_cant}" data-id="${detalle.producto.idProducto}" data-carrito-id="${detalle.carrito.carrito_id}" onclick="aumentarCantidadElemento(this)">+</a>
                            </div>
                            <span>S/${detalle.detalle_price}</span>
                            <c:set var="precio_tot" value="${detalle.detalle_price * detalle.detalle_cant}" />
                            <span>S/<fmt:formatNumber value="${precio_tot}" type="number" maxFractionDigits="2" minFractionDigits="2" /></span>
                            <a class="btn-remove" href="javascript:void(0);" data-id="${detalle.producto.idProducto}" data-carrito-id="${detalle.carrito.carrito_id}" onclick="eliminarProductoElemento(this)">Eliminar</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="d-flex justify-content-between mt-3">
                <h4>Total: S/<span id="total">${total}</span></h4>
                <a href="<%=request.getContextPath()%>/ControladorCompra?accion=continuarCompra" class="btn btn-primary" id="finalizar-compra">Continuar Compra</a>
            </div>
        </div>
        <div id="notification" class="notification"></div>

        <%@include file="footer.jsp" %>

        <script>
            function showNotification(message) {
                const notification = document.getElementById('notification');
                notification.textContent = message;
                notification.classList.add('visible');

                setTimeout(() => {
                    notification.classList.remove('visible');
                }, 3000);
            }

            function eliminarProductoElemento(elemento) {
                const idProducto = elemento.getAttribute('data-id');
                const carritoId = elemento.getAttribute('data-carrito-id');
                eliminarProducto(idProducto, carritoId);
            }

            function aumentarCantidadElemento(elemento) {
                const idProducto = elemento.getAttribute('data-id');
                const carritoId = elemento.getAttribute('data-carrito-id');
                const cantidad = elemento.getAttribute('data-cantidad');
                aumentarCantidad(idProducto, carritoId, cantidad);
            }

            function disminuirCantidadElemento(elemento) {
                const idProducto = elemento.getAttribute('data-id');
                const carritoId = elemento.getAttribute('data-carrito-id');
                const cantidad = elemento.getAttribute('data-cantidad');
                disminuirCantidad(idProducto, carritoId, cantidad);
            }

            function eliminarProducto(idProducto, carritoId) {
                fetch(`/TechCortexMaven/ControladorCarrito?accion=eliminarProducto`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        'idProducto': idProducto,
                        'carritoId': carritoId
                    })
                })
                        .then(response => {
                            if (response.ok) {
                                return response.text();
                            } else {
                                throw new Error('No se pudo eliminar el producto del carrito.');
                            }
                        })
                        .then(mensaje => {
                            window.location.reload();
                            showNotification(mensaje);
                        })
                        .catch(error => {
                            showNotification('Error: ' + error.message);
                        });
            }

            function aumentarCantidad(idProducto, carritoId, cantidad) {
                fetch(`/TechCortexMaven/ControladorCarrito?accion=aumentarCantidad`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        'idProducto': idProducto,
                        'carritoId': carritoId,
                        'cantidad': cantidad
                    })
                })
                        .then(response => {
                            if (response.ok) {
                                return response.text();
                            } else {
                                throw new Error('No se pudo aumentar la cantidad del producto en el carrito.');
                            }
                        })
                        .then(mensaje => {
                            showNotification(mensaje);
                            window.location.reload();
                        })
                        .catch(error => {
                            showNotification('Error: ' + error.message);
                        });
            }

            function disminuirCantidad(idProducto, carritoId, cantidad) {
                fetch(`/TechCortexMaven/ControladorCarrito?accion=disminuirCantidad`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({
                        'idProducto': idProducto,
                        'carritoId': carritoId,
                        'cantidad': cantidad
                    })
                })
                        .then(response => {
                            if (response.ok) {
                                return response.text();
                            } else {
                                throw new Error('No se pudo disminuir la cantidad del producto en el carrito.');
                            }
                        })
                        .then(mensaje => {
                            showNotification(mensaje);
                            window.location.reload();
                        })
                        .catch(error => {
                            showNotification('Error: ' + error.message);
                        });
            }
        </script>
    </body>
</html>
