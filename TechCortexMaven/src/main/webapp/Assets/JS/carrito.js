// Definición de productos de ejemplo con imagen
const productos = [
    { nombre: "Producto 1", precio: 20.00, imagen: "https://via.placeholder.com/50" },
    { nombre: "Producto 2", precio: 35.00, imagen: "https://via.placeholder.com/50" },
    { nombre: "Producto 3", precio: 15.00, imagen: "https://via.placeholder.com/50" },
];

// Inicializar el carrito vacío
let carrito = [];

// Función para agregar un producto al carrito
function agregarAlCarrito(producto) {
    const productoExistente = carrito.find(item => item.nombre === producto.nombre);
    if (productoExistente) {
        productoExistente.cantidad++;
    } else {
        producto.cantidad = 1;
        carrito.push(producto);
    }
    actualizarCarrito();
}

// Función para eliminar un producto del carrito
function eliminarProducto(index) {
    carrito.splice(index, 1);
    actualizarCarrito();
}

// Función para cambiar la cantidad de un producto
function cambiarCantidad(index, cantidad) {
    if (cantidad <= 0) {
        carrito.splice(index, 1);
    } else {
        carrito[index].cantidad = cantidad;
    }
    actualizarCarrito();
}

// Función para actualizar el carrito en la interfaz
function actualizarCarrito() {
    const listaCarrito = document.getElementById("lista-carrito");
    const totalElem = document.getElementById("total");
    listaCarrito.innerHTML = "";

    let total = 0;
    carrito.forEach((producto, index) => {
        const li = document.createElement("li");
        li.classList.add("list-group-item");
        li.innerHTML = `
            <div class="producto-info">
                <img src="${producto.imagen}" alt="${producto.nombre}">
                <span>${producto.nombre}</span>
            </div>
            <div class="cantidad-control">
                <button onclick="cambiarCantidad(${index}, ${producto.cantidad - 1})">-</button>
                <span>${producto.cantidad}</span>
                <button onclick="cambiarCantidad(${index}, ${producto.cantidad + 1})">+</button>
            </div>
            <span>$${(producto.precio * producto.cantidad).toFixed(2)}</span>
            <button class="btn-remove" onclick="eliminarProducto(${index})">Eliminar</button>
        `;
        listaCarrito.appendChild(li);
        total += producto.precio * producto.cantidad;
    });

    totalElem.textContent = total.toFixed(2);
}

// Función para simular la compra
document.getElementById("finalizar-compra").addEventListener("click", () => {
    if (carrito.length > 0) {
        alert("Compra finalizada. ¡Gracias por su compra!");
        carrito = [];
        actualizarCarrito();
    } else {
        alert("El carrito está vacío.");
    }
});

// Agregar productos de ejemplo al carrito (esto puede hacerse dinámicamente desde botones)
agregarAlCarrito(productos[0]);
agregarAlCarrito(productos[1]);
agregarAlCarrito(productos[2]);
