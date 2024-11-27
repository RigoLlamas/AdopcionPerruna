<%@page import="modelos.Peticion"%>
<%@page import="global.Peticiones"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Peticiones</title>
    <link rel="stylesheet" href="CSS/peticiones.css">
    <jsp:include page="nav.jsp" />
</head>
<body>
    <%
        Peticiones.Peticiones.clear();
        String tipodeusuario = (String) session.getAttribute("tipo");
        
        if (tipodeusuario == null) { 
            response.sendRedirect("index.jsp"); 
            return;
        } else {
            request.setAttribute("tipo", tipodeusuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Svpeticiones");
            dispatcher.include(request, response);
        }
    %>
    
    <div>
        <%for(Peticion p: Peticiones.Peticiones){%>
            <p>Numero de solicitud: <%= p.getNSolicitud() %></p>
            <h3>Usuario: <%= p.getUsuario() %></h3>
            <h1>Mascota: <%= p.getMascota() %></h1>
            <p>Fecha de solicitud: <%= p.getFechaSolicitud() %></p>
            <h3>Estado de solicitud: <%= p.getEstado() %></h3>
            <div contenteditable="false">Comentarios: <%= p.getComentarios() %></div>
        <%}%>
    </div>
</body>
</html>
