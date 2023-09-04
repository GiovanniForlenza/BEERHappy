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

@WebServlet(name = "GestioneUtentiServlet", value = "/GestioneUtentiServlet")
public class GestioneUtentiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserModel mc = new UserModel();
        try {
            ArrayList<UtenteBO> utenti = mc.recuperoUtentiBO();
            request.getSession().setAttribute("utenti", utenti);
            response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneUtenti.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
