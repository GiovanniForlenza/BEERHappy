package controller;

import model.bean.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.UserModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModificaDatiServlet", value = "/ModificaDatiServlet")
public class ModificaDatiServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel userModel = new UserModel();

		try {
			Utente utente = (Utente) request.getSession().getAttribute("utente");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");

			if (utente != null && nome != null && cognome != null && nome != "" && cognome != ""){
				utente = userModel.modificaDati(utente, nome, cognome);
				if(utente != null) {
					request.getSession().removeAttribute("utente");
					request.getSession().setAttribute("utente", utente);
					request.getSession().setAttribute("errorFormato", false);
				}
				else
					request.getSession().setAttribute("errorFormato", true);
			}
			response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}
