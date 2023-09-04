package controller;

import model.bean.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EliminazioneServlet", value = "/EliminazioneServlet")
public class EliminazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel mc=new UserModel();
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        try {
            if (utente != null){
                mc.removeUser(utente);
                HttpSession session = request.getSession();
                session.invalidate();
            }
            response.sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
