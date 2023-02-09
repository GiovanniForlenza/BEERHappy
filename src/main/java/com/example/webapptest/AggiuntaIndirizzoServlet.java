package com.example.webapptest;

import entity.Indirizzo;
import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AddressModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AggiuntaIndirizzoServlet", value = "/AggiuntaIndirizzoServlet")
public class AggiuntaIndirizzoServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Indirizzo indirizzo = new Indirizzo();
		indirizzo.setCitta(request.getParameter("citta"));
		indirizzo.setVia(request.getParameter("via"));
		indirizzo.setCivico(Integer.parseInt(request.getParameter("civico")));
		indirizzo.setCap(request.getParameter("cap"));
		indirizzo.setTelefono(request.getParameter("telefono"));
		Utente utente = (Utente) request.getSession().getAttribute("utente");

		try{
			AddressModel addressModel = new AddressModel();
			indirizzo = addressModel.aggiuntaIndirizzo(indirizzo, utente);
			utente.addIndirizzo(indirizzo);
			request.removeAttribute("utente");
			request.setAttribute("utente", utente);
			Boolean selectAddress = (Boolean) request.getSession().getAttribute("selectAddress");
			if(selectAddress != null && selectAddress) {
				request.getSession().setAttribute("selectAddress", false);
				request.getSession().removeAttribute("selectAddress");
				response.sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
			}
			else
				response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
