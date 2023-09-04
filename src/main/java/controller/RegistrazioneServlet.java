package controller;

import model.bean.Carrello;
import model.bean.Carta;
import model.bean.Indirizzo;
import model.bean.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.UserModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "RegistrazioneServlet", value = "/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserModel userModel = new UserModel();
		Utente utente = new Utente();
		boolean flag = false;

		utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setEmail(request.getParameter("e-mail"));
        utente.setPassword(request.getParameter("password"));

		boolean controlloformato = userModel.controlloFormato(utente);

		try {
			flag = userModel.controlloEmailRegistrazione(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(flag && controlloformato) {
			try {
				userModel.addUser(utente);
				ArrayList<Indirizzo> indirizzi = new ArrayList<>();
				ArrayList<Carta> carte = new ArrayList<>();

				utente.setIndirizzi(indirizzi);
				utente.setCarte(carte);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.getSession().setAttribute("utente", utente);
			request.getSession().setAttribute("accessoUtente",true);

			Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

			if(carrello != null){
				if(carrello.getProdotti().size() > 0){
					response.sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
				}
			}else {
				response.sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
			}
		}
		else {
			request.getSession().setAttribute("error_mail", true);
			response.sendRedirect("http://localhost:8080/webAppTest_war/signup.jsp");
		}
	}
}
