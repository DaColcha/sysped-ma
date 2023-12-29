<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script> <%@include file="jss/script.js"%></script>
</head>
<body>
    <div>
        ${ticket}
        <form action="/factura" method="get">
            <button type="submit">Generar factura</button>
        </form>
        <button class="delete-all-button">Regresar</button>
    </div>
</body>
</html>
