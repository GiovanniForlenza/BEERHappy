package controller;

import model.bean.UtenteBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EliminazioneUtenteBOServlet", value = "/EliminazioneUtenteBOServlet")
public class EliminazioneUtenteBOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteBO utenteBO=(UtenteBO) request.getSession().getAttribute("utente");
        UserModel mc=new UserModel();
        ArrayList<UtenteBO> utenti=(ArrayList<UtenteBO>)request.getSession().getAttribute("utenti");

        try {
            if(utenteBO != null) {
                mc.deleteUtenteBO(utenteBO);
                utenti = UtenteBO.remove(utenti, utenteBO);
                request.getSession().removeAttribute("utenti");
                request.getSession().setAttribute("utenti", utenti);
                response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneUtenti.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}