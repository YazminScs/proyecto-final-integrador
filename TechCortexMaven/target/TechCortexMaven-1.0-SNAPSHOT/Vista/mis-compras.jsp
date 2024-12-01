<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mis Compras || TechCortex</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome for icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <!-- Estilos personalizados -->
        <style>
            .container {
                margin-top: 50px;
            }

            h1 {
                text-align: center;
                color: #007bff;
                font-size: 3rem;
                margin-bottom: 40px;
            }

            .card {
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                margin-bottom: 30px;
                background-color: #fff;
                overflow: hidden;
                transition: transform 0.3s ease;
            }

            .card:hover {
                transform: translateY(-10px);
            }

            .card-header {
                background-color: #007bff;
                color: white;
                padding: 20px;
                font-size: 1.5rem;
            }

            .card-body {
                padding: 30px;
                background-color: #f8f9fa;
                border-radius: 0 0 10px 10px;
            }

            .btn-info {
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                padding: 12px 20px;
                font-size: 1.1rem;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-info:hover {
                background-color: #0056b3;
            }

            .btn-collapse {
                background-color: transparent;
                border: none;
                color: #007bff;
                font-size: 1.2rem;
                padding: 10px 15px;
                cursor: pointer;
                transition: color 0.3s ease;
            }

            .btn-collapse:hover {
                color: #0056b3;
            }

            .collapse-icon {
                margin-left: 10px;
                font-size: 1.3rem;
            }

            .no-compras {
                text-align: center;
                font-size: 1.5rem;
                color: #007bff;
                margin-top: 50px;
                font-weight: 600;
            }
        </style>
    </head>
    <body class="bg-body">
        <%@include file="header.jsp" %>
        
        <div class="container">
            <c:if test="${not empty carritoInfo}">
                <c:forEach var="info" items="${carritoInfo}">
                    <div class="card">
                        <div class="card-header">
                            <h4>Carrito ID: ${info.carrito.carrito_id}</h4>
                        </div>

                        <div class="card-body">
                            <p><strong>Orden ID:</strong> ${info.orden.orden_id}</p>
                            <p><strong>Fecha de Orden:</strong> ${info.carrito.carrito_fecha}</p>
                            <p><strong>Estado de la Orden:</strong> ${info.orden.orden_estado}</p>
                            <p><strong>Total:</strong> $${info.carrito.carrito_total}</p>

                            <!-- Botón para desplegar detalles -->
                            <button class="btn-collapse" type="button" data-bs-toggle="collapse" data-bs-target="#detallesCarrito${info.carrito.carrito_id}" aria-expanded="false" aria-controls="detallesCarrito${info.carrito.carrito_id}">
                                <i class="fas fa-chevron-down collapse-icon"></i> Ver Detalles
                            </button>

                            <!-- Detalles del carrito -->
                            <div class="collapse" id="detallesCarrito${info.carrito.carrito_id}">
                                <h5>Detalles del Carrito:</h5>
                                <ul>
                                    <c:forEach var="detalle" items="${info.detalles}">
                                        <li>
                                            <strong>Producto:</strong> ${detalle.producto.nombre} <br>
                                            <strong>Cantidad:</strong> ${detalle.detalle_cant} <br>
                                            <strong>Precio:</strong> $${detalle.detalle_price} <br>
                                            <strong>Subtotal:</strong> $${detalle.detalle_cant * detalle.detalle_price}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${empty carritoInfo}">
                <p class="no-compras">No tienes compras recientes.</p>
            </c:if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

        <script>
            document.querySelectorAll('[data-bs-toggle="collapse"]').forEach(function (button) {
                button.addEventListener('click', function () {
                    var icon = this.querySelector('.collapse-icon');
                    var target = document.querySelector(this.getAttribute('data-bs-target'));

                    if (target.classList.contains('show')) {
                        icon.classList.remove('fa-chevron-up');
                        icon.classList.add('fa-chevron-down');
                    } else {
                        icon.classList.remove('fa-chevron-down');
                        icon.classList.add('fa-chevron-up');
                    }
                });
            });
        </script>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
