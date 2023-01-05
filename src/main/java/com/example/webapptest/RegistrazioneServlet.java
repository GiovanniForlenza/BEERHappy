package com.example.webapptest;

import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "RegistrazioneServlet", value = "/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ModelSecurity modelSecurity = new ModelSecurity();
		Utente utente = new Utente();
		boolean flag = false;

		utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setEmail(request.getParameter("e-mail"));
        utente.setPassword(request.getParameter("password"));

		try {
			flag = modelSecurity.controlloEmailRegistrazione(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}

			if(flag) {
				try {
					modelSecurity.addUser(utente);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("utente", utente);
				response.sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
			}
			else {
				System.out.println("Error email already present");
				response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
			}

	}
}
