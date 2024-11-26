
// Mostrar notificación
function showNotification(message) {
    const notification = document.getElementById('notification');
    notification.textContent = message;
    notification.classList.add('visible');

    setTimeout(() => {
        notification.classList.remove('visible');
    }, 3000);
}

// Agregar al carrito
function addToCart() {
    showNotification('¡Producto agregado al carrito!');
}