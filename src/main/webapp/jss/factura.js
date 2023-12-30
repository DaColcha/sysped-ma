
function validarCliente() {
    // Obtener el valor de la cédula
    var cedulaCliente = $("#cedulaCliente").val();

    // Realizar la solicitud AJAX al servlet
    $.ajax({
        type: "PUT",
        url: "/factura",
        data: JSON.stringify({ cedulaCliente: cedulaCliente }),
        success: function (data) {
            // Rellenar los campos con los datos obtenidos
            $("#nombreCliente").val(data.nombreCliente);
            $("#apellidoCliente").val(data.apellidoCliente);
            $("#telefonoCliente").val(data.telefonoCliente);
            $("#emailCliente").val(data.emailCliente);
            $("#direccionCliente").val(data.direccionCliente);
            $("#error").text(data.noEncontrado)
        },
        error: function (error) {
            alert("Error: " + error.responseText);
        }
    });
}