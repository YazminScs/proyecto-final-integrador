<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width initial-scale=1.0">
        <title>Nosotros || TechCortex</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Assets/CSS/nosotros.css">
    </head>
    <body style="background: url(https://media.istockphoto.com/id/1303182525/es/foto/fondo-abstracto-blanco-azul-claro-degradado-de-color-p%C3%BArpura-desenfocado.jpg?s=612x612&w=0&k=20&c=cWDzgcPOCGDqzmMS0tOOpri65oN7BMVDWQPmAKFEPxY=) no-repeat top center / cover, black;">
        <%@include file="../Vista/header.jsp" %>
        <div class="container-fluid contenedores-box-shadow banner">
            <div class="row align-items-center justify-content-between  ">

                <div class="col-lg-6 col-md-12 text-center text-white ms-5">
                    <div class="banner-content">
                        <h1 class=" text-uppercase fw-bolder">Impulsando la Innovación Tecnológica</h1>

                        <a href="#" target="_blank" class="bot btn-con fs-3  mb-5">¡CONOCENOS!</a>
                    </div>
                </div>
                <div class="col-lg-5 mt-5 mb-5">
                    <img src="https://github.com/EduardoVargasZumaeta/Tiendita_Foley/blob/main/Imagenes%20Generales/fotoHero2.png?raw=true" alt="PortadaDeLaWeb" class="img-fluid redondeo">
                </div>
            </div>
        </div>
        <section>
            <div class="container">
                <div class="row mb-3">
                    <div class="offset-lg-3 col-lg-6 text-center">
                        <h2 class="border-bottom border-4 pb-3" style="border-color:#9D79AE !important;">Sobre nosotros</h2>
                    </div>

                </div>
                <div class="row align-items-center ">
                    <div class="col-lg-5 offset-lg-2 col-md-8">
                        <h3>Bienvenido a TechCortex: Tu Destino para la Última Tecnología</h3>
                        <p>En TechCortex, ofrecemos una amplia gama de productos tecnológicos de vanguardia que transforman
                            tu experiencia diaria. Desde dispositivos innovadores hasta accesorios esenciales, estamos aquí
                            para ayudarte a encontrar las herramientas perfectas que se adapten a tu estilo de vida.</p>
                        <p>Nuestro equipo de expertos en tecnología selecciona cuidadosamente cada producto, asegurando que
                            cumpla con los más altos estándares de calidad y rendimiento. Ya sea que busques mejorar tu
                            productividad, entretenimiento o conectividad, en TechCortex encontrarás soluciones que se
                            ajustan a tus necesidades.</p>
                        <p>Colaboramos con marcas líderes en la industria para traerte lo mejor en tecnología, desde los
                            últimos smartphones hasta gadgets inteligentes y equipos de computación. Nos enorgullece
                            ofrecerte productos que no solo son innovadores, sino también accesibles y fáciles de usar.</p>
                        <p>En TechCortex, no solo vendemos tecnología; te ofrecemos una experiencia de compra excepcional.
                            Nuestro objetivo es facilitarte la vida, permitiéndote explorar las herramientas digitales que
                            impulsan tu crecimiento personal y profesional.</p>
                        <p>Visítanos en nuestra tienda o navega por nuestro catálogo en línea para descubrir cómo TechCortex
                            puede llevar tu experiencia tecnológica al siguiente nivel. ¡Tu satisfacción es nuestra
                            prioridad!</p>

                    </div>
                    <div class="col-lg-3 col-md-4 mb-3">
                        <img src="https://github.com/EduardoVargasZumaeta/Tiendita_Foley/blob/main/Imagenes%20Generales/techCortex.png?raw=true" alt="Portada" class="img-fluid redondeo">
                    </div>
                </div>
            </div>
        </section>
        <br>
        <section class="bg-light">
            <div class="container">
                <div class="row mb-3">
                    <div class="offset-lg-3 col-lg-6 text-center">
                        <h2 class="border-bottom border-4 pb-3" style="border-color:#9D79AE !important;">Preguntas Frecuentes</h2>
                    </div>
                </div>
                <div class="row align-items-center">
                    <div class="offset-lg-2 col-lg-8">

                        <div class="accordion" id="accordionExample">
                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                        1. ¿Qué tipo de productos tecnológicos ofrece TechCortex?
                                    </button>
                                </h2>
                                <div id="collapseOne" class="accordion-collapse collapse"
                                     data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        En TechCortex, ofrecemos una amplia gama de productos tecnológicos, que incluyen
                                        dispositivos inteligentes, accesorios innovadores, soluciones avanzadas de
                                        ciberseguridad, tecnología para el hogar inteligente, y herramientas de
                                        productividad tanto para uso personal como profesional. Nuestro catálogo está en
                                        constante evolución, asegurando que siempre estés a la vanguardia de la tecnología.
                                    </div>
                                </div>
                            </div>

                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        2. ¿Cómo asegura TechCortex la calidad de sus productos?
                                    </button>
                                </h2>
                                <div id="collapseTwo" class="accordion-collapse collapse"
                                     data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        En TechCortex, nos comprometemos a garantizar la calidad de nuestros productos
                                        mediante rigurosas pruebas y controles de calidad. Trabajamos exclusivamente con
                                        fabricantes y marcas reconocidas a nivel mundial. Cada producto es sometido a
                                        evaluaciones que aseguran su seguridad, rendimiento y durabilidad antes de llegar a
                                        nuestros clientes.
                                    </div>
                                </div>
                            </div>

                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        3. ¿Qué servicios de soporte ofrece TechCortex?
                                    </button>
                                </h2>
                                <div id="collapseThree" class="accordion-collapse collapse"
                                     data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        TechCortex ofrece soporte técnico personalizado para todos sus productos. Nuestro
                                        equipo especializado está disponible para resolver problemas técnicos, guiar en la
                                        instalación y configuración de dispositivos, y proporcionar mantenimiento
                                        preventivo. Además, ofrecemos recursos como tutoriales y manuales para que obtengas
                                        el mayor rendimiento de tu compra.
                                    </div>
                                </div>
                            </div>

                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                        4. ¿Ofrece TechCortex garantías en sus productos?
                                    </button>
                                </h2>
                                <div id="collapseFour" class="accordion-collapse collapse"
                                     data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        Sí, todos los productos de TechCortex vienen con una garantía de fábrica. La
                                        duración y cobertura de la garantía varía según el tipo de producto, pero
                                        garantizamos soporte completo durante el período de garantía. Además, ofrecemos
                                        opciones extendidas de garantía para aquellos que deseen una mayor cobertura.
                                    </div>
                                </div>
                            </div>

                            <div class="accordion-item">
                                <h2 class="accordion-header">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                            data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                        5. ¿Cómo puedo rastrear mi pedido en TechCortex?
                                    </button>
                                </h2>
                                <div id="collapseFive" class="accordion-collapse collapse"
                                     data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                                        Rastrear tu pedido en TechCortex es sencillo. Una vez que tu compra haya sido
                                        procesada, recibirás un correo electrónico con el número de seguimiento y un enlace
                                        directo para ver el estado de tu envío en tiempo real. Además, puedes acceder a la
                                        sección de "Mi cuenta" en nuestro sitio web para consultar el progreso de tu pedido.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <%@include file="../Vista/footer.jsp" %>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
