package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Carrello;
import model.bean.Prodotto;
import model.bean.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import static org.mockito.Mockito.when;

class AggiuntaProdottoCarrelloServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@InjectMocks
	private AggiuntaProdottoCarrelloServlet servlet;
	@Mock
	private Utente utente;
	@Mock
	private Carrello carrello;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new AggiuntaProdottoCarrelloServlet();
		carrello = new Carrello();
		servlet.init();
	}

	@Test
	void AggiuntaProdottoCarrelloServletSuccess() throws ServletException, IOException {
		request.getSession().setAttribute("carrello", carrello);

		when(request.getParameter("quantity")).thenReturn("5");

		when(request.getSession().getAttribute("action")).thenReturn("addCart");
		when(request.getSession().getAttribute("nome")).thenReturn("Breakfast Porter");
		when(request.getSession().getAttribute("birrificio")).thenReturn("Impavida");

		servlet.doPost(request, response);
	}

	@Test
	void AggiuntaProdottoCarrelloServletActionIsNull() throws ServletException, IOException {

		request.getSession().setAttribute("carrello", carrello);

		when(request.getParameter("quantity")).thenReturn("5");

		when(request.getSession().getAttribute("action")).thenReturn(null);
		when(request.getSession().getAttribute("nome")).thenReturn("Breakfast Porter");
		when(request.getSession().getAttribute("birrificio")).thenReturn("Impavida");

		servlet.doPost(request, response);

	}

	@Test
	void AggiuntaProdottoCarrelloServletActionCartNotNull() throws ServletException, IOException {
		Prodotto prodotto = new Prodotto(
		"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");

		carrello.addProdotti(prodotto);
		request.getSession().setAttribute("carrello", carrello);

		when(request.getParameter("quantity")).thenReturn("5");

		when(request.getSession().getAttribute("action")).thenReturn("addCart");
		when(request.getSession().getAttribute("nome")).thenReturn("Breakfast Porter");
		when(request.getSession().getAttribute("birrificio")).thenReturn("Impavida");

		servlet.doPost(request, response);
	}
}


