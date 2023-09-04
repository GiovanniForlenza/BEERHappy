package controller;

import model.bean.Ordine;
import model.bean.Stato;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ModificaStatoServlet", value = "/ModificaStatoServlet")
public class ModificaStatoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stato=request.getParameter("newState");
        Ordine ordine=(Ordine)request.getSession().getAttribute("ordine");
        OrderModel om=new OrderModel();
        try {
            om.updateState(stato, ordine);
            ArrayList<Ordine> ordini=(ArrayList<Ordine>) request.getSession().getAttribute("ordini");
            ordine.setStato(Stato.valueOf(stato));
            ordini=Ordine.remove(ordini, ordine);
            ordini.add(ordine);
            request.getSession().removeAttribute("ordini");
            request.getSession().setAttribute("ordini", ordini);
            response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneOrdini.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}