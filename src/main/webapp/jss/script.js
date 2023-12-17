$(document).ready(function () {
    // Manejar el clic en el botón "Agregar al Carrito"
    $(document).on('click', '.product .agregar-carrito', function () {
        console.log('Clic en el botón "Agregar al Carrito"');
        var producto = $(this).closest('.product');
        var id = producto.data('id');
        var imagen = producto.data('img');
        var descripcion = producto.data('des');
        var precio = producto.data('precio');
        var cantidad = parseInt(producto.find('.product-amount').val()) || 1;
        var detalle = producto.find('.product-detalle').val() || "";

        // Crear objeto con la información del producto
        var productoData = {
            id: id,
            imagen: imagen,
            descripcion: descripcion,
            precio: precio,
            cantidad: cantidad,
            detalle: detalle
        };

        // Enviar datos al servlet mediante AJAX
        $.ajax({
            type: 'POST',
            url: '/carrito', // Reemplaza con la URL correcta
            contentType: 'application/json',
            data: JSON.stringify(productoData),
            success: function (response) {
                producto.find('.product-amount').val('');
                producto.find('.product-detalle').val('');
            },
            error: function (error) {
                console.error('Error en la solicitud AJAX: ', error);
            }
        });
    });
    $(document).on('click', '.delete-button', function () {
        console.log('Botón clickeado:');
        var productId = $(this).attr('data-id');
        var productoData = {
            id: productId,
        };
        $.ajax({
            url: "/carrito",
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(productoData),
            success: function(response) {
                console.log('Producto eliminado con éxito:', response);
                window.location.href = '/carrito';
            },
            error: function(error) {
                console.error('Error al eliminar el producto:', error);
            }
        });

    });

    $(document).on('click', '.delete-all-button', function () {
        var productoData = {
            id: "TODO",
        };
        $.ajax({
            url: "/carrito",
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(productoData),
            success: function(response) {
                window.location.href = '/carrito';
            },
            error: function(error) {
                console.error('Error al eliminar el producto:', error);
            }
        });

    });

    $(document).ready(function () {
        // Agrega un evento de escucha para el evento 'input' en las celdas editables
        $('.numero-celda').on('input', function () {
            // Obtén el valor actual de la celda
            var valor = $(this).text();

            // Valida si el valor está dentro del rango deseado (por ejemplo, entre 1 y 100)
            if (isNaN(valor) || valor <1) {
                // Si no está en el rango, establece un valor predeterminado (o puedes mostrar un mensaje de error)
                $(this).text("1"); // Establecer el valor predeterminado en 1
            }
        });
    });

    $(document).on('click', '.Actualizar', function () {
        var filas = $('#miTabla tbody tr:not(:first,:last)');

        // Crear un array para almacenar los datos de cada fila
        var datos = [];
        console.log("Número de filas:", filas.length);

        // Iterar sobre cada fila
        filas.each(function () {
            var id = $(this).find('.id').text();
            var cantidad = $(this).find('.cantidad').text();
            var detalle = $(this).find('.detalle').text();
            console.log("id:", id, "cantidad:", cantidad, "detalle:", detalle);

            // Agregar los datos de la fila al array
            datos.push({
                id: id,
                cantidad: cantidad,
                detalle: detalle
            });
        });
        $.ajax({
            url: "/carrito",
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(datos),
            success: function(response) {
                window.location.href = '/carrito';
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });

    });
});