package control;

import entity.Carta;
import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CardModel;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AggiuntaCartaServlet", value = "/AggiuntaCartaServlet")
public class AggiuntaCartaServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Carta carta = new Carta();

            carta.setIntestatario(request.getParameter("titolare"));
            carta.setDataScadenza(request.getParameter("dataScadenza"));
            carta.setnCata(request.getParameter("numero"));

            Utente utente = (Utente) request.getSession().getAttribute("utente");
            String address = request.getParameter("address");

            if(address != null)
                System.out.println(address);

            try{
                CardModel cm = new CardModel();
                carta = cm.aggiuntaCarta(carta,utente);
                utente.addCarta(carta);
                request.removeAttribute("utente");
                request.setAttribute("utente", utente);

                Boolean selectCard = (Boolean) request.getSession().getAttribute("selectCard");
                if(selectCard != null && selectCard) {
                    request.getSession().setAttribute("selectCard", false);
                    request.getSession().removeAttribute("selectCard");
                    response.sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
                }
                else
                    response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");


            }catch(SQLException e){
                e.printStackTrace();
            }
        }

}
