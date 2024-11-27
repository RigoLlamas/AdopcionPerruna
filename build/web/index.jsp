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
<<<<<<< HEAD
    <nav class="barra_navegacion" id="inicio" style="display: none;">
        <h1>Bienvenido a Adopciones Perrunas</h1>
        <ul id="menu-opciones">
            <%
                // Obtiene el tipo de usuario de la sesión
                String tipo = (String) request.getSession().getAttribute("tipo");
=======
        <nav class="barra_navegacion" id="inicio" style="display: none;">
            <h1>Bienvenido a Adopciones Perrunas</h1>
            <ul id="menu-opciones">
                <% 
                    // Obtiene el tipo de usuario de la sesión
                    String tipo = (String) request.getSession().getAttribute("tipo");
                    if ("Administrador".equals(tipo)) {
                %>
                    <li><a href="#">Mascotas</a></li>
                    <li><a href="peticiones.jsp">Peticiones</a></li>
                    <li><a href="#">Citas</a></li>
                <% 
                    } else if ("Usuario".equals(tipo)) { 
                %>
                    <li><a href="mis_mascotas.html">Ver Mascotas</a></li>
                    <li><a href="adopciones.html">Solicitar Adopción</a></li>
                    <li><a href="perfil.html">Mi Perfil</a></li>
                <% 
                    } 
                %>
                <li><a href="logout">Cerrar Sesión</a></li>
            </ul>
        </nav>
    </head>
    <body>
>>>>>>> 3041ecee316e1c65e27bcf3f5f73682ad176552a

                if ("Administrador".equals(tipo)) {
            %>
            <li><a href="#">Mascotas</a></li>
            <li><a href="#">Peticiones</a></li>
            <li><a href="#">Citas</a></li>
                <%
                } else if ("Usuario".equals(tipo)) {
                %>
            <li><a href="index.jsp">Ver Mascotas</a></li>
            <li><a href="#">Mis Peticiones</a></li>
            <li><a href="#">Mis Citas</a></li>
                <%
                    }
                %>
            <li><a href="logout">Cerrar Sesión</a></li>
        </ul>
    </nav>
</head>
<body>

    <header>

    </header>


    <!-- Contenedor de inicio de sesión -->
    <div class="contenedor" id="login" style="display: none;">
        <h1>Iniciar Sesión</h1>
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
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

<<<<<<< HEAD
    </div>
=======
        <script>
            // Script para decidir qué sección mostrar
            const logeado = <%= Boolean.TRUE.equals(request.getSession().getAttribute("loggedIn")) ? 1 : 0 %>
>>>>>>> 3041ecee316e1c65e27bcf3f5f73682ad176552a

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
