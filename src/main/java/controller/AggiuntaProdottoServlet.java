package controller;

import model.bean.Prodotto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.CatalogoModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AggiuntaProdottoServlet", value = "/AggiuntaProdottoServlet")
public class AggiuntaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean quantita = true;
        boolean prezzo = true;
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setBirrificio(request.getParameter("birrificio"));
        prodotto.setFormato(request.getParameter("formato"));

        try {
            prodotto.setQuantitaDisp(Integer.parseInt(request.getParameter("quantita")));
        }catch (NumberFormatException e){
            e.printStackTrace();
            quantita = false;
        }

        prodotto.setDescrizione(request.getParameter("descrizione"));

        try{
            prodotto.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
        }catch(NumberFormatException e){
            e.printStackTrace();
            prodotto.setPrezzo(0.0F);
            prezzo = false;
        }

        prodotto.setPathImage(request.getParameter("image"));
        CatalogoModel catalogoModel = new CatalogoModel();

        try {
            if(catalogoModel.searchProductByKey(prodotto.getNome(), prodotto.getBirrificio())){
                request.getSession().setAttribute("error", "Prodotto già presente");
                response.sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
            } else {
                if (prodotto.getPathImage() == "")
                {
                    prodotto.setPathImage("https://arbeiterbrewing.com/wp-content/uploads/2020/12/beerz-768x1024.jpg");
                }

                try {
                    if(prezzo && quantita && catalogoModel.addProduct(prodotto)) {
                        ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
                        if (prodotti != null) {
                            prodotti.add(prodotto);
                        } else {
                            prodotti = new ArrayList<>();
                            prodotti.add(prodotto);
                        }
                        request.getSession().removeAttribute("prodotti");
                        request.getSession().setAttribute("prodotti", prodotti);

                        request.getSession().setAttribute("errorFormato",false);
                        response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
                    }
                    else{
                        request.getSession().setAttribute("errorFormato",true);
                        response.sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
