package control;

import entity.UtenteBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelSecurity;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GestioneUtentiServlet", value = "/GestioneUtentiServlet")
public class GestioneUtentiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelSecurity mc=new ModelSecurity();
        ArrayList<UtenteBO> utenti;
        utenti=mc.recuperoUtentiBO();
        request.getSession().setAttribute("utenti", utenti);
        response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneUtenti.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
