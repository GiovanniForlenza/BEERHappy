package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Carrello;
import model.bean.Carta;
import model.bean.Indirizzo;
import model.bean.Utente;
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

class RimozioneIndirizzoServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;

	@InjectMocks
	private RimozioneIndirizzoServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new RimozioneIndirizzoServlet();
		servlet.init();
	}

	@Test
	void RimozioneIndirizzo() throws ServletException, IOException {
		ArrayList<Carta> carte = new ArrayList<>();
		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		Carrello carrello = new Carrello();

		Utente utente = new Utente(1,"GionPallino@gmail.com","ilPiccolo","Gino","Paolo",carte,indirizzi,carrello);

		when(request.getParameter("indirizzoID")).thenReturn("1");
		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		servlet.doGet(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");

	}
}