<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SysPed</title>
    <style><%@include file="styles/index.css"%></style>
</head>
<body>
    <form action="/" method="post" >
        <div class="header">
            <h1>MENÚ</h1>
            <div class="ticket-button">
                <button type="submit">Realizar ticket</button>
            </div>
        </div>
        ${menu}
    </form>
</body>
</html>