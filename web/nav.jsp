<link rel="stylesheet" href="CSS/styles.css">
<link rel="stylesheet" href="CSS/mascotas.css">

<nav class="barra_navegacion" id="inicio" style="display: block;">
        <h1>Bienvenido a Adopciones Perrunas</h1>
        <ul id="menu-opciones">
            <%
                // Obtiene el tipo de usuario de la sesi�n
                String tipo = (String) request.getSession().getAttribute("tipo");
                if ("Administrador".equals(tipo)) {
            %>
            <li><a href="LoginServlet">Mascotas</a></li>
            <li><a href="peticiones.jsp">Peticiones</a></li>
            <li><a href="citas.jsp">Citas</a></li>
            <li><a href="SvCerrarSesion">Cerrar Sesi�n</a></li>
                <%
                } else if ("Usuario".equals(tipo)) {
                %>
            <li><a href="LoginServlet">Ver Mascotas</a></li>
            <li><a href="peticiones.jsp">Mis Peticiones</a></li>
            <li><a href="citas.jsp">Mis Citas</a></li>
<<<<<<< HEAD
            <li><a href="SvCerrarSesion">Cerrar Sesi�n</a></li>
                <%
                } else if ("Usuario".equals(tipo)) {
                %>
            <li><a href="LoginServlet">Ver Mascotas</a></li>
            <li><a href="#">Mis Peticiones</a></li>
            <li><a href="#">Mis Citas</a></li>
            <li><a href="SvCerrarSesion">Cerrar Sesi�n</a></li>

=======
            <li><a href="#">Citas</a></li>
            <li><a href="SvCerrarSesion">Cerrar Sesi�n</a></li>            
>>>>>>> 407de85d65dbc4a2893073ec610e66f2f7925ebe
                <%
                    }
                %>
            
        </ul>
    </nav>