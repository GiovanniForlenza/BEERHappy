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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AggiuntaCartaServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private AggiuntaCartaServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new AggiuntaCartaServlet();
		Carrello carrello = new Carrello();
		ArrayList<Carta> carte = new ArrayList<>();
		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		utente = new Utente(2,"pinoPaolo@gmail.com","ilPiccoloPino","Pino","Paolo", carte, indirizzi, carrello);
		servlet.init();

	}

	@Test
	void aggiuntaCarta_1() throws ServletException, IOException {

		String ncarta = "1234";
		String titolare = "Mario Rossi";
		String dataScadenza = "2025-07-19";
		String cvv = "123";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void aggiuntaCarta_2() throws ServletException, IOException {

		String ncarta = "njkdaflad";
		String titolare = "Mario Rossi";
		String dataScadenza = "2025-07-19";
		String cvv = "123";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void aggiuntaCarta_3() throws ServletException, IOException {

		String ncarta = "1234123412341234";
		String titolare = "";
		String dataScadenza = "2025-07-19";
		String cvv = "123";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void aggiuntaCarta_4() throws ServletException, IOException {

		String ncarta = "1234123412341234";
		String titolare = "56342";
		String dataScadenza = "2025-07-19";
		String cvv = "123";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void aggiuntaCarta_5() throws ServletException, IOException {

		String ncarta = "1234123412341234";
		String titolare = "Mario Rossi";
		String dataScadenza = "asd";
		String cvv = "123";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void aggiuntaCarta_6() throws ServletException, IOException {

		String ncarta = "1234123412341234";
		String titolare = "Mario Rossi";
		String dataScadenza = "2025-07-19";
		String cvv = "1";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void aggiuntaCarta_7() throws ServletException, IOException {

		String ncarta = "1234123412341234";
		String titolare = "Mario Rossi";
		String dataScadenza = "2025-07-19";
		String cvv = "asd";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaCarta.jsp");
	}

	@Test
	void aggiuntaCarta_8() throws ServletException, IOException {

		String ncarta = "1234123412341234";
		String titolare = "Mario Rossi";
		String dataScadenza = "2025-07-19";
		String cvv = "123";

		when(request.getParameter("titolare")).thenReturn(titolare);
		when(request.getParameter("dataScadenza")).thenReturn(dataScadenza);
		when(request.getParameter("numero")).thenReturn(ncarta);
		when(request.getParameter("cvv")).thenReturn(cvv);

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("selectCard")).thenReturn(false);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}
}