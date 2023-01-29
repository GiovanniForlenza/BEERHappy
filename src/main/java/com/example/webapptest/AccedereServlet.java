package com.example.webapptest;

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

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccedereServlet", value = "/AccedereServlet")
public class AccedereServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressModel am = new AddressModel();
		CardModel cm = new CardModel();
		ModelSecurity ms = new ModelSecurity();
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
				utente.setIndirizzi(am.recuperoIndirizzo(utente));
				utente.setCarte(cm.recuperoCarte(utente));
				request.getSession().setAttribute("utente", utente);
				utente.setIndirizzi(am.recuperoIndirizzo(utente));
				utente.setCarte(cm.recuperoCarte(utente));
				request.getSession().setAttribute("utente", utente);
				request.getSession().setAttribute("accessoUtenteBO", false);
				request.getSession().setAttribute("accessoUtente", true);
				response.sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");

			} else {
				request.getSession().setAttribute("accessoUtenteBO", false);
				request.getSession().setAttribute("accessoUtente", false);
				response.sendRedirect("http://localhost:8080/webAppTest_war/accesso.jsp");
			}
		} catch (SQLException e) {

		}
	}

}
