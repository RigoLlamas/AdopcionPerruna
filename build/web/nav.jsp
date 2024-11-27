<link rel="stylesheet" href="CSS/styles.css">
<link rel="stylesheet" href="CSS/mascotas.css">

<nav class="barra_navegacion" id="inicio" style="display: none;">
        <h1>Bienvenido a Adopciones Perrunas</h1>
        <ul id="menu-opciones">
            <%
                // Obtiene el tipo de usuario de la sesi�n
                String tipo = (String) request.getSession().getAttribute("tipo");

                if ("Administrador".equals(tipo)) {
            %>
            <li><a href="#">Mascotas</a></li>
            <li><a href="peticiones.jsp">Peticiones</a></li>
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
            <li><a href="logout">Cerrar Sesi�n</a></li>
        </ul>
    </nav>