<%-- 
    Document   : agregar_mascota
    Created on : 27 nov. 2024, 00:08:50
    Author     : Rigo y Joshua
--%>

<%@page import="modelos.Mascotas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Nueva Mascota</title>
        <jsp:include page="nav.jsp" />
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    </head>
    <body>
        <div class="contenedor">
            <h1>Agregar Nueva Mascota</h1>

            <%
                if (session == null || !"Administrador".equals(session.getAttribute("tipo"))) {
                    response.sendRedirect("LoginServlet");
                    return;
                }
                String error = (String) request.getAttribute("error");
            %>
            <% if (error != null) {%>
            <div class="error"><%= error%></div>
            <% }%>

            <form action="AgregarMascotaServlet" method="POST">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>

                <label for="edad">Edad:</label>
                <input type="number" id="edad" name="edad" required>

                <label for="sexo">Sexo:</label>
                <select id="sexo" name="sexo" required>
                    <option value="Macho">Macho</option>
                    <option value="Hembra">Hembra</option>
                </select>

                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required></textarea>

                <label for="fechaIngreso">Fecha de Ingreso:</label>
                <input type="date" id="fechaIngreso" name="fechaIngreso">

                <label for="fk_categoria">Categoría:</label>
                <select id="fk_categoria" name="fk_categoria">
                    <c:forEach var="categoria" items="${categorias}">
                        <option value="${categoria.pk_categoria}">
                            ${categoria.tipoMascota} - ${categoria.raza}
                        </option>
                    </c:forEach>
                </select>

                <button type="submit">Agregar Mascota</button>
            </form>
        </div>
    </body>
</html>
