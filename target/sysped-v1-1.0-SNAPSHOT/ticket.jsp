<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script> <%@include file="jss/script.js"%></script>
    <style><%@include file="styles/ticket.css"%></style>
</head>
<body>
    <div class="ticket">
        ${ticket}
        <form action="/factura" method="get">
            <button class="generate-btn" type="submit">Generar factura</button>
        </form>
        <button class="go-back-btn">Regresar</button>
    </div>
</body>
</html>
