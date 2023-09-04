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

class EliminazioneProdottoServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private EliminazioneProdottoServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new EliminazioneProdottoServlet();

		servlet.init();
	}

	@Test
	void EliminazioneProdottoServletSuccess() throws ServletException, IOException {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");
		prodotti.add(prodotto);

		when(request.getSession().getAttribute("oldProduct")).thenReturn(prodotto);
		when(request.getSession().getAttribute("prodotti")).thenReturn(prodotti);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
	}

	@Test
	void EliminazioneProdottoServletFailed() throws ServletException, IOException {
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");
		prodotti.add(prodotto);

		when(request.getSession().getAttribute("oldProduct")).thenReturn(null);
		when(request.getSession().getAttribute("prodotti")).thenReturn(prodotti);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/modificaProdotto.jsp");
	}
}