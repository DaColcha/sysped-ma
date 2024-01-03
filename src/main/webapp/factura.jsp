<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Factura Sysped</title>
    <style><%@include file="styles/factura.css"%></style>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script> <%@include file="jss/factura.js"%></script>

</head>
<body>

    <script>
    function mostrarTipoClienteFacturacion(opcionSeleccionada) {
        var contenido = document.getElementById("contenido");

        if (opcionSeleccionada === "cliente_general") {
            contenido.innerHTML = "" +
                "<form action=\"/factura\" method=\"post\">" +
                "<div>" +
                "<label for = \"cedulaCliente\">Cédula: </label>" +
                "<input type=\"text\" name=\"cedulaCliente\" id=\"cedulaCliente\" value=\"9999999999\">" +
                "<div class=\"validate-btn\" onclick=\"validarCliente()\">Validar</div>" +
                "<div id=\"error\">${error}</div>" +
                "</div>  <div>" +
                "<label for = \"nombreCliente\">Nombre: </label>" +
                "<input type=\"text\" name=\"nombreCliente\" id=\"nombreCliente\" value=\"${nombreCliente}\">" +
                "</div>  <div>" +
                "<label for = \"apellidoCliente\">Apellido: </label>" +
                "<input type=\"text\" name=\"apellidoCliente\" id=\"apellidoCliente\" value=\"${apellidoCliente}\" >" +
                "</div>  <div>" +
                "<label for = \"telefonoCliente\">Teléfono: </label>" +
                "<input type=\"text\" name=\"telefonoCliente\" id=\"telefonoCliente\" value=\"${telefonoCliente}\" >" +
                "</div>  <div>" +
                "<label for = \"emailCliente\">Correo Eléctronico: </label>" +
                "<input type=\"email\" name=\"emailCliente\" id=\"emailCliente\" value=\"${emailCliente}\" >" +
                "</div>  <div>" +
                "<label for = \"direccionCliente\">Dirección: </label>" +
                "<input type=\"text\" name=\"direccionCliente\" id=\"direccionCliente\" value=\"${direccionCliente}\">" +
                "</div>  <div>" +
                "<label for = \"codigoPedido\">Código del ticket del pedido: </label>" +
                "<input type=\"text\" name=\"codigoPedido\" id=\"codigoPedido\" value=\"${codigoPedido}\"> </div>" +
                "<button class=\"generate-btn\" type=\"submit\" >Generar factura</button> </form>";

        } else if (opcionSeleccionada === "consumidor_final") {
            contenido.innerHTML = "" +
                "<form action=\"/factura\" method=\"post\">" +
                "<div>" +
                "<label for = \"cedulaCliente\">Cédula: </label>" +
                "<input type=\"text\" name=\"cedulaCliente\" id=\"cedulaCliente\" value=\"9999999999\">" +
                "<div class=\"validate-btn\" onclick=\"validarCliente()\">Validar</div>" +
                "<div id=\"error\">${error}</div>" +
                "</div>  <div>" +
                "<label for = \"nombreCliente\">Nombre: </label>" +
                "<input type=\"text\" name=\"nombreCliente\" id=\"nombreCliente\" value=\"Consumidor\">" +
                "</div>  <div>" +
                "<label for = \"apellidoCliente\">Apellido: </label>" +
                "<input type=\"text\" name=\"apellidoCliente\" id=\"apellidoCliente\" value=\"Final\" >" +
                "</div>  <div>" +
                "<label for = \"telefonoCliente\">Teléfono: </label>" +
                "<input type=\"text\" name=\"telefonoCliente\" id=\"telefonoCliente\" value=\"0000000000\" >" +
                "</div>  <div>" +
                "<label for = \"emailCliente\">Correo Eléctronico: </label>" +
                "<input type=\"text\" name=\"emailCliente\" id=\"emailCliente\" value=\"*****\" >" +
                "</div>  <div>" +
                "<label for = \"direccionCliente\">Dirección: </label>" +
                "<input type=\"text\" name=\"direccionCliente\" id=\"direccionCliente\" value=\"S/D\">" +
                "</div>  <div>" +
                "<label for = \"codigoPedido\">Código del ticket del pedido: </label>" +
                "<input type=\"text\" name=\"codigoPedido\" id=\"codigoPedido\" value=\"${codigoPedido}\"> </div>" +
                "<button class=\"generate-btn\" type=\"submit\" >Generar factura</button> </form>";

        } else {
            contenido.innerHTML = "<p>No seleccionaste ninguna opción.</p>";
        }
    }
    </script>

    <h1>Facturación del Pedido</h1>
    <container>
        <div class="registro-cliente">
            <h2>Registro del cliente</h2>
            <label for="opcionSi">Cobrar con Tipo de Cliente General:</label>
            <input type="radio" name="opcion" value="cliente_general" id="opcionSi" onchange="mostrarTipoClienteFacturacion(this.value)">

            <label for="opcionNo">Cobrar con Consumidor Final:</label>
            <input type="radio" name="opcion" value="consumidor_final" id="opcionNo" onchange="mostrarTipoClienteFacturacion(this.value)">
        </div>
        <div class="registro-cliente" id="contenido">
            <!-- Contenido dinámico aparecerá aquí -->
        </div>
        <div class="partes-factura">
            ${mostrarFactura}
            ${mostrarDetalleFactura}
            <form action="/pago" method="post">
                ${mostrarMetodoPago}
                <button class="generate-btn" type="submit" >Cobrar Pedido</button>
            </form>
            ${notificacionPago}
        </div>

    </container>

</body>
</html>
