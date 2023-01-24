package com.example.webapptest;

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


		String redirectedPage = "/catalogo.jsp";

		try {
			request.removeAttribute("birre");
			request.setAttribute("birre", model.doRetrieveAll());

			Boolean flag = (Boolean) request.getSession().getAttribute("accesso");
			if(flag != null){
				redirectedPage = "/catalogo.jsp";
			}
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
