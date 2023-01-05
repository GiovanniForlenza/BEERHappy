package com.example.webapptest;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RichiestaBirreServlet", value = "/RichiestaBirreServlet")
public class RichiestaBirreServlet extends HttpServlet {
	static ModelSecurity model = new ModelSecurity();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String redirectedPage = "/homePage.jsp";

		try {
			request.removeAttribute("birre");

			request.setAttribute("birre", model.doRetrieveAll());

		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}

		//response.sendRedirect(redirectedPage);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
