package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrdineUtenteServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private OrdineUtenteServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new OrdineUtenteServlet();
		Carrello carrello = new Carrello();
		ArrayList<Carta> carte = new ArrayList<>();
		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		utente = new Utente(2,"pinoPaolo@gmail.com","ilPiccoloPino","Pino","Paolo", carte, indirizzi, carrello);
		servlet.init();
	}

	@Test
	void OrdineUtenti() throws ServletException, IOException {
		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		Indirizzo indirizzo = new Indirizzo("Campagna","via Pariti", 16,"84022","3385875173",20);
		Carta carta = new Carta("1234123412341234","2026-05-02",123,"Gino",18);
		Carrello carrello = new Carrello();

		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 10,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");
		carrello.addProdotti(prodotto);

		when(request.getSession().getAttribute("visualizza")).thenReturn(indirizzo);
		when(request.getSession().getAttribute("cardView")).thenReturn(carta);
		when(request.getSession().getAttribute("carrello")).thenReturn(carrello);
		when(request.getSession().getAttribute("prezzo")).thenReturn(22.05F);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/ordinePagina.jsp");
	}
	@Test
	void OrdineUtentiFailed() throws ServletException, IOException {
		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		when(request.getSession().getAttribute("visualizza")).thenReturn(null);
		when(request.getSession().getAttribute("cardView")).thenReturn(null);
		when(request.getSession().getAttribute("carrello")).thenReturn(null);
		when(request.getSession().getAttribute("prezzo")).thenReturn(22.05F);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
	}
}