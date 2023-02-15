package control;
import entity.Carrello;
import entity.Prodotto;
import entity.Utente;
import entity.UtenteBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AddressModel;
import model.CardModel;
import model.ModelSecurity;
import model.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AccedereServlet", value = "/AccedereServlet")
public class AccedereServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressModel am = new AddressModel();
		CardModel cm = new CardModel();
		ModelSecurity ms = new ModelSecurity();
		OrderModel orderModel = new OrderModel();
		Utente utente;
		UtenteBO utenteBO;
		String email, password;


		email = request.getParameter("e-mail");
		password = request.getParameter("password");
		System.out.println("email: "+email+" password: "+password);

		try {
			if ((utenteBO = ms.loginAmministratore(email, password)) != null) {
				request.getSession().setAttribute("utenteBO", utenteBO);
				request.getSession().setAttribute("accessoUtenteBO", true);
				request.getSession().setAttribute("accessoUtente", false);
				response.sendRedirect("http://localhost:8080/webAppTest_war/selezioneRuolo.jsp");
			} else if ((utente = ms.loginUtente(email, password)) != null) {
				utente.setOrdini(orderModel.recuperoOrdini(utente));
				utente.setIndirizzi(am.recuperoIndirizzo(utente));
				utente.setCarte(cm.recuperoCarte(utente));

				request.getSession().setAttribute("utente", utente);
				request.getSession().setAttribute("accessoUtenteBO", false);
				request.getSession().setAttribute("accessoUtente", true);

				Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

				if(carrello != null){
					if(carrello.getProdotti().size() > 0){
						response.sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
					}
				}else {
					response.sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
				}
			} else {
				request.getSession().setAttribute("accessoUtenteBO", false);
				request.getSession().setAttribute("accessoUtente", false);
				request.getSession().setAttribute("email_password", true);
				response.sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
