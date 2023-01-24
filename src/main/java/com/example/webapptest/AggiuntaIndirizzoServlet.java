package com.example.webapptest;

import entity.Indirizzo;
import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AggiuntaIndirizzoServlet", value = "/AggiuntaIndirizzoServlet")
public class AggiuntaIndirizzoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelSecurity modelSecurity = new ModelSecurity();
		Indirizzo indirizzo = new Indirizzo();

		indirizzo.setCitta(request.getParameter("citta"));
		indirizzo.setVia(request.getParameter("via"));
		//indirizzo.setCivico(request.getParameter("civico"));
		indirizzo.setCap(request.getParameter("cap"));
		indirizzo.setTelefono(request.getParameter("telefono"));


		Utente utente = (Utente) request.getSession().getAttribute("utente");

		try{
			modelSecurity.aggiuntaIndirizzo(indirizzo,utente);
			response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");

			utente.addIndirizzo(indirizzo);
			request.removeAttribute("utente");
			request.setAttribute("utente", utente);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
