package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RichiestaBirreServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private ServletContext servletcontex;
	@Mock
	private RequestDispatcher requestDispatcher;
	@Mock
	private ServletConfig servletConfig;
	@InjectMocks
	private RichiestaBirreServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servletcontex = mock(ServletContext.class);
		servletConfig = mock(ServletConfig.class);
		requestDispatcher = mock(RequestDispatcher.class);
		servlet = new RichiestaBirreServlet();
		servlet.init(servletConfig);

	}

	@Test
	void RichiestaBirraServletCatalogo() throws ServletException, IOException {

		when(request.getAttribute("birre")).thenReturn(null);
		when(request.getAttribute("error")).thenReturn(null);

		when(request.getSession().getAttribute("home")).thenReturn(null);
		when(request.getSession().getAttribute("homestore")).thenReturn(null);

		when(servlet.getServletContext()).thenReturn(servletcontex);
		when(servletcontex.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

		servlet.doPost(request,response);
	}

	@Test
	void RichiestaBirraServletHome() throws ServletException, IOException {

		when(request.getAttribute("birre")).thenReturn(null);
		when(request.getAttribute("error")).thenReturn(null);

		when(request.getSession().getAttribute("home")).thenReturn(true);
		when(request.getSession().getAttribute("homestore")).thenReturn(null);

		when(servlet.getServletContext()).thenReturn(servletcontex);
		when(servletcontex.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

		servlet.doPost(request,response);
	}

	@Test
	void RichiestaBirraServletHomeStore() throws ServletException, IOException {

		when(request.getAttribute("birre")).thenReturn(null);
		when(request.getAttribute("error")).thenReturn(null);

		when(request.getSession().getAttribute("home")).thenReturn(null);
		when(request.getSession().getAttribute("homestore")).thenReturn(true);

		when(servlet.getServletContext()).thenReturn(servletcontex);
		when(servletcontex.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

		servlet.doPost(request,response);
	}




}