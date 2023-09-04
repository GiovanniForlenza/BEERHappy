package controller;

import model.bean.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.ProductModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String searchTerm = request.getParameter("searchTerm");
		ProductModel productModel = new ProductModel();
		try {

			ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");

			if(prodotti == null) {
				prodotti = productModel.doRetrieveAll();
				request.getSession().setAttribute("prodotti", prodotti);
			}

			StringBuilder sb = new StringBuilder();
			for (Prodotto product : prodotti) {
				String href = "DettagliProdottoServlet?nome=" + product.getNome() + "&birrificio=" + product.getBirrificio() + "&formato=" + product.getFormato();
				if (product.getNome().toLowerCase().startsWith(searchTerm.toLowerCase())) {
					sb.append("<a href='" + href + "'>").append("<p>").append(product.getNome() + " " + product.getBirrificio() + " " + product.getFormato()).append("</p>").append("</a>");
				}else if (product.getBirrificio().toLowerCase().startsWith(searchTerm.toLowerCase())) {
					sb.append("<a href='" + href + "'>").append("<p>").append(product.getNome() + " " + product.getBirrificio() + " " + product.getFormato()).append("</p>").append("</a>");
				}
			}

			response.getWriter().println(sb.toString());
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
}
