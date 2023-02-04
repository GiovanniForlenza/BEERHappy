package control;

import entity.Carta;
import entity.Indirizzo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.AddressModel;
import model.CardModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ScelezionaCartaServlet", value = "/ScelezionaCartaServlet")
public class ScelezionaCartaServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            CardModel addressModel = new CardModel();
            Integer idCard = Integer.parseInt(request.getParameter("selezionato"));
            if(idCard != null){
                Carta indirizzo = addressModel.doRetrieveByKey(idCard);
                request.getSession().setAttribute("cardView", indirizzo);
                response.sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
	}
}
