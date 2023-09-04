package controller;

import model.bean.Carrello;
import model.bean.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.CartModel;
import model.dao.ProductModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AggiuntaProdottoCarrelloServlet", value = "/AggiuntaProdottoCarrelloServlet")
public class AggiuntaProdottoCarrelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        String quantity = request.getParameter("quantity");

        int quant = Integer.parseInt(quantity);

        if(carrello == null)
            carrello = new Carrello();

        String action = (String) request.getSession().getAttribute("action");
        String nome = (String) request.getSession().getAttribute("nome");
        String birrificio = (String) request.getSession().getAttribute("birrificio");


        ProductModel productModel = new ProductModel();
        boolean flag = false;
        try {
            Prodotto prodotto = productModel.doRetrieveByKey(nome,birrificio);
            prodotto.setQuantita(quant);
            if(action != null){
                String path = "http://localhost:8080/webAppTest_war/carrello.jsp";
                if(action.equals("addCart")){
                    if(carrello.getProdotti() != null){
                        for (int i = 0; i < carrello.getProdotti().size(); i++) {
                            if (carrello.getProdotti().get(i).equals(prodotto)) {
                                int sum = carrello.getProdotti().get(i).getQuantita();
                                carrello.getProdotti().get(i).setQuantita(sum + quant);
                                flag = true;
                            }
                        }

                    }
                    if(flag == false)
                        carrello.addProdotti(prodotto);

                    prodotto.aggiornaQuantita(quant);
                    CartModel cartModel = new CartModel();
                    cartModel.doUpdate(prodotto);
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
}
