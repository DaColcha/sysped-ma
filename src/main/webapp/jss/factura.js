$(document).ready(function () {

    $(document).on('click', ".Completar", function () {
        var cedula = $('input[name="cedulaCliente"]').text();
        console.log(cedula);

        $.ajax({
            url: '/factura',
            type: 'PUT',
            data: { cedulaCliente: cedula },
            success: function (response) {
                window.location.href = '/factura';
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    });
});