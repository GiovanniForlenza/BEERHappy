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
import static org.mockito.Mockito.when;

class AnnullaOrdineServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private AnnullaOrdineServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new AnnullaOrdineServlet();
		servlet.init();
	}

	@Test
	void AnnullaOrdineServletSucess() throws ServletException, IOException {
		Carrello carrello = new Carrello();
		ArrayList<Carta> carte = new ArrayList<>();
		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		utente = new Utente(1,"ginoPaolo@gmail.com","ilPiccoloGino","Gino","Paolo", carte, indirizzi, carrello);
		Ordine ordine = new Ordine(99,"2023-07-18",22.05F,"Via Roma",22,"Salerno", "3331234328");
		ArrayList<Ordine> ordini = new ArrayList<>();
		ordini.add(ordine);
		utente.setOrdini(ordini);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("ordineID")).thenReturn("99");
		servlet.doPost(request,response);
	}

}