/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bd.MascotasDAO;
import bd.SolicitudesDAO;
import bd.UsuariosDAO;
import global.Peticiones;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import modelos.Mascotas;
import modelos.Peticion;
import modelos.Solicitudes;
import modelos.Usuarios;

/**
 *
 * @author Joshua Romo
 */
@WebServlet(name = "Svpeticiones", urlPatterns = {"/Svpeticiones"})
public class Svpeticiones extends HttpServlet {

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
            out.println("<title>Servlet Svpeticiones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Svpeticiones at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String tipo = (String) request.getAttribute("tipo");

        if (username == null || tipo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos de sesión o tipo faltantes");
            return;
        }

        SolicitudesDAO sdao = new SolicitudesDAO();
        MascotasDAO mdao = new MascotasDAO();
        UsuariosDAO udao = new UsuariosDAO();

        try {
            if (tipo.equals("Administrador")) {
                ArrayList<Solicitudes> solicitudesListAdmin = sdao.select();
                for (Solicitudes s : solicitudesListAdmin) {
                    Usuarios u = udao.select_user2(s.getFk_usuario());
                    Mascotas m = mdao.select_mascota(s.getFk_mascota());
                    if (u != null && m != null) {
                        Peticiones.Peticiones.add(new Peticion(s, m, u));
                    }
                    System.out.println(u.getPk_usuario());
                    System.out.println(m.getPk_mascota());
                }
            } else {
                Usuarios u = udao.select_user(username);
                if (u == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
                    return;
                }

                ArrayList<Solicitudes> solicitudesList = sdao.selectfromuser(u.getPk_usuario());
                for (Solicitudes s : solicitudesList) {
                    Mascotas m = mdao.select_mascota(s.getFk_mascota());
                    if (m != null) {
                        Peticiones.Peticiones.add(new Peticion(s, m, u));
                    }
                }
            }
            response.sendRedirect("peticiones.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error procesando datos");
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
