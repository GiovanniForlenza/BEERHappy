package controller;

import model.bean.Carrello;
import model.bean.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.CartModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CartModel cartModel = new CartModel();
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null){
			try {
				cartModel.svuotaCarrello(utente);
				Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
				if(carrello != null && carrello.getProdotti() != null) {
					int i = 0;
					while (i < carrello.getProdotti().size()) {
						cartModel.salvaCarrello(utente.getEmail(), carrello.getProdotti().get(i));
						i++;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		HttpSession session = request.getSession();
		session.invalidate();

		response.sendRedirect("http://localhost:8080/webAppTest_war/homePage.jsp");

	}


}
