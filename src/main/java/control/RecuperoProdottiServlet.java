package control;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecuperoProdottiServlet", value = "/RecuperoProdottiServlet")
public class RecuperoProdottiServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductModel productModel = new ProductModel();
		try {
			ArrayList<Prodotto> prodotti = productModel.doRetrieveAll();
			request.getSession().setAttribute("prodotti", prodotti);
			response.sendRedirect("http://localhost:8080/webAppTest_war/homePage.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
