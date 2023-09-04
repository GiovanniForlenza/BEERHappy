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

class LogoutServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private LogoutServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new LogoutServlet();
		Carrello carrello = new Carrello();
		ArrayList<Carta> carte = new ArrayList<>();
		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		utente = new Utente(2,"pinoPaolo@gmail.com","ilPiccoloPino","Pino","Paolo", carte, indirizzi, carrello);
		servlet.init();

	}
	@Test
	void LogoutServlet() throws ServletException, IOException {
		Carrello cart = new Carrello();
		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getSession().getAttribute("carrello")).thenReturn(cart);

		servlet.doGet(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/homePage.jsp");
	}
}