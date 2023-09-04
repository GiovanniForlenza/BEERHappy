package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.ProductModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RichiestaBirreServlet", value = "/RichiestaBirreServlet")
public class RichiestaBirreServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String redirectedPage = "/catalogo.jsp";

		try {
			ProductModel productModel = new ProductModel();
			request.removeAttribute("birre");
			request.setAttribute("birre", productModel.doRetrieveAll());

			Boolean home = (Boolean) request.getSession().getAttribute("home");
			Boolean homestore = (Boolean) request.getSession().getAttribute("homestore");

			if(home != null) {
				redirectedPage = "/homePage.jsp";
				request.getSession().removeAttribute("home");
			} else if (homestore != null) {
				redirectedPage = "/homePageStore.jsp";
				request.getSession().removeAttribute("homestore");
			}


		} catch(SQLException e) {
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
