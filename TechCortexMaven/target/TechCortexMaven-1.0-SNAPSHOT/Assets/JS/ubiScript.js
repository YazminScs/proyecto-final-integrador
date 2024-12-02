// Tiendas de ejemplo
const stores = [
    { name: "Av. los Próceres de Huandoy 4891, Lima 15109", lat: -11.97868, lng: -77.08444, district: "sjm" },
    { name: "Av. los Próceres de Huandoy", lat: -11.96018, lng: -77.07616, district: "sjm" },
    { name: "Av. Javier Prado Este 691, San Isidro", lat: -12.08737, lng: -77.03354, district: "san_isidro" },
    { name: "Av. José Larco 812, Miraflores", lat: -12.11623, lng: -77.02910, district: "miraflores" },
    { name: "Av. Abancay 123, Cercado de Lima", lat: -12.04637, lng: -77.03356, district: "cercado" },
    { name: "Av. Arenales 590, Lince", lat: -12.09055, lng: -77.03410, district: "lince" },
    { name: "Av. Brasil 1276, Pueblo Libre", lat: -12.05635, lng: -77.05190, district: "pueblo_libre" },
    { name: "Av. Tomás Marsano 187, Surquillo", lat: -12.10685, lng: -77.03528, district: "surquillo" },
    { name: "Av. Pardo 610, Miraflores", lat: -12.11515, lng: -77.03271, district: "miraflores" },
    { name: "Av. La Marina 1635, San Miguel", lat: -12.05258, lng: -77.05622, district: "san_miguel" },
    { name: "Av. San Juan 2451, San Juan de Lurigancho", lat: -11.97454, lng: -77.05658, district: "sjdl" },
    { name: "Av. Grau 2641, La Victoria", lat: -12.05270, lng: -77.04374, district: "la_victoria" },
    { name: "Av. José de la Riva Agüero 586, San Isidro", lat: -12.09337, lng: -77.03445, district: "san_isidro" },
    { name: "Av. 28 de Julio 290, Miraflores", lat: -12.11865, lng: -77.02983, district: "miraflores" },
    { name: "Av. Universitaria 1254, San Miguel", lat: -12.01388, lng: -77.04838, district: "san_miguel" }
];

// Inicializar Google Maps
function initMap() {
    const mapOptions = {
        center: { lat: -11.975, lng: -77.058 }, // Centrado en Lima
        zoom: 12,
    };
    const map = new google.maps.Map(document.getElementById("map"), mapOptions);

    // Renderizar lista de tiendas y marcadores en el mapa
    function renderStores(storeList) {
        const storeContainer = document.getElementById("storeList");
        storeContainer.innerHTML = ""; // Limpiar la lista anterior

        storeList.forEach(store => {
            // Crear contenedor para la tienda
            const storeDiv = document.createElement("div");
            storeDiv.className = "store-item d-flex justify-content-between align-items-center p-2 border-bottom"; // Clase para estilos
            storeDiv.innerHTML = `
                <span class=" fw-bold fs-5">${store.name}</span>
                <span style="cursor: pointer; font-size: 1.5em;"> <!-- Aquí es donde insertamos el SVG -->
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-arrow-right text-primary " viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8"></path>
        </svg>
    </span> <!-- Flecha -->
            `;

            // Evento al hacer clic en el contenedor de la tienda
            storeDiv.addEventListener("click", () => {
                map.setCenter({ lat: store.lat, lng: store.lng });
                map.setZoom(15);
            });

            // Agregar el contenedor al contenedor principal
            storeContainer.appendChild(storeDiv);

            // Crear marcador en el mapa
            const marker = new google.maps.Marker({
                position: { lat: store.lat, lng: store.lng },
                map: map,
                title: store.name,
            });

            // Evento al hacer clic en el marcador
            marker.addListener("click", () => {
                alert(`Has seleccionado: ${store.name}`);
            });
        });
    }


    // Inicialmente, mostrar todas las tiendas
    renderStores(stores);

    // Filtrar tiendas por distrito
    document.getElementById("districtSelector").addEventListener("change", function () {
        const selectedDistrict = this.value;
        const filteredStores = stores.filter(store => store.district === selectedDistrict);
        map.setCenter({ lat: -11.975, lng: -77.058 }); // Volver a centrar el mapa
        map.setZoom(12); // Ajustar el zoom

        // Renderizar las tiendas filtradas
        renderStores(filteredStores);
    });
}

// Cargar mapa al inicio
window.onload = initMap;

// Población dinámica de distritos
document.getElementById("citySelector").addEventListener("change", function () {
    const districtSelector = document.getElementById("districtSelector");
    districtSelector.innerHTML = ""; // Limpiar distritos previos

    if (this.value === "lima") {
        districtSelector.innerHTML = `
            <option value='sjm'>San Martin de Porres</option>
            <option value='san_isidro'>San Isidro</option>
            <option value='miraflores'>Miraflores</option>
            <option value='cercado'>Cercado de Lima</option>
            <option value='lince'>Lince</option>
            <option value='pueblo_libre'>Pueblo Libre</option>
            <option value='surquillo'>Surquillo</option>
            <option value='san_miguel'>San Miguel</option>
            <option value='sjdl'>San Juan de Lurigancho</option>
            <option value='la_victoria'>La Victoria</option>
        `;
    } else {
        districtSelector.innerHTML = "<option value=''>Selecciona un distrito</option>";
    }
});
