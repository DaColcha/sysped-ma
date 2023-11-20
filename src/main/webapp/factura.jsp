<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Factura Sysped</title>
</head>
<body>
    <h1>Facturación del Pedido</h1>
    <form action="factura.jsp" method="post" >
        ${generarFactura}
        <div><button type="submit">Mostrar factura</button></div>
    </form>
    <div>
        ${factura}
    </div>
</body>
</html>
