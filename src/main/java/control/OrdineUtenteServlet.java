package control;

import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "OrdineUtenteServlet", value = "/OrdineUtenteServlet")
public class OrdineUtenteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        Indirizzo indirizzo = (Indirizzo) request.getSession().getAttribute("visualizza");
        Carta carta = (Carta) request.getSession().getAttribute("cardView");
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        if(utente != null && indirizzo != null && carta != null && carrello != null) {
            float prezzo = (Float) request.getSession().getAttribute("prezzo");
            //TODO data da gestire

            LocalDate localDate=LocalDate.now();
            Ordine ordine = new Ordine();
            ordine.setDataOrdine(localDate.toString());
            ordine.setVia(indirizzo.getVia());
            ordine.setCitta(indirizzo.getCitta());
            ordine.setCivico(indirizzo.getCivico());
            ordine.setTelefono(indirizzo.getTelefono());
            ordine.setPrezzo(prezzo);
            ordine.setStato(Stato.inoltrato);

            OrderModel orderModel = new OrderModel();

            try {
                ordine = orderModel.aggiuntaOrdine(ordine, utente);
                Prodotto prodottoOrdinato = new Prodotto();
                for(int i = 0; i < carrello.getProdotti().size(); i++){
                    Prodotto prodotto = carrello.getProdotti().get(i);

                    prodottoOrdinato.setNome(prodotto.getNome());
                    prodottoOrdinato.setBirrificio(prodotto.getBirrificio());
                    prodottoOrdinato.setDescrizione(prodotto.getDescrizione());
                    prodottoOrdinato.setFormato(prodotto.getFormato());
                    prodottoOrdinato.setQuantita(prodotto.getQuantita());
                    prodottoOrdinato.setPrezzo(prodotto.getPrezzo());
                    prodottoOrdinato.setPathImage(prodotto.getPathImage());

                    orderModel.aggiuntaProdottiOrdine(prodottoOrdinato, ordine);
                }

                carrello.clearProdotti();
                utente.setOrdini(orderModel.recuperoOrdini(utente));
                request.getSession().removeAttribute("utente");
                request.getSession().setAttribute("utente", utente);
                response.sendRedirect("http://localhost:8080/webAppTest_war/ordinePagina.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else
            response.sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
    }
}
