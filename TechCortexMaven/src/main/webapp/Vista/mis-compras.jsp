<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis ordenes || TechCortex</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/Assets/CSS/mis-compras.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="bg-body">
        <%@include file="header.jsp" %>
        
        <div class="container">
            <h1 class="text-center mb-5" style="color: #512DA8;">Mis Órdenes</h1>

            <!-- Order Card 1 -->
            <div class="order-card">
                <div class="order-header">
                    <h5>Orden #12345</h5>
                    <span class="status completed">Completado</span>
                </div>
                <p><strong>Fecha:</strong> 25 de Noviembre, 2024</p>
                <p><strong>Total:</strong> $120.00</p>
                <div class="d-flex justify-content-end">
                    <button class="btn-view" data-bs-toggle="modal" data-bs-target="#orderDetailsModal" 
                            onclick="loadOrderDetails('Orden #12345', '25 de Noviembre, 2024', '$120.00', 'Producto A x2, Producto B x1')">
                        Ver Detalles
                    </button>
                </div>
            </div>

            <!-- Order Card 2 -->
            <div class="order-card">
                <div class="order-header">
                    <h5>Orden #12344</h5>
                    <span class="status pending">Pendiente</span>
                </div>
                <p><strong>Fecha:</strong> 24 de Noviembre, 2024</p>
                <p><strong>Total:</strong> $85.50</p>
                <div class="d-flex justify-content-end">
                    <button class="btn-view" data-bs-toggle="modal" data-bs-target="#orderDetailsModal" 
                            onclick="loadOrderDetails('Orden #12344', '24 de Noviembre, 2024', '$85.50', 'Producto C x1, Producto D x3')">
                        Ver Detalles
                    </button>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="orderDetailsModal" tabindex="-1" aria-labelledby="orderDetailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="orderDetailsModalLabel">Detalles de la Orden</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p><strong>ID Orden:</strong> <span id="modalOrderId"></span></p>
                        <p><strong>Fecha:</strong> <span id="modalOrderDate"></span></p>
                        <p><strong>Total:</strong> <span id="modalOrderTotal"></span></p>
                        <p><strong>Productos:</strong> <span id="modalOrderProducts"></span></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
        function loadOrderDetails(orderId, orderDate, orderTotal, orderProducts) {
            document.getElementById('modalOrderId').textContent = orderId;
            document.getElementById('modalOrderDate').textContent = orderDate;
            document.getElementById('modalOrderTotal').textContent = orderTotal;
            document.getElementById('modalOrderProducts').textContent = orderProducts;
        }
    </script>
    </body>
</html>
