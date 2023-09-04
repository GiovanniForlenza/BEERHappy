package controller;
import model.bean.Carrello;
import model.bean.Utente;
import model.bean.UtenteBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.*;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "AccedereServlet", value = "/AccedereServlet")
public class AccedereServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressModel am = new AddressModel();
		CardModel cm = new CardModel();
		UserModel ms = new UserModel();
		OrderModel orderModel = new OrderModel();
		CartModel cartModel = new CartModel();
		Utente utente;
		UtenteBO utenteBO;
		String email, password;


		email = request.getParameter("e-mail");
		password = request.getParameter("password");


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
				Carrello carrelloSessione = (Carrello) request.getSession().getAttribute("carrello");

				Carrello carrello = cartModel.recuperoCarrello(utente, carrelloSessione);
				String redirect = "http://localhost:8080/webAppTest_war/homePageStore.jsp";
				if(carrello != null) {
					utente.setCarrello(carrello);
					request.getSession().setAttribute("carrello",carrello);

					if(carrello.getProdotti() != null)
						if(carrello.getProdotti().size() > 0)
							redirect = "http://localhost:8080/webAppTest_war/carrello.jsp";
				}

				request.getSession().setAttribute("utente", utente);
				request.getSession().setAttribute("accessoUtenteBO", false);
				request.getSession().setAttribute("accessoUtente", true);
				response.sendRedirect(redirect);

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
