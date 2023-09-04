package controller;

import model.bean.Ordine;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GestioneOrdiniServlet", value = "/GestioneOrdiniServlet")
public class GestioneOrdiniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderModel cm = new OrderModel();
        try {
            ArrayList<Ordine> ordini = cm.recuperoOrdini();
            request.getSession().setAttribute("ordini", ordini);
            response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneOrdini.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}