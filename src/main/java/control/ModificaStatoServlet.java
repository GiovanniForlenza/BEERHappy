package control;

import entity.Ordine;
import entity.Stato;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderModel;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModificaStatoServlet", value = "/ModificaStatoServlet")
public class ModificaStatoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stato=request.getParameter("newState");
        Ordine ordine=(Ordine)request.getSession().getAttribute("ordine");
        System.out.println(""+stato);
        OrderModel om=new OrderModel();
        om.updateState(stato, ordine);
        ArrayList<Ordine> ordini=(ArrayList<Ordine>) request.getSession().getAttribute("ordini");
        ordine.setStato(Stato.valueOf(stato));
        ordini=Ordine.remove(ordini, ordine);
        ordini.add(ordine);
        request.getSession().removeAttribute("ordini");
        request.getSession().setAttribute("ordini", ordini);
        response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneOrdini.jsp");

    }
}