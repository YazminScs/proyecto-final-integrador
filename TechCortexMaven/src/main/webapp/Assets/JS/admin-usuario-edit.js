document.addEventListener("DOMContentLoaded", function () {
    const editarModal = document.getElementById("editarModal");
    const eliminarModal = document.getElementById("eliminarModal");
    
    editarModal.addEventListener("show.bs.modal", function (event) {
        const button = event.relatedTarget;

        // Obtener los datos del usuario del botón
        const id = button.getAttribute("data-id");
        const username = button.getAttribute("data-username");
        const email = button.getAttribute("data-email");
        const address = button.getAttribute("data-address");
        const phone = button.getAttribute("data-phone");

        // Llenar los campos del modal
        editarModal.querySelector("#editar-id").value = id;
        editarModal.querySelector("#editar-username").value = username;
        editarModal.querySelector("#editar-email").value = email;
        editarModal.querySelector("#editar-address").value = address;
        editarModal.querySelector("#editar-phone").value = phone;
    });
    
    eliminarModal.addEventListener("show.bs.modal", function (event) {
        const button = event.relatedTarget;
        const id = button.getAttribute("data-id");
        editarModal.querySelector("#eliminar-id").value = id;
    });
});

