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

class RegistrazioneServletTest {
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpSession session;
	@InjectMocks
	private RegistrazioneServlet servlet;

	@Mock
	private UserModel userModel;
	@Mock
	private Utente utente;


	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new RegistrazioneServlet();
		servlet.init();
	}
/*
	@Test
	void testRegistrationSuccess() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Mario";
		cognome = "Rossi";
		email = "MarioR@gmail.com";
		password = "SuperMario64";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(true);

		userModel.addUser(utente);

		servlet.doPost(request, response);
		verify(session).setAttribute("accessoUtente", true);
	}

	@Test
	void testRegistrationFailed() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Gino";
		cognome = "Paolo";
		email = "ginoPaolo@gmail.com";
		password = "ilPiccoloGino";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		userModel.addUser(utente);

		servlet.doPost(request, response);
		verify(session).setAttribute("error_mail", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/signUp.jsp");
	}
*/
	@Test
	void Registrazione_1() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "";
		cognome = "Rossi";
		email = "marioR@gmail.com";
		password = "SUPERMario";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		servlet.doPost(request, response);
		verify(session).setAttribute("error_mail", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/signup.jsp");
	}

	@Test
	void Registrazione_2() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Mar10";
		cognome = "Rossi";
		email = "marioR@gmail.com";
		password = "SUPERMario";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		servlet.doPost(request, response);
		verify(session).setAttribute("error_mail", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/signup.jsp");
	}

	@Test
	void Registrazione_3() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Mario";
		cognome = "";
		email = "marioR@gmail.com";
		password = "SUPERMario";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		servlet.doPost(request, response);
		verify(session).setAttribute("error_mail", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/signup.jsp");
	}

	@Test
	void Registrazione_4() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Mario";
		cognome = "Rossi";
		email = "";
		password = "SUPERMario";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		servlet.doPost(request, response);
		verify(session).setAttribute("error_mail", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/signup.jsp");
	}

	@Test
	void Registrazione_5() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Mario";
		cognome = "Rossi";
		email = "marioR@gmail";
		password = "SUPERMario";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		servlet.doPost(request, response);
		verify(session).setAttribute("error_mail", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/signup.jsp");
	}

	@Test
	void Registrazione_6() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Mario";
		cognome = "Rossi";
		email = "marioR@gmail.com";
		password = "SUPER";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		servlet.doPost(request, response);
		verify(session).setAttribute("error_mail", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/signup.jsp");
	}

	@Test
	void Registrazione_8() throws SQLException, ServletException, IOException {
		String nome, cognome, email, password;
		nome = "Mario";
		cognome = "Rossi";
		email = "marioR@gmail.com";
		password = "SUPERMario";

		when(request.getParameter("nome")).thenReturn(nome);
		when(request.getParameter("cognome")).thenReturn(cognome);
		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.controlloEmailRegistrazione(utente)).thenReturn(false);

		servlet.doPost(request, response);
		verify(session).setAttribute("accessoUtente", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
	}
}