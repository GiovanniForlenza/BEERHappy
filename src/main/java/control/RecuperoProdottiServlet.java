package control;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.DriverManagerConnectionPool;
import model.ProductModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecuperoProdottiServlet", value = "/RecuperoProdottiServlet")
public class RecuperoProdottiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("searchTerm");
		ArrayList<Prodotto> products = searchProducts(searchTerm);
		request.setAttribute("products", products);
		request.getRequestDispatcher("results.jsp").forward(request, response);
	}

	private ArrayList<Prodotto> searchProducts(String searchTerm) {
		ArrayList<Prodotto> products = new ArrayList<>();
		try  {
			ProductModel productModel = new ProductModel();
			products = productModel.doRetrieveProducts(searchTerm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		ProductModel productModel = new ProductModel();
		try {
			ArrayList<Prodotto> prodotti = productModel.doRetrieveAll();
			request.getSession().setAttribute("prodotti", prodotti);
			response.sendRedirect("http://localhost:8080/webAppTest_war/searchBar.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
*/
	}
}
