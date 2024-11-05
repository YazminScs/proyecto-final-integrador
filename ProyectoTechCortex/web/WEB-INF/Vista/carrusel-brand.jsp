<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-slider-brand.css"/> 
        <title>Carrusel de Marcas</title>
    </head>
    <body>
        <div class="container-fluid carousel-container-brand">
            <div class="slick-carousel brand m-5" id="brand">
                <c:forEach var="marca" items="${listaMarcas}">
                    <div class="m-1" ><img src="${marca.url_imagen}" alt="${marca.nombre}" class="border border-dark shadow p-2 brand"/></div>
                    </c:forEach>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/slider-brand-menu.js"></script> 
    </body>
</html>
