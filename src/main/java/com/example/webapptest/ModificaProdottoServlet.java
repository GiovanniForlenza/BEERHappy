package com.example.webapptest;

import entity.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.CatalogoModel;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ModificaProdottoServlet", value = "/ModificaProdottoServlet")
public class ModificaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto oldProduct=(Prodotto) request.getSession().getAttribute("prodotto");
        Prodotto newProduct=new Prodotto();
        newProduct.setNome(request.getParameter("nome"));
        newProduct.setBirrificio(request.getParameter("birrificio"));
        newProduct.setFormato(request.getParameter("formato"));
        newProduct.setQuantitaDisp(Integer.parseInt(request.getParameter("quantita")));
        newProduct.setDescrizione(request.getParameter("descrizione"));
        newProduct.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        newProduct.setPathImage(request.getParameter("pathImage"));
        CatalogoModel cm=new CatalogoModel();

        if(!(cm.searchProductByKey(newProduct.getNome(), newProduct.getBirrificio()))) {

            request.getSession().setAttribute("error", "Prodotto già presente");
            request.getSession().setAttribute("prodotto", oldProduct);
            response.sendRedirect("http://localhost:8080/webAppTest_war/modificaProdotto.jsp");

        } else {
            cm.updateProduct(oldProduct, newProduct);
            ArrayList<Prodotto> prodotti=(ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
            prodotti=Prodotto.remove(prodotti, oldProduct);
            prodotti.add(newProduct);

            request.getSession().removeAttribute("prodotti");
            request.getSession().setAttribute("prodotti", prodotti);
            response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
        }
    }
}
