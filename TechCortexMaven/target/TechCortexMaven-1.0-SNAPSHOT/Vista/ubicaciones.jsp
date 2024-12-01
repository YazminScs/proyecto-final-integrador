<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ubicaciones || TechCortex</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/CSS/ubicaciones.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCXcWYs4918jluT3QuQdHAec9ffxPABpK0"></script>
    </head>

    <body style="background: url(https://media.istockphoto.com/id/1303182525/es/foto/fondo-abstracto-blanco-azul-claro-degradado-de-color-p%C3%BArpura-desenfocado.jpg?s=612x612&w=0&k=20&c=cWDzgcPOCGDqzmMS0tOOpri65oN7BMVDWQPmAKFEPxY=) no-repeat top center / cover, black;">

        <%@include file="../Vista/header.jsp" %>

        <div class="container-fluid contenedores-box-shadow fondoDePantalla2   ">
            <br>
            <h2 class="text-white text-center mb-4 mt-2 fw-bold fs-1">BUSCA TU TIENDA MÁS CERCANA</h2>

            <div class="row ms-5 me-5 mt-5">
                <div class="col-md-4 ">
                    <div class="mb-3  ">
                        <label for="citySelector" class="form-label fw-bold fs-5 text-white">Ciudad</label>
                        <select id="citySelector" class="form-select box-shadow-dos fw-bold fs-5">
                            <option value="" class="fw-bold fs-5">Selecciona una ciudad</option>
                            <option value="lima" class="fw-bold fs-5">Lima</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="districtSelector" class="form-label fw-bold fs-5 text-white">Distrito</label>
                        <select id="districtSelector" class="form-select box-shadow-dos fw-bold fs-5">
                            <option value="" class="fw-bold fs-5">Selecciona un distrito</option>
                        </select>
                    </div>


                    <div id="storeList" class="bg-white border p-3 overflow-auto box-shadow-dos" style="max-height: 500px;">

                    </div>
                    <br>

                </div>

                <div class="col-md-8 ">
                    <div class="d-flex flex-column m-5 ">

                        <div id="map" class="border rounded mt-3 box-shadow-tres" style="height: 500px;"></div>
                    </div>
                </div>
            </div>
        </div>

        <section class="container-fluid  ">

            <div class="container" id="featuresSection">
                <br>
                <br>
                <h2 class="text-center mb-4 text-primary fw-bold pb-2  border-3 ">
                    ¿Por qué elegir <span class="text-black">TechCortex</span>?
                </h2>

                <div class="row">
                    <div class="col-md-4 mb-4 ">
                        <div class="card text-center contenedores-box-shadow">
                            <div class="card-body">
                                <h5 class="card-title">Últimas Ofertas</h5>
                                <p class="card-text">Siempre tendrás acceso a las mejores promociones en tecnología. ¡No te las
                                    pierdas!</p>
                                <img src="https://github.com/EduardoVargasZumaeta/Tiendita_Foley/blob/main/Imagenes%20Generales/ofertas.png?raw=true" class="img-fluid my-3" alt="Ofertas de TechCortex"
                                     style="max-height: 150px;">
                                <p class="card-text">En TechCortex, nos esforzamos por ofrecerte los productos más novedosos a
                                    precios inigualables. Desde dispositivos móviles hasta accesorios de última generación,
                                    ¡aquí encontrarás todo lo que necesitas!</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card text-center contenedores-box-shadow">
                            <div class="card-body">
                                <h5 class="card-title">Suscripción Personalizada</h5>
                                <p class="card-text">Recibe actualizaciones y ofertas que se adaptan a tus intereses.
                                    ¡Suscríbete y mantente informado!</p>
                                <img src="https://github.com/EduardoVargasZumaeta/Tiendita_Foley/blob/main/Imagenes%20Generales/Personalizado.jpg?raw=true" class="img-fluid my-3" alt="Suscripción Personalizada"
                                     style="max-height: 150px;">
                                <p class="card-text">Al registrarte, tendrás acceso a contenido exclusivo, recomendaciones de
                                    productos y notificaciones sobre descuentos especiales que se ajustan a tus preferencias.
                                    ¡No te quedes fuera de las novedades!</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="card text-center contenedores-box-shadow">
                            <div class="card-body">
                                <h5 class="card-title">Soporte 24/7</h5>
                                <p class="card-text">Nuestro equipo está siempre disponible para ayudarte con cualquier pregunta
                                    o inquietud.</p>
                                <img src="https://github.com/EduardoVargasZumaeta/Tiendita_Foley/blob/main/Imagenes%20Generales/soporte.png?raw=true" class="img-fluid my-3" alt="Soporte 24/7"
                                     style="max-height: 150px;">
                                <p class="card-text">Ya sea que necesites asistencia técnica, información sobre un producto o
                                    ayuda con tu compra, nuestro servicio al cliente está aquí para ti a cualquier hora del día.
                                    ¡Tu satisfacción es nuestra prioridad!</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="../Vista/footer.jsp" %>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="<%=request.getContextPath()%>/Assets/JS/ubiScript.js" defer></script>
    </body>

</html>