<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SysPed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
            background: #333;
            height: 100vh;
            margin: 0;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); /* Se ajusta automáticamente y tiene un ancho mínimo de 200px */
            max-width: 98%;

        }

        .producto {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: center;
            min-width: 200px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #333;
        }

        img {
            max-height: 130px;
            min-width: 150px;
            height: auto;
            border-radius: 4px;
        }
        .dato{
            justify-content: center;
            text-align: center;
        }
        .contenedor {
            display: flex;
            justify-content: center; /* Centra horizontalmente */
            padding: 20px;
        }
        .ticket {
            background-color: #222;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            max-width: 100%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #333;
            color: white;
        }
        .ticket-header {
            text-align: center;
            font-size: 24px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<form action="/" method="post" >
    ${menu}
    <div class="contenedor"><button type="submit">Realizar tictek</button></div>
</form>
<div>${ticket}</div>

</body>
</html>