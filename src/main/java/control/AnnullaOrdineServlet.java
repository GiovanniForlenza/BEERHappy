package control;

import entity.Ordine;
import entity.Stato;
import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderModel;

import java.io.IOException;

@WebServlet(name = "AnnullaOrdineServlet", value = "/AnnullaOrdineServlet")
public class AnnullaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente=(Utente) request.getSession().getAttribute("utente");
        Ordine ordine=new Ordine();
        int idOrdine=Integer.parseInt(request.getParameter("ordineID"));
        ordine.setIdOrdine(idOrdine);

        OrderModel orderModel=new OrderModel();
        orderModel.updateState(Stato.annullato.name(), ordine);
        utente.annullaOrdine(idOrdine);
        request.getSession().setAttribute("utente", utente);
        response.sendRedirect("http://localhost:8080/webAppTest_war/ordinePagina.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
