document.addEventListener('DOMContentLoaded', function () {
    var editarCategoriaModal = document.getElementById('editarCategoriaModal');

    editarCategoriaModal.addEventListener('show.bs.modal', function (event) {
        // Obtener el botón que activó el modal
        var button = event.relatedTarget;

        // Obtener los datos del botón
        var categoriaId = button.getAttribute('data-id');
        var nombreCategoria = button.getAttribute('data-nombre');

        // Asignar los valores a los campos del modal
        document.getElementById('CategoriaId').value = categoriaId;
        document.getElementById('nombreCategoriaEditar').value = nombreCategoria;
    });
});



