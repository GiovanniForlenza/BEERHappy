package control;

import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelSecurity;

import java.io.IOException;

@WebServlet(name = "CambioPasswordServlet", value = "/CambioPasswordServlet")
public class CambioPasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
		String password = request.getParameter("old");
		String newPassword = request.getParameter("password");
		ModelSecurity modelSecurity = new ModelSecurity();
		try{
			if(password.equals(utente.getPassword())){
				modelSecurity.cambioPassword(utente, newPassword);
				response.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
			}
			else {
				request.getSession().setAttribute("notChack", true);
				response.sendRedirect("http://localhost:8080/webAppTest_war/cambioPassword.jsp");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
