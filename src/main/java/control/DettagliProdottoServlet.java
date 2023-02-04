package control;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DettagliProdottoServlet", value = "/DettagliProdottoServlet")
public class DettagliProdottoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome, birrificio, formato;
		nome = request.getParameter("nome");
		birrificio = request.getParameter("birrificio");
		formato = request.getParameter("formato");

		ProductModel productModel = new ProductModel();

		try {
			Prodotto prodotto;
			prodotto = productModel.doRetrieveByKey(nome,birrificio,formato);
			request.getSession().setAttribute("prodotto", prodotto);
			response.sendRedirect("http://localhost:8080/webAppTest_war/prodottoPage.jsp");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
