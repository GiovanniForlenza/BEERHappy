package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RecuperoPasswordServlet", value = "/RecuperoPasswordServlet")
public class RecuperoPasswordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		UserModel userModel = new UserModel();

		try {
			String password = userModel.recuperoPassword(email);
			String successJson = "";

			if(password != null){
				successJson = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
			}
			else {
				request.getSession().setAttribute("errormessage", true);
			}
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(successJson);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
