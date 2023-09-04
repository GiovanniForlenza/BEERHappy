package controller;

import model.bean.Carrello;
import model.bean.Carta;
import model.bean.Indirizzo;
import model.bean.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class CambioPasswordServletTest {
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@InjectMocks
	private CambioPasswordServlet servlet;
	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new CambioPasswordServlet();
		servlet.init();
	}
/*
	@Test
	void cambioPasswordSuccess() throws IOException, ServletException {
		String oldPassword, newPassword;

		oldPassword = "ilPiccoloGino";
		newPassword = "SuperMarioBros64";

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("old")).thenReturn(oldPassword);
		when(request.getParameter("password")).thenReturn(newPassword);
		
		servlet.doPost(request, response);
		verify(session).setAttribute("notChack", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/cambioPassword.jsp");
	}
	@Test
	void cambioPasswordFailed() throws IOException, ServletException {
		String oldPassword, newPassword;

		oldPassword = "ilPiccoloGino";
		newPassword = "SuperMarioBros64";

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("old")).thenReturn(oldPassword);
		when(request.getParameter("password")).thenReturn(newPassword);

		servlet.doPost(request, response);
		verify(session).setAttribute("notChack", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/cambioPassword.jsp");
	}
*/
	@Test
	void modificaPassword_1() throws IOException, ServletException {
		String oldPassword, newPassword;

		oldPassword = "Super";
		newPassword = "SuperMarioBros64";

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("old")).thenReturn(oldPassword);
		when(request.getParameter("password")).thenReturn(newPassword);

		servlet.doPost(request, response);
		verify(session).setAttribute("notChack", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/cambioPassword.jsp");
	}

	@Test
	void modificaPassword_2() throws IOException, ServletException {

		String oldPassword, newPassword;
		oldPassword = "SUPERMario";
		newPassword = "Super";

		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		ArrayList<Carta> carte = new ArrayList<>();
		Carrello carrello = new Carrello();
		utente = new Utente(100, "marioR@gmail.com","SUPERMario","Mario","Rossi", carte, indirizzi, carrello);

		when(request.getParameter("old")).thenReturn(oldPassword);
		when(request.getParameter("password")).thenReturn(newPassword);
		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request, response);

		verify(session).setAttribute("notChack", false);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/cambioPassword.jsp");
	}


	@Test
	void modificaPassword_4() throws IOException, ServletException {

		String oldPassword, newPassword;
		oldPassword = "SUPERMario";
		newPassword = "SuperMARIO64";

		ArrayList<Indirizzo> indirizzi = new ArrayList<>();
		ArrayList<Carta> carte = new ArrayList<>();
		Carrello carrello = new Carrello();
		utente = new Utente(100, "marioR@gmail.com","SUPERMario","Mario","Rossi", carte, indirizzi, carrello);

		when(request.getParameter("old")).thenReturn(oldPassword);
		when(request.getParameter("password")).thenReturn(newPassword);
		when(request.getSession().getAttribute("utente")).thenReturn(utente);

		servlet.doPost(request, response);

		verify(session).setAttribute("notChack", false);
		verify(session).setAttribute("errorFormato", false);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

}