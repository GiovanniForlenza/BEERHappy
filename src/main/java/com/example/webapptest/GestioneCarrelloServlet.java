package com.example.webapptest;

import entity.Carrello;
import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CartModel;
import model.ProductModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GestioneCarrelloServlet", value = "/GestioneCarrelloServlet")
public class GestioneCarrelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

		if(carrello == null)
			carrello = new Carrello();

		String action = request.getParameter("action");
		String nome = request.getParameter("nome");
		String birrificio = request.getParameter("birrificio");
		String formato = request.getParameter("formato");

		ProductModel productModel = new ProductModel();

		try {
			Prodotto prodotto = productModel.doRetrieveByKey(nome,birrificio,formato);
			if(action != null){
				String path = "http://localhost:8080/webAppTest_war/carrello.jsp";
				if (action.equals("rimuoviProdotto")) {
					int quantita = Integer.parseInt(request.getParameter("quantita"));
					prodotto.aggiungiQuantita(quantita);
					prodotto.setQuantita(0);
					CartModel cartModel = new CartModel();
					cartModel.doUpdate(prodotto);
					carrello.removeProdotti(prodotto);
				}else if (action.equals("checkout")){
					Boolean accesso = (Boolean) request.getSession().getAttribute("accessoUtente");
					if(accesso == null || !accesso.booleanValue()){
						path = "http://localhost:8080/webAppTest_war/accesso.jsp";
					}
					else if(accesso){
						path = "http://localhost:8080/webAppTest_war/effettuaOrdine.jsp";
					}
				}

				request.getSession().removeAttribute("carrello");
				request.getSession().removeAttribute("prodotto");
				request.getSession().setAttribute("carrello", carrello);
				request.getSession().setAttribute("prodotto", prodotto);
				response.sendRedirect(path);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
