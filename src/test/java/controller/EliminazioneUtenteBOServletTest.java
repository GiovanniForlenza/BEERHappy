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

import static org.mockito.Mockito.when;

class EliminazioneUtenteBOServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private UtenteBO utente;
	@InjectMocks
	private EliminazioneUtenteBOServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new EliminazioneUtenteBOServlet();
		servlet.init();
		utente = new UtenteBO("ciropallinopal@beerhappy.it","pallinopal",3);
	}

	@Test
	void EliminazioneUtenteBOServlet() throws ServletException, IOException {
		ArrayList<UtenteBO> utenti = new ArrayList<>();

		utenti.add(utente);

		when(request.getSession().getAttribute("utenti")).thenReturn(utente);
		when(request.getSession().getAttribute("utenti")).thenReturn(utenti);

		servlet.doPost(request,response);
	}
}