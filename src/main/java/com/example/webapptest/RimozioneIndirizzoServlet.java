package com.example.webapptest;

import entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AddressModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RimozioneIndirizzoServlet", value = "/RimozioneIndirizzoServlet")
public class RimozioneIndirizzoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s=req.getParameter("indirizzoID");
		System.out.println(s);
		int indirizzoID;
		System.out.println(s);
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

	}
}
