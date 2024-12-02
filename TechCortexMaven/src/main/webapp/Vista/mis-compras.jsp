<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mis Compras || TechCortex</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Colores morados personalizados */
            .btn-purple {
                background-color: #6f42c1 !important;
                color: white !important;
            }
            .btn-purple:hover {
                background-color: #5a32a3 !important;
                color: white !important;
            }
            .card-header, .modal-header {
                background-color: #6f42c1 !important;
                color: white;
            }
            .modal-body {
                background-color: #f8f9fa;
            }
            .no-compras {
                color: #6f42c1;
                font-size: 1.2rem;
                font-weight: bold;
            }
        </style>
    </head>
    <body class="bg-body">
        <%@include file="header.jsp" %>

        <div class="container mt-4">
            <c:if test="${not empty carritoInfo}">
                <c:forEach var="info" items="${carritoInfo}">
                    <div class="card mb-3 shadow-sm">
                        <div class="card-header text-white">
                            <h4>Carrito ID: ${info.carrito.carrito_id}</h4>
                        </div>

                        <div class="card-body">
                            <p><strong>Orden ID:</strong> ${info.orden.orden_id}</p>
                            <p><strong>Fecha de Orden:</strong> ${info.carrito.carrito_fecha}</p>
                            <p><strong>Estado de la Orden:</strong> ${info.orden.orden_estado}</p>
                            <p><strong>Total:</strong> S/${info.carrito.carrito_total}</p>

                            <!-- Botón para abrir el modal con animación -->
                            <button class="btn btn-purple" 
                                    type="button" 
                                    data-bs-toggle="modal" 
                                    data-bs-target="#detallesModal${info.carrito.carrito_id}">
                                Ver Detalles
                            </button>

                            <!-- Modal para mostrar detalles con animación -->
                            <div class="modal fade" id="detallesModal${info.carrito.carrito_id}" tabindex="-1" aria-labelledby="detallesModalLabel${info.carrito.carrito_id}" aria-hidden="true">
                                <div class="modal-dialog" style="margin-top: 10rem">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="detallesModalLabel${info.carrito.carrito_id}">Detalles del Carrito ID: ${info.carrito.carrito_id}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <h5>Detalles del Carrito:</h5>
                                            <ul>
                                                <c:forEach var="detalle" items="${info.detalles}">
                                                    <li>
                                                        <strong>Producto:</strong> ${detalle.producto.nombre} <br>
                                                        <strong>Cantidad:</strong> ${detalle.detalle_cant} <br>
                                                        <strong>Precio:</strong> S/${detalle.detalle_price} <br>
                                                        <strong>Subtotal:</strong> S/${detalle.detalle_cant * detalle.detalle_price}
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-purple" data-bs-dismiss="modal">Cerrar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${empty carritoInfo}">
                <p class="no-compras">No tienes compras recientes.</p>
            </c:if>
        </div>

        <%@include file="footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>

        <!-- Animaciones del modal -->
        <script>
            var myModal = document.getElementById('detallesModal');
            myModal.addEventListener('show.bs.modal', function (event) {
                // Aquí puedes añadir efectos adicionales si lo deseas
            });
        </script>
    </body>
</html>
