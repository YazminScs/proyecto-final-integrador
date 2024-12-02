// Selección de botones de filtro
const filterButtons = document.querySelectorAll(".filter-btn");
const products = document.querySelectorAll(".product-card");

// Función para aplicar el filtro
filterButtons.forEach(button => {
    button.addEventListener("click", () => {
        // Quitar la clase 'active' de todos los botones y agregarla al actual
        filterButtons.forEach(btn => btn.classList.remove("active"));
        button.classList.add("active");

        const category = button.getAttribute("data-category");

        products.forEach(product => {
            if (category === "all" || product.getAttribute("data-category") === category) {
                product.style.display = "block"; // Mostrar producto
            } else {
                product.style.display = "none"; // Ocultar producto
            }
        });
    });
});
