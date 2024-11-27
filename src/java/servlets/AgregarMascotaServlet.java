/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bd.CategoriasDAO;
import bd.MascotasDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import modelos.Categorias;
import modelos.Mascotas;

/**
 *
 * @author Rigo
 */
@WebServlet(name = "AgregarMascotaServlet", urlPatterns = {"/AgregarMascotaServlet"})
public class AgregarMascotaServlet extends HttpServlet {

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
            out.println("<title>Servlet AgregarMascotaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarMascotaServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession(false);
        if (session == null || !"Administrador".equals(session.getAttribute("tipo"))) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Obtener las categorías
        CategoriasDAO categoriaDAO = new CategoriasDAO();
        ArrayList<Categorias> categorias = categoriaDAO.select();

        // Pasar las categorías a la JSP
        request.setAttribute("categorias", categorias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("agregar_mascota.jsp");
        dispatcher.forward(request, response);
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

        HttpSession session = request.getSession(false);
        if (session == null || !"Administrador".equals(session.getAttribute("tipo"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Obtener y validar los parámetros del formulario
            String nombre = request.getParameter("nombre");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String sexo = request.getParameter("sexo");
            String descripcion = request.getParameter("descripcion");
            String estado = "Disponible";
            int fk_categoria = Integer.parseInt(request.getParameter("fk_categoria"));

            // Fecha de ingreso
            String fechaIngresoStr = request.getParameter("fechaIngreso");
            LocalDate fechaIngreso = null;
            if (fechaIngresoStr != null && !fechaIngresoStr.isEmpty()) {
                fechaIngreso = LocalDate.parse(fechaIngresoStr);
            }

            // Crear objeto Mascotas con los datos ingresados
            Mascotas mascota = new Mascotas();
            mascota.setNombre(nombre);
            mascota.setEdad(edad);
            mascota.setSexo(sexo);
            mascota.setDescripcion(descripcion);
            mascota.setEstado(estado);
            mascota.setFk_categoria(fk_categoria);
            mascota.setFechaIngreso(fechaIngreso);
            mascota.setImagen("IMAGENES/default.png"); // Establecer imagen por defecto

            MascotasDAO mascotaDAO = new MascotasDAO();
            boolean agregada = mascotaDAO.insert(mascota);

            if (agregada) {
                // Redirigir a la página principal con un mensaje de éxito
                response.sendRedirect("LoginServlet");
            } else {
                // Manejar el error y reenviar al formulario con los datos actuales
                request.setAttribute("error", "No se pudo agregar la mascota.");
                request.getRequestDispatcher("agregar_mascota.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.getRequestDispatcher("agregar_mascota.jsp").forward(request, response);
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
