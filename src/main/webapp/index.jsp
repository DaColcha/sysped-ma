<%@ page import="com.example.syspedv1.CarritoServlet" %>
<%@ page import="com.example.syspedv1.CarritoDeProductos" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SysPed</title>
    <style><%@include file="styles/index.css"%></style>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script> <%@include file="jss/script.js"%></script>
</head>
<body>
<form action="/carrito" method="get">
        <div class="header">
            <h1>MENÚ</h1>
                <div class="ticket-button">
                    <button type="submit">Mostrar Carrito</button>
                </div>
        </div>
</form>

        ${menu}
        ${carrito}

<form action="/ticket" method="post">
        <div class="ticket-button">
            ${lista}
        </div>
</form>
</body>
</html>