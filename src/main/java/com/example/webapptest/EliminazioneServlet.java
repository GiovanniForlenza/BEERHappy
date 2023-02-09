package com.example.webapptest;

import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EliminazioneServlet", value = "/EliminazioneServlet")
public class EliminazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s=request.getParameter("email");
        System.out.println(s);
        ModelSecurity mc=new ModelSecurity();
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        try {
            mc.removeUser(utente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("accesso", false);
        System.out.println("Ci arrivo");
        response.sendRedirect("http://localhost:8080/webAppTest_war/accesso.jsp");



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
