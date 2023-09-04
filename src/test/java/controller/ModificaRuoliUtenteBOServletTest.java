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

class ModificaRuoliUtenteBOServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@InjectMocks
	private ModificaRuoliUtenteBOServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new ModificaRuoliUtenteBOServlet();
		servlet.init();
	}

	@Test
	void ModificaRuoloUtenteBO() throws ServletException, IOException {
		String[] ruolo = {"Gestore catalogo"};
		when(request.getParameterValues("ruolo")).thenReturn(ruolo);

		UtenteBO utente = new UtenteBO();
		utente.setEmail("ciropallinopal@beerhappy.it");
		utente.setPassword("pallinopal");
		utente.setRuolo(3);
		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		ArrayList<UtenteBO> utenti = new ArrayList<>();
		utenti.add(utente);

		when(request.getSession().getAttribute("utenti")).thenReturn(utenti);

		servlet.doPost(request,response);
	}

}