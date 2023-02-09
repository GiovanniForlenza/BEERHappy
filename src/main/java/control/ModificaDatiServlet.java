package control;

import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelSecurity;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModificaDatiServlet", value = "/ModificaDatiServlet")
public class ModificaDatiServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelSecurity modelSecurity = null;

		try {
			Utente utente = (Utente) request.getSession().getAttribute("utente");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");

			modelSecurity.modificaDati(utente, nome, cognome);
			response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}
