package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.when;

class DettagliProdottoServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private DettagliProdottoServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new DettagliProdottoServlet();
		servlet.init();
	}

	@Test
	void DettagliProdottoServlet() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("Free Solo");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("Lattina 44cl");

		servlet.doGet(request,response);
	}
}