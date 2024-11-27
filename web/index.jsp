<%-- Document : index Created on : 24 nov. 2024, 20:40:15 Author : Rigo y Joshua --%>

<%@page import="modelos.Categorias" %>
<%@page import="modelos.Mascotas" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Adopción Perruna</title>
        <link rel="stylesheet" href="CSS/styles.css">
        <link rel="stylesheet" href="CSS/mascotas.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <jsp:include page="nav.jsp" />
        <%String tipo = (String) request.getSession().getAttribute("tipo");%>
    </head>

    <body>
        <!-- Contenedor de inicio de sesión -->
        <div class="contenedor" id="login" style="display: none;">
            <h1>Iniciar Sesión</h1>
            <% String logout = request.getParameter("logout");
                                if ("true".equals(logout)) { %>
            <script>
                Swal.fire({
                    title: '¡Sesión cerrada!',
                    text: 'Tu sesión se ha cerrado correctamente.',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            </script>
            <% } %>

            <% String errorMessage = (String) request.getAttribute("errorMessage");
                                        if (errorMessage != null && logout == null) {%>

            <script>
                Swal.fire({
                    title: 'Ups..',
                    text: 'hubo un error 😣',
                    icon: 'error',
                    confirmButtonText: 'Aceptar'
                });
            </script>
<<<<<<< HEAD
            
            <form action="solicitud.jsp">
                <input type="hidden" name="Mascota" value="<%= mascota.getPk_mascota()%>">
                <button type="submit" class="tarjeta-boton">
                    <div class="tarjeta">
                        <img src="<%= (mascota.getImagen() != null && !mascota.getImagen().isEmpty())
                                ? mascota.getImagen()
                                : "IMAGENES/default.png"%>" 
                             alt="<%= mascota.getNombre()%>" 
=======
            <div class="error-message">
                <%= errorMessage%>
            </div>
            <% } %>
            <form action="LoginServlet" method="POST">
                <label for="email">Correo Electrónico:</label><br>
                <input type="email" name="email" id="email" required><br>
                <label for="contrasena">Contraseña:</label><br>
                <input type="password" name="contrasena" id="contrasena" required><br>
                <button type="submit">Iniciar Sesión</button>
            </form>
        </div>

        <div id="lista" style="display: none;">
            <div style="display: flex; justify-content: space-between; margin: 1rem;">
                <h2>Mascotas disponibles para adopción</h2>
                <div style="display: flex;">
                    <form action="LoginServlet" method="get">
                        <div>
                            <label for="categoria">Filtrar por categoría:</label>
                        <select id="categoria" name="categoria">
                            <option value="">Todas las categorías</option>
                            <c:forEach var="categoria" items="${listaCategorias}">
                                <option value="${categoria.pk_categoria}">
                                    ${categoria.tipoMascota} - ${categoria.raza}
                                </option>
                            </c:forEach>
                        </select>
                        <button type="submit">Filtrar</button>
                        </div>
                        
                        
                    </form>
                </div>

            </div>

            <div class="tarjetas">
                <%if ("Administrador".equals(tipo)) { %>

                <form action="AgregarMascotaServlet" method="GET">
                    <button type="submit" class="tarjeta-boton">
                        <div class="tarjeta agregar-mascota">
                            <img src="IMAGENES/default.png" alt="Agregar Mascota"
                                 class="imagen-mascota">
                            <div class="contenido-tarjeta">
                                <h3>Agregar Nueva Mascota</h3>
                                <p>Click aquí para agregar una nueva mascota al sistema.</p>
                            </div>
                        </div>
                    </button>
                </form>

                <% } %>



                <% ArrayList<Mascotas> listaMascotas = (ArrayList<Mascotas>) request.getAttribute("listaMascotas");

                    if (listaMascotas != null && !listaMascotas.isEmpty()) {
                        for (Mascotas mascota : listaMascotas) {
                            if ("Usuario".equals(tipo)) {
                %>
                <script>
                    Swal.fire({
                        title: '¡Bienvenido usuario!',
                        text: '¿Cómo estas hoy? 😎',
                        icon: 'success',
                        confirmButtonText: 'Aceptar'
                    });
                </script>

                <form action="solicitud_adopcion.jsp" method="get">
                    <input type="hidden" name="Mascota"
                           value="<%= mascota.getPk_mascota()%>">
                    <button type="submit" class="tarjeta-boton">
                        <div class="tarjeta">
                             <img src="<%= (mascota.getImagen() != null && !mascota.getImagen().isEmpty())
                                                                    ? mascota.getImagen()
                                                                    : " IMAGENES/default.png"%>"
                             alt="<%= mascota.getNombre()%>"
>>>>>>> 407de85d65dbc4a2893073ec610e66f2f7925ebe
                             class="imagen-mascota">

                        <div class="contenido-tarjeta">
                            <h3><%= mascota.getNombre()%></h3>
                            <p><strong>Edad:</strong> <%= mascota.getEdad()%> años</p>
                            <p><strong>Sexo:</strong> <%= mascota.getSexo()%></p>
                            <p><strong>Descripción:</strong> <%= mascota.getDescripcion()%></p>
                            <p><strong>Estado:</strong> <%= mascota.getEstado()%></p>
                        </div>
                    </div>
                </button>
            </form>
            <%
            } else if ("Administrador".equals(tipo)) {
            %>
            <script>
                Swal.fire({
                    title: '¡Bienvenido Administrador!',
                    text: 'Se ha logueado correctamente 😎',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            </script>
            <form action="ModificarMascotaServlet" method="GET">
                <input type="hidden" name="idMascota" value="<%= mascota.getPk_mascota()%>">
                <button type="submit" class="tarjeta-boton">
                    <div class="tarjeta">
                        <img src="<%= (mascota.getImagen() != null && !mascota.getImagen().isEmpty())
                                ? mascota.getImagen()
                                : "IMAGENES/default.png"%>" 
                             alt="<%= mascota.getNombre()%>" 
                             class="imagen-mascota">

                            <div class="contenido-tarjeta">
                                <h3>
                                    <%= mascota.getNombre()%>
                                </h3>
                                <p><strong>Edad:</strong>
                                    <%= mascota.getEdad()%> años
                                </p>
                                <p><strong>Sexo:</strong>
                                    <%= mascota.getSexo()%>
                                </p>
                                <p><strong>Descripción:</strong>
                                    <%= mascota.getDescripcion()%>
                                </p>
                                <p><strong>Estado:</strong>
                                    <%= mascota.getEstado()%>
                                </p>
                            </div>
                        </div>
                    </button>
                </form>
                <% }
                                                        }
                                                    } else { %>
                <p>No hay mascotas disponibles en este momento.</p>

                <% }%>
            </div>

        </div>

        <script>
            // Script para decidir qué sección mostrar
            const logeado = <%= Boolean.TRUE.equals(request.getSession().getAttribute("loggedIn")) ? 1 : 0%>;

            console.log("Estado del usuario: " + logeado);

            if (logeado === 1) {
                document.getElementById("inicio").style.display = "block";
                document.getElementById("lista").style.display = "block";
                console.log("Mostrando el menú del usuario");
            } else {
                document.getElementById("login").style.display = "block";
                console.log("Mostrando el formulario de inicio de sesión");
            }
        </script>

        <footer>
            <p>&copy; 2024 Adopción Perruna - Todos los derechos reservados.</p>
        </footer>
    </body>

</html>