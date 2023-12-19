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
            <div class="cart-button">
                <button type="submit">Actualizar carrito</button>
            </div>
            <label for="cart-button-check">
                Mostrar carrito
            </label>
        </div>
    </form>
    <input type="checkbox" id="cart-button-check">
    ${menu}
    <div class="cart-container">
        <div class="cart">
            <label class="close-icon" for="cart-button-check">
                <img alt="close cart icon" src="assets/close-icon.svg">
            </label>
            ${carrito}
            <form class="ticket-button" action="/ticket" method="post">
                ${lista}
            </form>
        </div>
    </div>
</body>
</html>