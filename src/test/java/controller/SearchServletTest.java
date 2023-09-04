package controller;

import model.bean.Prodotto;
import model.dao.ProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private ProductModel productModel;
	@InjectMocks
	private SearchServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new SearchServlet();
	}

	@Test
	void SearchServlet() throws ServletException, IOException {
		// Mock della lista di prodotti
		ArrayList<Prodotto> mockProdotti = new ArrayList<>();

		mockProdotti.add(new Prodotto("Breakfast Porter", 4.05F, "Impavida", 50,"Lattina 44cl","Nuova versione della Robust Porter, con l`aggiunta di lattosio e del pregiato caffè Castillo Colombiano."));
		mockProdotti.add(new Prodotto("Lazy Wit", 4.05F, "Impavida",50,"Lattina 44cl", "Un twist di scorze di agrumi, coriandolo e pepe profuma questa Belgian Wit! Opalescente, sinuosa, profumatissima… è la birra	perfetta per bevute spensierate e rinfrescanti, ma ti darà grandi soddisfazioni anche a tavola. Are you ready?"));
		when(session.getAttribute("prodotti")).thenReturn(mockProdotti);

		// Configura il comportamento della richiesta
		when(request.getParameter("searchTerm")).thenReturn("Lazy Wit");

		// Configura lo StringWriter per catturare l'output del PrintWriter
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		// Esegui il metodo doPost
		servlet.doPost(request, response);

	}
}
