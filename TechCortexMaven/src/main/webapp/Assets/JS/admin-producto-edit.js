// JavaScript para llenar el modal con los datos del producto
document.addEventListener('DOMContentLoaded', function () {
    var editarProductoModal = document.getElementById('editarProductoModal');
    editarProductoModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        var idProducto = button.getAttribute('data-id');
        var nombreProducto = button.getAttribute('data-nombre');
        var descripcionProducto = button.getAttribute('data-descripcion');
        var precioProducto = button.getAttribute('data-precio');
        var stockProducto = button.getAttribute('data-stock');
        var categoriaProducto = button.getAttribute('data-categoria');
        var marcaProducto = button.getAttribute('data-marca');
        var imagenProducto = button.getAttribute('data-imagen');

        // Asignar los valores a los campos del modal
        document.getElementById('productoId').value = idProducto;
        document.getElementById('nombreProducto').value = nombreProducto;
        document.getElementById('descripcionProducto').value = descripcionProducto;
        document.getElementById('precioProducto').value = precioProducto;
        document.getElementById('stockProducto').value = stockProducto;
        document.getElementById('categoriaProducto').value = categoriaProducto;
        document.getElementById('marcaProducto').value = marcaProducto;
        document.getElementById('imagenProducto').value = imagenProducto;
    });
});
