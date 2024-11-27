<%-- 
    Document   : solicitud
    Created on : 27 nov. 2024, 02:02:40
    Author     : Joshua Romo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realizar Solicitud</title>
        <link rel="stylesheet" href="CSS/peticion.css">
    </head>
    <body>
        <% 
            // Obtener el parámetro "Mascota" si está presente
            String mascota = request.getParameter("Mascota"); 
        %>
        <form action="SvSolicitud" method="POST">
            <label for="comentario">Dale todo tu cariño con un comentario para realizar tu solicitud</label>
            <input type="text" id="comentario" name="comentario" required>
            <br><br>

            <!-- Incluir el valor de "Mascota" como un campo oculto si existe -->
            <% if (mascota != null && !mascota.isEmpty()) { %>
                <input type="hidden" name="mascota" value="<%= mascota %>">
            <% } %>

            <button type="submit">Enviar</button>
        </form>
    </body>
</html>

