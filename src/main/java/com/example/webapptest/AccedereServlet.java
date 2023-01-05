package com.example.webapptest;

import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccedereServlet", value = "/AccedereServlet")
public class AccedereServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelSecurity modelSecurity = new ModelSecurity();
		Utente utente;
		String mail, password;
		boolean flag = false;

		mail = request.getParameter("e-mail");
		password = request.getParameter("password");

		try {
			flag = modelSecurity.controlloAccesso(mail, password);
			System.out.println("Valore di ritorno: " + flag);
			if(flag == false)
				response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
			else {
				utente = modelSecurity.getUtente();
				request.getSession().setAttribute("utente", utente);
				response.sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

	}
}
