<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gracias por tu compra ||TechCortex</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/Assets/CSS/final-compra.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="bg-body">

        <%@include file="header.jsp" %>

        <div class="container">
            <div class="section-title">¡Compra Finalizada!</div>

            <div class="completed-text">Gracias por tu compra. Tu orden ha sido procesado con éxito.</div>

            <div class="order-summary">
                <h3>Resumen del Pedido</h3>
                <table class="table table-bordered table-striped order-summary">
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detalle" items="${miDetalle}">
                            <tr>
                                <td>${detalle.producto.nombre}</td>
                                <td>${detalle.detalle_cant}</td>
                                <td>S/${detalle.detalle_price}</td>
                                <c:set var="precio_tot" value="${detalle.detalle_price * detalle.detalle_cant}" />
                                <td>S/<fmt:formatNumber value="${precio_tot}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3" class="text-end">Total</td>
                            <td>S/${total}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="animation-container">
                <button class="btn-purple">Descargar PDF</button>
            </div>
        </div>

        <%@include file="footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
