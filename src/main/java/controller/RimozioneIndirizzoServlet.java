package controller;

import model.bean.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.AddressModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RimozioneIndirizzoServlet", value = "/RimozioneIndirizzoServlet")
public class RimozioneIndirizzoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s=req.getParameter("indirizzoID");

		int indirizzoID;
		indirizzoID=Integer.parseInt(s);
		AddressModel am=new AddressModel();
		try {
			am.doDelete(indirizzoID);
		}catch (SQLException e){
			e.printStackTrace();
		}
		Utente utente=(Utente)req.getSession().getAttribute("utente");
		utente.removeIndirizzo(indirizzoID);
		req.removeAttribute("utente");
		req.setAttribute("utente", utente);

		resp.sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
