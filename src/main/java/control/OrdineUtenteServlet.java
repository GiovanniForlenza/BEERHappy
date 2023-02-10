package control;

import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
            Date today = calendar.getTime();

            Ordine ordine = new Ordine();
            ordine.setEmail(utente.getEmail());
            ordine.setDataOrdine(today.toString());
            ordine.setVia(indirizzo.getVia());
            ordine.setCitta(indirizzo.getCitta());
            ordine.setCivico(indirizzo.getCivico());
            ordine.setTelefono(indirizzo.getTelefono());
            ordine.setPrezzo(prezzo);
            ordine.setStato("inoltrato");

            OrderModel orderModel = new OrderModel();

            try {
                ordine = orderModel.aggiuntaOrdine(ordine);
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
