package controller;

import model.bean.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.CatalogoModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ModificaProdottoServlet", value = "/ModificaProdottoServlet")
public class ModificaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto oldProduct = (Prodotto) request.getSession().getAttribute("oldProduct");
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setBirrificio(request.getParameter("birrificio"));
        prodotto.setFormato(request.getParameter("formato"));
        prodotto.setQuantitaDisp(Integer.parseInt(request.getParameter("quantita")));
        prodotto.setDescrizione(request.getParameter("descrizione"));
        prodotto.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
        prodotto.setPathImage(request.getParameter("image"));
        CatalogoModel cm = new CatalogoModel();

        if(oldProduct.compareKeys(prodotto)){
            try {
                if(cm.updateProduct(oldProduct, prodotto)){
                    ArrayList<Prodotto> prodotti=(ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
                    prodotti = Prodotto.remove(prodotti, oldProduct);
                    prodotti.add(prodotto);
                    request.getSession().removeAttribute("prodotti");
                    request.getSession().setAttribute("prodotti", prodotti);
                    response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
                }
                else
                    response.sendRedirect("http://localhost:8080/webAppTest_war/modificaProdotto.jsp");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            boolean flag;
            try {
                flag = cm.searchProductByKey(prodotto.getNome(), prodotto.getBirrificio());
                if(flag) {
                    request.setAttribute("error", "error");
                    request.getSession().removeAttribute("oldProduct");
                    request.getSession().setAttribute("oldProduct", oldProduct);
                    response.sendRedirect("http://localhost:8080/webAppTest_war/modificaProdotto.jsp");
                } else {
                    try {
                        if(cm.updateProduct(oldProduct, prodotto)) {
                            ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
                            prodotti = Prodotto.remove(prodotti, oldProduct);
                            prodotti.add(prodotto);

                            request.getSession().removeAttribute("prodotti");
                            request.getSession().setAttribute("prodotti", prodotti);
                            response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
                        }
                        else
                            response.sendRedirect("http://localhost:8080/webAppTest_war/modificaProdotto.jsp");

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
