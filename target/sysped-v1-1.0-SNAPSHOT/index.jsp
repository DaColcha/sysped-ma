<%@ page import="com.example.syspedv1.CarritoServlet" %>
<%@ page import="com.example.syspedv1.CarritoDeCompras" %>
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
    <div class="header">
        <h1>MENÚ</h1>
        <label id= "Mostrar" for="cart-button-check">
            <button class="invisible-button">Mostrar carrito</button>
        </label>
    </div>
    <input type="checkbox" id="cart-button-check">
    ${menu}
    <div class="cart-container">
        <div class="cart">
            <label class="close-icon" for="cart-button-check">
                <img alt="close cart icon" src="assets/close-icon.svg">
            </label>
            <img class="refresh-icon Actualizar" alt="refresh cart icon" src="assets/refresh-icon.svg">
            ${carrito}
            <form class="ticket-button" action="/ticket" method="post">
                ${lista}
            </form>
        </div>
    </div>
</body>
</html>