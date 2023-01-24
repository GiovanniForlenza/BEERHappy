package com.example.webapptest;

import entity.Utente;
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
		AddressModel am= new AddressModel();
		CardModel cm=new CardModel();
		ModelSecurity ms= new ModelSecurity();
		Utente utente;

		String mail, password;
		boolean flag = false;

		mail = request.getParameter("e-mail");
		password = request.getParameter("password");

		try {
			flag = ms.controlloAccesso(mail, password);
			System.out.println("Valore di ritorno: " + flag);
			if(flag == false)
				response.sendRedirect("http://localhost:8080/webAppTest_war/accesso.jsp");
			else {
				utente = ms.getUtente();
				request.getSession().setAttribute("accesso", true);

				utente.setIndirizzi(am.recuperoIndirizzo(utente));
				utente.setCarte(cm.recuperoCarte(utente));
				request.getSession().setAttribute("utente", utente);
				response.sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

	}
}
