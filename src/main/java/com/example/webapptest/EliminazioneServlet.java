package com.example.webapptest;

import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EliminazioneServlet", value = "/EliminazioneServlet")
public class EliminazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ModelSecurity modelSecurity = new ModelSecurity();
        Utente utente;
        utente = (Utente) req.getSession().getAttribute("utente");
        req.removeAttribute("utente");
        req.setAttribute("accesso",false);

        try{
            modelSecurity.removeUser(utente);
            resp.sendRedirect("http://localhost:8080/webAppTest_war/accesso.jsp");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
