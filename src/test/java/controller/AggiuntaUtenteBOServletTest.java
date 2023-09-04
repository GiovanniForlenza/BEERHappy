package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.UtenteBO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AggiuntaUtenteBOServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@InjectMocks
	private AggiuntaUtenteBOServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new AggiuntaUtenteBOServlet();
		servlet.init();
	}

	@Test
	void aggiuntaDiUnUtenteBO_2() throws ServletException, IOException {
		String[] ruoli = {"Gestore catalogo"};
		when(request.getParameterValues("ruolo")).thenReturn(ruoli);

		when(request.getParameterValues("ruoli")).thenReturn(ruoli);

		when(request.getParameter("email")).thenReturn("ciropallinopal@gmail.it");
		when(request.getParameter("password")).thenReturn("ans169ndfso193");

		servlet.doPost(request, response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaUtente.jsp");
	}

	@Test
	void aggiuntaDiUnUtenteBO_3() throws ServletException, IOException {

		String[] ruoli = {"Gestore catalogo"};
		when(request.getParameterValues("ruolo")).thenReturn(ruoli);

		when(request.getParameter("email")).thenReturn("test@beerhappy.it");
		when(request.getParameter("password")).thenReturn("password123");

		ArrayList<UtenteBO> utenti = new ArrayList<>();
		when(session.getAttribute("utenti")).thenReturn(utenti);

		servlet.doPost(request, response);

		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/gestioneUtenti.jsp");
	}
}