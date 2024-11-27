<%-- 
    Document   : modificar_mascota
    Created on : 26 nov. 2024, 18:18:45
    Author     : Rigo
--%>

<%@page import="modelos.Mascotas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modificar Mascota</title>
        <!-- Vincular el archivo CSS -->
        <link rel="stylesheet" href="CSS/styles.css">
        <link rel="stylesheet" href="CSS/mascotas.css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    </head>
    <body>
        <!-- Contenedor principal -->
        <div class="contenedor">
            <!-- Título del formulario -->
            <h1>Modificar Información de Mascota</h1>

            <%
                Mascotas mascota = (Mascotas) request.getAttribute("mascota");
                String error = (String) request.getAttribute("error");

                if (error != null) {
            %>
            <!-- Mensaje de error -->
            <div class="error"><%= error%></div>
            <% }%>

            <!-- Formulario de modificación -->
            <form action="ModificarMascotaServlet" method="POST">
                <input type="hidden" name="idMascota" value="<%= mascota.getPk_mascota()%>">

                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%= mascota.getNombre()%>" required>

                <label for="edad">Edad:</label>
                <input type="number" id="edad" name="edad" value="<%= mascota.getEdad()%>" required>

                <label for="sexo">Sexo:</label>
                <select id="sexo" name="sexo" required>
                    <option value="Macho" <%= "Macho".equals(mascota.getSexo()) ? "selected" : ""%>>Macho</option>
                    <option value="Hembra" <%= "Hembra".equals(mascota.getSexo()) ? "selected" : ""%>>Hembra</option>
                </select>

                <label for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion" required><%= mascota.getDescripcion()%></textarea>

                <label for="fechaIngreso">Fecha de Ingreso:</label>
                <input type="date" id="fechaIngreso" name="fechaIngreso" value="<%= mascota.getFechaIngreso() != null ? mascota.getFechaIngreso().toString() : ""%>" required>

                <label for="fk_categoria">Categoría:</label>
                <select id="fk_categoria" name="fk_categoria">
                    <c:forEach var="categoria" items="${categorias}">
                        <option value="${categoria.pk_categoria}">
                            ${categoria.tipoMascota} - ${categoria.raza}
                        </option>
                    </c:forEach>
                </select>

                <label for="estado">Estado:</label>
                <select id="estado" name="estado" required>
                    <option value="Disponible" <%= "Disponible".equals(mascota.getEstado()) ? "selected" : ""%>>Disponible</option>
                    <option value="Adoptado" <%= "Adoptado".equals(mascota.getEstado()) ? "selected" : ""%>>Adoptado</option>
                </select>

                <button type="submit">Guardar Cambios</button>
            </form>
            <form action="EliminarMascotaServlet" method="POST">
                <input type="hidden" name="idMascota" value="<%= mascota.getPk_mascota()%>">
                <button type="submit" style="margin-top: 1rem">Eliminar Mascota</button>
            </form>
        </div>
    </body>
</html>
