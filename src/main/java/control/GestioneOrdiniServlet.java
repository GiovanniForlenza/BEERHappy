package control;

import entity.Ordine;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderModel;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GestioneOrdiniServlet", value = "/GestioneOrdiniServlet")
public class GestioneOrdiniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderModel cm = new OrderModel();
        ArrayList<Ordine> ordini;
        ordini = cm.recuperoOrdini();
        request.getSession().setAttribute("ordini", ordini);
        response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneOrdini.jsp");
    }
}