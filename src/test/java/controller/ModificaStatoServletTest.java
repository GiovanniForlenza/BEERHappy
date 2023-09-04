package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Ordine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ModificaStatoServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@InjectMocks
	private ModificaStatoServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new ModificaStatoServlet();
		servlet.init();
	}

	@Test
	void ModificaStatoOrdini() throws ServletException, IOException {
		when(request.getParameter("newState")).thenReturn("consegnato");
		Ordine ordine = new Ordine();
		when(request.getSession().getAttribute("ordine")).thenReturn(ordine);
		ArrayList<Ordine> ordini = new ArrayList<>();
		ordini.add(ordine);
		when(request.getSession().getAttribute("ordini")).thenReturn(ordini);

		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/gestioneOrdini.jsp");
	}

}