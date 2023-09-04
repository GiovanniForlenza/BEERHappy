package controller;

import model.bean.Carrello;
import model.bean.Carta;
import model.bean.Indirizzo;
import model.bean.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AggiuntaIndirizzoServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@InjectMocks
	private AggiuntaIndirizzoServlet servlet;
	@Mock
	private Utente utente;


	@BeforeEach
	void setUp() throws ServletException {
		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		ArrayList<Carta> carte = new ArrayList<>();
		Carrello carrello = new Carrello();
		utente = new Utente(100, "marioR@gmail.com","SUPERMario","Mario","Rossi", carte, indirizzi, carrello);

		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new AggiuntaIndirizzoServlet();
		servlet.init();
	}

	@Test
	void AggiuntaIndirizzo_1() throws ServletException, IOException{
		String citta, via, civico, cap, telefono;

		via = "123";
		civico = "22";
		citta = "Salerno";
		cap = "84020";
		telefono = "3331234567";

		when(request.getParameter("citta")).thenReturn(citta);
		when(request.getParameter("via")).thenReturn(via);
		when(request.getParameter("civico")).thenReturn(civico);
		when(request.getParameter("cap")).thenReturn(cap);
		when(request.getParameter("telefono")).thenReturn(telefono);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);

	}
	@Test
	void AggiuntaIndirizzo_2() throws ServletException, IOException{
		String citta, via, civico, cap, telefono;

		via = "Via Roma";
		civico = "2202";
		citta = "Salerno";
		cap = "84020";
		telefono = "3331234567";

		when(request.getParameter("citta")).thenReturn(citta);
		when(request.getParameter("via")).thenReturn(via);
		when(request.getParameter("civico")).thenReturn(civico);
		when(request.getParameter("cap")).thenReturn(cap);
		when(request.getParameter("telefono")).thenReturn(telefono);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);

	}

	@Test
	void AggiuntaIndirizzo_3() throws ServletException, IOException{
		String citta, via, civico, cap, telefono;

		via = "Via Roma";
		civico = "22";
		citta = "222";
		cap = "84020";
		telefono = "3331234567";

		when(request.getParameter("citta")).thenReturn(citta);
		when(request.getParameter("via")).thenReturn(via);
		when(request.getParameter("civico")).thenReturn(civico);
		when(request.getParameter("cap")).thenReturn(cap);
		when(request.getParameter("telefono")).thenReturn(telefono);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);

	}

	@Test
	void AggiuntaIndirizzo_4() throws ServletException, IOException{
		String citta, via, civico, cap, telefono;

		via = "Via Roma";
		civico = "22";
		citta = "Salerno";
		cap = "840";
		telefono = "3331234567";

		when(request.getParameter("citta")).thenReturn(citta);
		when(request.getParameter("via")).thenReturn(via);
		when(request.getParameter("civico")).thenReturn(civico);
		when(request.getParameter("cap")).thenReturn(cap);
		when(request.getParameter("telefono")).thenReturn(telefono);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);

	}

	@Test
	void AggiuntaIndirizzo_5() throws ServletException, IOException{
		String citta, via, civico, cap, telefono;

		via = "Via Roma";
		civico = "22";
		citta = "Salerno";
		cap = "84020";
		telefono = "3331234asd";

		when(request.getParameter("citta")).thenReturn(citta);
		when(request.getParameter("via")).thenReturn(via);
		when(request.getParameter("civico")).thenReturn(civico);
		when(request.getParameter("cap")).thenReturn(cap);
		when(request.getParameter("telefono")).thenReturn(telefono);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);

	}

	@Test
	void AggiuntaIndirizzo_6() throws ServletException, IOException{
		String citta, via, civico, cap, telefono;

		via = "Via Roma";
		civico = "22";
		citta = "Salerno";
		cap = "84020";
		telefono = "3331234";

		when(request.getParameter("citta")).thenReturn(citta);
		when(request.getParameter("via")).thenReturn(via);
		when(request.getParameter("civico")).thenReturn(civico);
		when(request.getParameter("cap")).thenReturn(cap);
		when(request.getParameter("telefono")).thenReturn(telefono);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);

	}

	@Test
	void AggiuntaIndirizzo_7() throws ServletException, IOException{
		String citta, via, civico, cap, telefono;

		via = "Via Roma";
		civico = "22";
		citta = "Salerno";
		cap = "84020";
		telefono = "3331234567";

		when(request.getParameter("citta")).thenReturn(citta);
		when(request.getParameter("via")).thenReturn(via);
		when(request.getParameter("civico")).thenReturn(civico);
		when(request.getParameter("cap")).thenReturn(cap);
		when(request.getParameter("telefono")).thenReturn(telefono);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", false);

	}
}