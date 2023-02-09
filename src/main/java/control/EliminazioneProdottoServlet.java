package control;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductModel;

import java.io.IOException;
import java.security.Permission;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EliminazioneProdottoServlet", value = "/EliminazioneProdottoServlet")
public class EliminazioneProdottoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto prodotto = (Prodotto) request.getSession().getAttribute("oldProduct");

        if(prodotto != null){
            try{
				ProductModel productModel = new ProductModel();
				productModel.doDelete(prodotto);
				ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
				prodotti = Prodotto.remove(prodotti, prodotto);
				request.getSession().removeAttribute("prodotti");
				request.getSession().setAttribute("prodotti", prodotti);
				response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
			}catch (SQLException e){
				e.printStackTrace();
			}
        }else{
			response.sendRedirect("http://localhost:8080/webAppTest_war/modificaProdotto.jsp");
		}

	}
}
