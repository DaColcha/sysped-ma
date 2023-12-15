<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket</title>
</head>
<body>
    <div>
        ${ticket}
        <form action="/factura" method="get">
            <button type="submit">Generar factura</button>
        </form>
        <a href="/">Regresar al menú</a>
    </div>
</body>
</html>
