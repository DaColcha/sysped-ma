$(document).ready(function () {
    // Manejar el clic en el botón "Agregar al Carrito"
    $(document).on('click', '.product .agregar-carrito', function () {
        const producto = $(this).closest('.product');
        let amount = parseInt(producto.find('.product-amount').val());
        if ((amount < 1) || (isNaN(amount))) {
            console.log('Minus one')
            alert('Ingrese una cantidad correcta para el producto seleccionado')
        } else {
            console.log('Clic en el botón "Agregar al Carrito"');
            const id = producto.data('id');
            const imagen = producto.data('img');
            const descripcion = producto.data('des');
            const precio = producto.data('precio');
            const cantidad = amount;
            const detalle = producto.find('.product-detalle').val() || "";

            // Crear objeto con la información del producto
            const productoData = {
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
        }
    });
    $(document).on('click', '.delete-button', function () {
        console.log('Botón clickeado:');
        const productId = $(this).attr('data-id');
        const productoData = {
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
        const productoData = {
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

    // $(document).on('click', '.Actualizar', function () {
    //     const filas = $('#miTabla tbody tr:not(:first,:last)');
    //
    //     // Crear un array para almacenar los datos de cada fila
    //     let datos = [];
    //     console.log("Número de filas:", filas.length);
    //
    //     // Iterar sobre cada fila
    //     filas.each(function () {
    //         let id = $(this).find('.id').text();
    //         let cantidad = $(this).find('.cantidad').text();
    //         let detalle = $(this).find('.detalle').text();
    //         console.log("id:", id, "cantidad:", cantidad, "detalle:", detalle);
    //
    //         // Agregar los datos de la fila al array
    //         datos.push({
    //             id: id,
    //             cantidad: cantidad,
    //             detalle: detalle
    //         });
    //     });
    //     $.ajax({
    //         url: "/carrito",
    //         type: 'PUT',
    //         contentType: 'application/json',
    //         data: JSON.stringify(datos),
    //         success: function(response) {
    //             window.location.href = '/carrito';
    //         },
    //         error: function(error) {
    //             console.error('Error:', error);
    //         }
    //     });
    //
    // });
});