package control;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductModel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
        String query = request.getParameter("query");
        ProductModel productModel = new ProductModel();
        try {
            ArrayList<Prodotto> prodotti = productModel.doRetrieveProducts(query);
            // Componi la risposta in formato HTML
            ArrayList<String> results = new ArrayList<>();

            for(int i = 0; i < prodotti.size(); i++) {
                results.add(prodotti.get(i).getNome());
            }

            StringBuilder sb = new StringBuilder();
            for (String result : results) {
                sb.append("<p>").append(result).append("</p>");
            }
            response.getWriter().write(sb.toString());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		*/

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
