<%-- 
    Document   : citas
    Created on : 26 nov. 2024, 23:36:46
    Author     : Joshua Romo
--%>

<%@page import="modelos.Cita"%>
<%@page import="global.CitasP"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/peticiones.css">
        <jsp:include page="nav.jsp" />
    </head>
    <body>
        <%
        CitasP.Citas.clear();
        String tipodeusuario = (String) session.getAttribute("tipo");
        
        if (tipodeusuario == null) { 
            response.sendRedirect("LoginServlet.jsp"); 
            return;
        } else {
            request.setAttribute("tipo", tipodeusuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("SvCitas");
            dispatcher.include(request, response);
        }
    %>
    
    <div>
        <%if(tipodeusuario.equals("Administrador")){%>
            <%for(Cita p: CitasP.Citas){%>
                <p>Numero de cita: <%= p.getNCita() %></p>
                <p>Numero de solicitud: <%= p.getNPeticion() %></p>
                <h3>Usuario: <%= p.getUser() %></h3>
                <h1>Fecha de cita: <%= p.getFechaCita()%></h1>
                <p>Estado de cita: <%= p.getEstadoCita()%></p>
                <div contenteditable="false">Notas de cita: <%= p.getNotas()%></div>
            <%}%>
        <%} else{%>
            <%for(Cita p: CitasP.Citas){%>
                <p>Numero de cita: <%= p.getNCita() %></p>
                <h1>Fecha de cita: <%= p.getFechaCita()%></h1>
                <p>Estado de cita: <%= p.getEstadoCita()%></p>
            <%}%>
        <%}%>
    </div>
    </body>
</html>
