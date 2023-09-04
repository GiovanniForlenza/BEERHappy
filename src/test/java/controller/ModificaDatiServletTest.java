package controller;

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
import java.sql.SQLException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ModificaDatiServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private Utente utente;
	@Mock
	private UserModel userModel;
	@InjectMocks
	private ModificaDatiServlet servlet;


	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new ModificaDatiServlet();
		servlet.init();
	}
/*
	@Test
	void EditDataSuccess() throws SQLException, ServletException, IOException {
		String nome, cognome;
		nome = "Paolo";
		cognome = "Rossi";

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(userModel.modificaDati(utente, nome, cognome)).thenReturn(utente);
		servlet.doPost(request,response);
		verify(session).removeAttribute("utente");
		verify(session).setAttribute("utente", utente);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void EditDataFailed() throws SQLException, ServletException, IOException {
		String nome, cognome;
		nome = null;
		cognome = null;

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		servlet.doPost(request,response);

		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}
*/
	@Test
	void modificaDatiPersonali_1() throws ServletException, IOException {
		String nome, cognome;
		nome = "123";
		cognome = "Rossi";

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		servlet.doPost(request,response);

		verify(session).setAttribute("errorFormato",true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void modificaDatiPersonali_2() throws ServletException, IOException {
		String nome, cognome;
		nome = "Mario";
		cognome = "123";

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		servlet.doPost(request,response);

		verify(session).setAttribute("errorFormato",true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

	@Test
	void modificaDatiPersonali_3() throws SQLException, ServletException, IOException {
		String nome, cognome;
		nome = "Mario";
		cognome = "Rossi";

		when(request.getSession().getAttribute("utente")).thenReturn(utente);
		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);

		servlet.doPost(request,response);

		verify(session).setAttribute("utente",utente);
		verify(session).setAttribute("errorFormato",false);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/profilo.jsp");
	}

}