package com.example.webapptest;

import entity.Indirizzo;
import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.AddressModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RimozioneIndirizzoServlet", value = "/RimozioneIndirizzoServlet")
public class RimozioneIndirizzoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String citta = req.getParameter("citta");
		String via = req.getParameter("via");

		AddressModel am = new AddressModel();
		Indirizzo indirizzo;

		try {
			indirizzo = am.doRetrieveByKey(citta, via);
			System.out.println("aooo"+indirizzo);
			Utente utente = (Utente) req.getSession().getAttribute("utente");
			utente.remove(indirizzo);

			am.doDelete(indirizzo);
			req.removeAttribute("utente");
			req.setAttribute("utente", utente);
			resp.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");


		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
