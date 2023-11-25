<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Factura Sysped</title>
    <style>

    </style>
</head>
<body>
    <h1>Facturación del Pedido</h1>
    <form action="/factura" method="post" >
        ${generarFactura}
        <div><button type="submit">Mostrar factura</button></div>
    </form>
    <div>
        ${mostrarFactura}
    </div>
</body>
</html>
