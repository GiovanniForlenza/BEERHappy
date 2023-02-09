package com.example.webapptest;

import entity.Indirizzo;
import entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.AddressModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SelezionaIndirizzoServlet", value = "/SelezionaIndirizzoServlet")
public class SelezionaIndirizzoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			AddressModel addressModel = new AddressModel();
			Integer idAddress = Integer.parseInt(request.getParameter("selezionato"));
			if(idAddress != null){
				Indirizzo indirizzo = addressModel.doRetrieveByKey(idAddress);
				request.getSession().setAttribute("visualizza", indirizzo);
				response.sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
