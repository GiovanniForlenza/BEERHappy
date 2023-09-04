package controller;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SelezionaCartaServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;

	@InjectMocks
	private SelezionaCartaServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new SelezionaCartaServlet();
		servlet.init();
	}

	@Test
	void SelezionaCarta() throws ServletException, IOException {
		when(request.getParameter("selezionato")).thenReturn("1");
		servlet.doPost(request,response);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/effettuaOrdine.jsp");
	}
}