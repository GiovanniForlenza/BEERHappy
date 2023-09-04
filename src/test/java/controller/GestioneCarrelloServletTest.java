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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GestioneCarrelloServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private GestioneCarrelloServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new GestioneCarrelloServlet();
		servlet.init();
	}

	@Test
	void GestioneCarrelloServletRimuoviProdotto() throws ServletException, IOException {
		Carrello carrello = new Carrello();
		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");

		carrello.addProdotti(prodotto);

		when(request.getSession().getAttribute("carrello")).thenReturn(carrello);
		when(request.getParameter("action")).thenReturn("rimuoviProdotto");
		when(request.getParameter("nome")).thenReturn("Lazy Wit");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("Lattina 44cl");
		when(request.getParameter("quantita")).thenReturn("22");

		servlet.doPost(request,response);
	}

	@Test
	void GestioneCarrelloServletCheckoutFalse() throws ServletException, IOException {
		Carrello carrello = new Carrello();
		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");

		carrello.addProdotti(prodotto);

		when(request.getSession().getAttribute("carrello")).thenReturn(carrello);
		when(request.getParameter("action")).thenReturn("checkout");
		when(request.getParameter("nome")).thenReturn("Lazy Wit");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("Lattina 44cl");
		when(request.getParameter("quantita")).thenReturn("22");
		when(request.getSession().getAttribute("accessoUtente")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
	}

	@Test
	void GestioneCarrelloServletCheckoutTrue() throws ServletException, IOException {
		Carrello carrello = new Carrello();
		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");

		carrello.addProdotti(prodotto);

		when(request.getSession().getAttribute("carrello")).thenReturn(carrello);
		when(request.getParameter("action")).thenReturn("checkout");
		when(request.getParameter("nome")).thenReturn("Lazy Wit");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("Lattina 44cl");
		when(request.getParameter("quantita")).thenReturn("22");
		when(request.getSession().getAttribute("accessoUtente")).thenReturn(true);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
	}
}
