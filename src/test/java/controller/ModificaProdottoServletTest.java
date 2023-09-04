package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Prodotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ModificaProdottoServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@InjectMocks
	private ModificaProdottoServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new ModificaProdottoServlet();
		servlet.init();
	}
	@Test
	void ModificaProdottoServletSuccess() throws ServletException, IOException {
		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");

		when(request.getSession().getAttribute("oldProduct")).thenReturn(prodotto);

		when(request.getParameter("nome")).thenReturn("Lazy Wit");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("Lattina 44cl");
		when(request.getParameter("quantita")).thenReturn("22");
		when(request.getParameter("descrizione")).thenReturn("Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("");

		ArrayList<Prodotto> prodotti = new ArrayList<>();
		prodotti.add(prodotto);

		when(request.getSession().getAttribute("prodotti")).thenReturn(prodotti);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
	}

	@Test
	void ModificaProdottoServletUpdate() throws ServletException, IOException {
		Prodotto prodotto = new Prodotto(
				"Lazy Wit", 4.05F,"Impavida", 22,"Lattina 44cl",
				"Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");

		when(request.getSession().getAttribute("oldProduct")).thenReturn(prodotto);

		when(request.getParameter("nome")).thenReturn("Lazy Wit");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("Lattina 33cl");
		when(request.getParameter("quantita")).thenReturn("22");
		when(request.getParameter("descrizione")).thenReturn("Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit Opalescente, sinuosa, profumatissima… è la birra perfetta per bevute spensierate e rinfrescanti ma ti darà grandi soddisfazioni anche a tavola. Are you ready");
		when(request.getParameter("prezzo")).thenReturn("1.95");
		when(request.getParameter("image")).thenReturn("");

		ArrayList<Prodotto> prodotti = new ArrayList<>();
		prodotti.add(prodotto);

		when(request.getSession().getAttribute("prodotti")).thenReturn(prodotti);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
	}
}