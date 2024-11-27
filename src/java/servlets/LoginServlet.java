/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bd.CategoriasDAO;
import bd.MascotasDAO;
import bd.UsuariosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import modelos.Categorias;
import modelos.Mascotas;

/**
 *
 * @author Rigo y Joshua
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión actual
        HttpSession session = request.getSession(false);

        if (session != null && Boolean.TRUE.equals(session.getAttribute("loggedIn"))) {
            MascotasDAO mascotaDAO = new MascotasDAO();
            ArrayList<Mascotas> listaMascotas;

            // Obtener el parámetro de categoría seleccionado
            String categoriaSeleccionada = request.getParameter("categoria");

            if (categoriaSeleccionada != null && !categoriaSeleccionada.isEmpty()) {
                // Filtrar las mascotas por categoría
                int categoriaId = Integer.parseInt(categoriaSeleccionada);
                listaMascotas = mascotaDAO.selectPorCategoria(categoriaId);
            } else {
                // Obtener todas las mascotas
                listaMascotas = mascotaDAO.select();
            }

            request.setAttribute("listaMascotas", listaMascotas);

            // Obtener las categorías
            CategoriasDAO categoriaDAO = new CategoriasDAO();
            ArrayList<Categorias> listaCategorias = categoriaDAO.select();
            request.setAttribute("listaCategorias", listaCategorias);

            // Pasar la categoría seleccionada a la JSP
            request.setAttribute("categoriaSeleccionada", categoriaSeleccionada);

            // Reenviar a index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            // Si no hay sesión o no está logueado, redirigir al login
            response.sendRedirect("LoginServlet");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        UsuariosDAO usuarioDAO = new UsuariosDAO();
        HttpSession session = request.getSession();        

        try {
            String tipoUsuario = usuarioDAO.validarUsuario(email, contrasena);
            if (tipoUsuario != null) {
                session.setAttribute("loggedIn", true);
                session.setAttribute("username", email);
                session.setAttribute("tipo", tipoUsuario);
                response.sendRedirect("LoginServlet");
            } else {
                request.setAttribute("errorMessage", "Credenciales incorrectas");
                session.setAttribute("loggedIn", false);
                request.getRequestDispatcher("LoginServlet").forward(request, response);
            }
        } catch (Exception e) {
            session.setAttribute("loggedIn", false);
            request.setAttribute("errorMessage", "Error al validar el usuario. Intente de nuevo.");
            request.getRequestDispatcher("LoginServlet").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
