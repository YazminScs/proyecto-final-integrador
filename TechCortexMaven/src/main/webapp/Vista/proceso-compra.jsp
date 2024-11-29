<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proceso || TechCortex</title>
        <link href="<%=request.getContextPath()%>/Assets/CSS/proceso-compra.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="bg-body">
        <%@include file="header.jsp" %>

        <div class="container py-5">
            <h1 class="text-center text-purple mb-5 title-animation">
                Proceso de Compra
            </h1>
            <div class="row">
                <!-- Resumen de la compra -->
                <div class="col-md-6 mb-4">
                    <div class="card shadow fade-in">
                        <div class="card-header bg-purple text-white">
                            <h5>Resumen de la Compra</h5>
                        </div>
                        <div class="card-body">
                            <ul class="list-group">
                                <c:forEach var="detalle" items="${miDetalle}">
                                    <li class="list-group-item d-flex align-items-center">
                                        <img
                                            src="${detalle.producto.url_imagen}"
                                            alt="${detalle.producto.nombre}"
                                            class="img-fluid rounded me-3"
                                            style="width: 50px !important;"
                                            />
                                        <div class="d-flex flex-column w-100">
                                            <span>${detalle.producto.nombre}</span>
                                            <small class="text-muted">Cantidad: ${detalle.detalle_cant}</small>
                                        </div>
                                        <c:set var="precio_tot" value="${detalle.detalle_price * detalle.detalle_cant}" />
                                        <span>S/<fmt:formatNumber value="${precio_tot}" type="number" maxFractionDigits="2" minFractionDigits="2" /></span>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="d-flex justify-content-between mt-3 total-animation">
                                <h5>Total:</h5>
                                <h5 class="text-purple">S/${total}</h5>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Formulario de datos del usuario -->
                <div class="col-md-6">
                    <div class="card shadow slide-up">
                        <div class="card-header bg-purple text-white">
                            <h5>Información del Usuario</h5>
                        </div>
                        <div class="card-body">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <strong>Nombre:</strong> ${usuario.username}
                                </li>
                                <li class="list-group-item">
                                    <strong>Dirección:</strong> ${usuario.address}
                                </li>
                                <li class="list-group-item">
                                    <strong>Correo:</strong> ${usuario.email}
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Opciones de pago -->
            <div class="card shadow mt-4 fade-in">
                <div class="card-header bg-purple text-white">
                    <h5>Método de Pago</h5>
                </div>
                <div class="card-body">
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="metodoPago"
                            id="efectivo"
                            value="efectivo"
                            checked
                            />
                        <label class="form-check-label" for="efectivo">Efectivo</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input
                            class="form-check-input"
                            type="radio"
                            name="metodoPago"
                            id="tarjeta"
                            value="tarjeta"
                            />
                        <label class="form-check-label" for="tarjeta">Tarjeta</label>
                    </div>
                    <div id="tarjetaInfo" class="mt-3 d-none">
                        <div class="form-wrapper">
                            <form class="form" id="form">
                                <div class="form-group">
                                    <label for="input-name" class="label">Nombre en la tarjeta</label>
                                    <input
                                        type="text"
                                        class="input"
                                        id="input-name"
                                        placeholder="p.j. Jane Appleseed"
                                        required
                                        />
                                </div>
                                <div class="form-group">
                                    <label for="input-number" class="label">Numero de la tarjeta</label>
                                    <input
                                        type="text"
                                        class="input"
                                        id="input-number"
                                        placeholder="p.j. 1234 5678 9123 0000"
                                        maxlength="19"
                                        required
                                        />
                                </div>
                                <div class="form-group double">
                                    <div class="rows">
                                        <label for="input-month" class="label"
                                               >Expiracion (MM/YY)</label
                                        >
                                        <div class="columns">
                                            <input
                                                type="text"
                                                class="input"
                                                id="input-month"
                                                placeholder="MM"
                                                maxlength="2"
                                                pattern="0[1-9]|1[0-2]"
                                                title="Ingresa mes valido (01-12)"
                                                required
                                                />
                                            <input
                                                type="text"
                                                class="input"
                                                id="input-year"
                                                placeholder="YY"
                                                maxlength="2"
                                                pattern="\d{2}"
                                                title="Ingresa año valido (p.j., 24)"
                                                required
                                                />
                                        </div>
                                    </div>
                                    <div class="rows">
                                        <label for="input-cvc" class="label">CVC</label>
                                        <input
                                            type="text"
                                            class="input"
                                            id="input-cvc"
                                            placeholder="p.j. 123"
                                            maxlength="3"
                                            pattern="\d{3}"
                                            title="Ingresa 3 digitos CVC"
                                            required
                                            />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button class="button" type="submit">
                                        Confirmar tarjeta
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-center mt-4">
                <a 
                    style="text-decoration: none !important;" 
                    class="btn btn-purple btn-lg px-5 pulse-animation" 
                    href="<%=request.getContextPath()%>/ControladorCompra?accion=finalizarCompra&pago_id=" 
                    id="finalizarCompraLink">
                    Finalizar Compra
                </a>
            </div>
        </div>

        <%@include file="footer.jsp" %>

        <script>
            const tarjetaInfo = document.getElementById("tarjetaInfo");
            document.getElementById("efectivo").addEventListener("change", () => {
                tarjetaInfo.classList.add("d-none");
            });
            document.getElementById("tarjeta").addEventListener("change", () => {
                tarjetaInfo.classList.remove("d-none");
            });

            document.getElementById("input-number").addEventListener("input", (e) => {
                let value = e.target.value.replace(/\D/g, ""); // Elimina caracteres no numéricos
                value = value.substring(0, 16); // Limita a 16 dígitos
                e.target.value = value.replace(/(\d{4})(?=\d)/g, "$1 "); // Formato 4 bloques
            });

            document.getElementById("form").addEventListener("submit", (e) => {
                e.preventDefault();

                const name = document.getElementById("input-name").value.trim();
                const cardNumber = document.getElementById("input-number").value.replace(/\s/g, "");
                const month = document.getElementById("input-month").value;
                const year = document.getElementById("input-year").value;
                const cvc = document.getElementById("input-cvc").value;

                // Validaciones
                if (!name) {
                    alert("El nombre del titular de la tarjeta es obligatorio.");
                    return;
                }

                if (!/^\d{16}$/.test(cardNumber)) {
                    alert("El número de tarjeta debe tener 16 dígitos.");
                    return;
                }

                if (!/^(0[1-9]|1[0-2])$/.test(month)) {
                    alert("El mes de expiración debe ser válido (01-12).");
                    return;
                }

                if (!/^\d{2}$/.test(year)) {
                    alert("El año de expiración debe tener dos dígitos.");
                    return;
                }

                if (!/^\d{3}$/.test(cvc)) {
                    alert("El CVC debe tener 3 dígitos.");
                    return;
                }

                // Mostrar mensaje de éxito
                alert("Formulario enviado correctamente.");

                // Limpiar los campos
                document.getElementById("input-name").value = "";
                document.getElementById("input-number").value = "";
                document.getElementById("input-month").value = "";
                document.getElementById("input-year").value = "";
                document.getElementById("input-cvc").value = "";
            });


            function actualizarEnlacePago() {
                var metodoPago = document.querySelector('input[name="metodoPago"]:checked').value;
                var pagoId = (metodoPago === 'efectivo') ? 1 : 2;

                var enlace = document.getElementById('finalizarCompraLink');
                enlace.href = "<%=request.getContextPath()%>/ControladorCompra?accion=finalizarCompra&pago_id=" + pagoId;
            }

            document.addEventListener('DOMContentLoaded', function () {
                actualizarEnlacePago();
            });

            document.querySelectorAll('input[name="metodoPago"]').forEach(function (radio) {
                radio.addEventListener('change', actualizarEnlacePago);
            });
        </script>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
