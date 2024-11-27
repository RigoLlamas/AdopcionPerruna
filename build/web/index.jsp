<%-- 
    Document   : index
    Created on : 24 nov. 2024, 20:40:15
    Author     : Rigo y Joshua
--%>

<%@page import="modelos.Mascotas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Adopción Perruna</title>
        <link rel="stylesheet" href="CSS/styles.css">
        <link rel="stylesheet" href="CSS/mascotas.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <jsp:include page="nav.jsp" />
        <%String tipo = (String) request.getSession().getAttribute("tipo");%>
</head>
<body>
<!--ei-->
    <header>

    </header>


    <!-- Contenedor de inicio de sesión -->
    <div class="contenedor" id="login" style="display: none;">
        <h1>Iniciar Sesión</h1>
        <%
            String logout = request.getParameter("logout");
            if ("true".equals(logout)) {
        %>
            <script>
                Swal.fire({
                    title: '¡Sesión cerrada!',
                    text: 'Tu sesión se ha cerrado correctamente.',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            </script>
        <%
            }
        %>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null && logout == null) {
        %>
        <script>
                Swal.fire({
                    title: 'Ups..',
                    text: 'hubo un error 😣',
                    icon: 'error',
                    confirmButtonText: 'Aceptar'
                });
        </script>
        <div class="error-message"><%= errorMessage%></div>
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
        <h2>Mascotas disponibles para adopción</h2>
        <div class="tarjetas">
            <%
                ArrayList<Mascotas> listaMascotas = (ArrayList<Mascotas>) request.getAttribute("listaMascotas");

                if (listaMascotas != null && !listaMascotas.isEmpty()) {
                    for (Mascotas mascota : listaMascotas) {
                        if ("Usuario".equals(tipo)) {
            %>
            <script>
                Swal.fire({
                    title: '¡Bienvenido usuario!',
                    text: 'Se ha logueado correctamente 😎',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            </script>
            <form action="solicitud_adopcion.jsp" method="get" class="tarjeta-form">
                <input type="hidden" name="Mascota" value="<%= mascota.getPk_mascota()%>">
                <button type="submit" class="tarjeta-boton">
                    <div class="tarjeta">
                        <img src="<%= (mascota.getImagen() != null && !mascota.getImagen().isEmpty())
                                ? mascota.getImagen()
                                : "IMAGENES/default.png"%>" 
                             alt="<%= mascota.getNombre()%>" 
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
            <form action="ModificarMascotaServlet" method="GET" class="tarjeta-form">
                <input type="hidden" name="idMascota" value="<%= mascota.getPk_mascota()%>">
                <button type="submit" class="tarjeta-boton">
                    <div class="tarjeta">
                        <img src="<%= (mascota.getImagen() != null && !mascota.getImagen().isEmpty())
                                ? mascota.getImagen()
                                : "IMAGENES/default.png"%>" 
                             alt="<%= mascota.getNombre()%>" 
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
                    }
                }
            } else {
            %>
            <p>No hay mascotas disponibles en este momento.</p>

            <%
                }
            %>
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
