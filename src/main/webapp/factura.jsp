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
    <h1>Facturación del Pedido</h1>
    <form action="/factura" method="post" onsubmit="ocultarForm()">
        <div>
            <label for = "cedulaCliente">Cédula: </label>
             <input type="text" name="cedulaCliente" id="cedulaCliente" value="${cedulaCliente}">
            <div class="validate-btn" onclick="validarCliente()">Validar</div>
            <div id="error">${error}</div>
        </div>
        <div>
            <label for = "nombreCliente">Nombre: </label>
            <input type="text" name="nombreCliente" id="nombreCliente" value="${nombreCliente}">
        </div>

         <div>
             <label for = "apellidoCliente">Apellido: </label>
             <input type="text" name="apellidoCliente" id="apellidoCliente" value="${apellidoCliente}" >
         </div>

         <div>
             <label for = "telefonoCliente">Teléfono: </label>
            <input type="text" name="telefonoCliente" id="telefonoCliente" value="${telefonoCliente}" >
         </div>

         <div>
             <label for = "emailCliente">Correo Eléctronico: </label>
            <input type="text" name="emailCliente" id="emailCliente" value="${emailCliente}" >
         </div>

         <div>
             <label for = "direccionCliente">Dirección: </label>
             <input type="text" name="direccionCliente" id="direccionCliente" value="${direccionCliente}">
         </div>

         <div>
            <label for = "codigoPedido">Código del ticket del pedido: </label>
            <input type="text" name="codigoPedido" id="codigoPedido" >
         </div>

         <div><button type="submit" >Generar factura</button></div>

    </form>
    <div>
        <div class="partes-factura">
            ${mostrarFactura}
        </div>
        <div class="partes-factura">
            ${mostrarDetalleFactura}
        </div>
    </div>
</body>
</html>
