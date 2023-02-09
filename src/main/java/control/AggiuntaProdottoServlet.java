package control;

import entity.Prodotto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CatalogoModel;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AggiuntaProdottoServlet", value = "/AggiuntaProdottoServlet")
public class AggiuntaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto prodotto=new Prodotto();
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setBirrificio(request.getParameter("birrificio"));
        prodotto.setFormato(request.getParameter("formato"));
        prodotto.setQuantitaDisp(Integer.parseInt(request.getParameter("quantita")));
        prodotto.setDescrizione(request.getParameter("descrizione"));
        prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        prodotto.setPathImage(request.getParameter("pathImage"));
        CatalogoModel cm=new CatalogoModel();

        if(cm.searchProductByKey(prodotto.getNome(), prodotto.getBirrificio())){

            request.getSession().setAttribute("error", "Prodotto già presente");
            System.out.println("Prodotto già esistente");
            response.sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");


        } else {
            cm.addProduct(prodotto);
            ArrayList<Prodotto> prodotti=(ArrayList<Prodotto>) request.getSession().getAttribute("prodotti");
            prodotti.add(prodotto);
            request.getSession().removeAttribute("prodotti");
            request.getSession().setAttribute("prodotti", prodotti);

            System.out.println("Prodotto aggiunto");
            response.sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
        }
    }
}
