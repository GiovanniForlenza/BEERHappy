package controller;

import model.bean.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.UserModel;

import java.io.IOException;

@WebServlet(name = "CambioPasswordServlet", value = "/CambioPasswordServlet")
public class CambioPasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		String password = request.getParameter("old");
		String newPassword = request.getParameter("password");
		UserModel userModel = new UserModel();

		try{
			if(password.equals(utente.getPassword())){
				request.getSession().setAttribute("notChack", false);

				if(userModel.cambioPassword(utente, newPassword)) {
					request.getSession().setAttribute("errorFormato", false);
					response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
				}
				else {
					request.getSession().setAttribute("errorFormato", true);
					response.sendRedirect("http://localhost:8080/webAppTest_war/cambioPassword.jsp");
				}
			}
			else {
				request.getSession().setAttribute("notChack", true);
				request.getSession().setAttribute("errorFormato", true);
				response.sendRedirect("http://localhost:8080/webAppTest_war/cambioPassword.jsp");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
