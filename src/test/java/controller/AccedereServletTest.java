package controller;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.bean.Utente;
import model.bean.UtenteBO;
import model.dao.AddressModel;
import model.dao.CardModel;
import model.dao.CartModel;
import model.dao.UserModel;
import model.dao.OrderModel;


public class AccedereServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
	private UserModel userModel;
	@Mock
	private UtenteBO utenteBO;
	@Mock
	private Utente utente;
	@Mock
	private AddressModel addressModel;
	@Mock
	private OrderModel orderModel;
	@Mock
	private CardModel cardModel;
	@Mock
	private CartModel cartModel;
	@InjectMocks
	private AccedereServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new AccedereServlet();
		servlet.init();
	}
/*
	@Test
	void testLoginAmministratore() throws ServletException, IOException, SQLException {
		String email = "asd@beerhappy.it";
		String password = "asd";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginAmministratore(email, password)).thenReturn(utenteBO);

		servlet.doPost(request, response);

		verify(session).setAttribute("accessoUtenteBO", true);
		verify(session).setAttribute("accessoUtente", false);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/selezioneRuolo.jsp");
	}

	@Test
	void testLoginUtente() throws ServletException, IOException, SQLException {
		String email = "ginoPaolo@gmail.com";
		String password = "ilPiccoloGino";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginUtente(email, password)).thenReturn(utente);
		when(orderModel.recuperoOrdini(utente)).thenReturn(null);
		when(addressModel.recuperoIndirizzo(utente)).thenReturn(null);
		when(cardModel.recuperoCarte(utente)).thenReturn(null);
		when(request.getSession().getAttribute("carrello")).thenReturn(null);
		when(cartModel.recuperoCarrello(utente, null)).thenReturn(null);

		servlet.doPost(request, response);

		verify(session).setAttribute("accessoUtenteBO", false);
		verify(session).setAttribute("accessoUtente", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
	}

	@Test
	void testLoginFailed() throws SQLException, ServletException, IOException {
		//failed login test
		String email = "pinkoPallino@libero.it";
		String password = "password";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginUtente(email,password)).thenReturn(null);
		when(userModel.loginUtente(email,password)).thenReturn(null);
		when(request.getSession()).thenReturn(session);
		servlet.doPost(request, response);
		verify(session).setAttribute("accessoUtenteBO", false);
		verify(session).setAttribute("accessoUtente", false);
		verify(session).setAttribute("email_password", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
	}
*/
	@Test
	void Login_1() throws SQLException, ServletException, IOException {
		String email = "";
		String password = "ilPiccoloGino";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginAmministratore(email, password)).thenReturn(utenteBO);

		servlet.doPost(request, response);

		verify(session).setAttribute("accessoUtenteBO", false);
		verify(session).setAttribute("accessoUtente", false);
		verify(session).setAttribute("email_password", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
	}

	@Test
	void Login_2() throws SQLException, ServletException, IOException {
		String email = "ginoPaolo";
		String password = "ilPiccoloGino";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginAmministratore(email, password)).thenReturn(utenteBO);

		servlet.doPost(request, response);

		verify(session).setAttribute("accessoUtenteBO", false);
		verify(session).setAttribute("accessoUtente", false);
		verify(session).setAttribute("email_password", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
	}

	@Test
	void Login_3() throws SQLException, ServletException, IOException {
		String email = "ginoPaolo@gmail.it";
		String password = "";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginAmministratore(email, password)).thenReturn(utenteBO);

		servlet.doPost(request, response);

		verify(session).setAttribute("accessoUtenteBO", false);
		verify(session).setAttribute("accessoUtente", false);
		verify(session).setAttribute("email_password", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
	}

	@Test
	void Login_4() throws SQLException, ServletException, IOException {
		String email = "ginoPaolo@gmail.it";
		String password = "ilPiccolo";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginAmministratore(email, password)).thenReturn(utenteBO);

		servlet.doPost(request, response);

		verify(session).setAttribute("accessoUtenteBO", false);
		verify(session).setAttribute("accessoUtente", false);
		verify(session).setAttribute("email_password", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/login.jsp");
	}

	@Test
	void Login_5() throws SQLException, ServletException, IOException {
		String email = "ginoPaolo@gmail.com";
		String password = "ilpiccologino";

		when(request.getParameter("e-mail")).thenReturn(email);
		when(request.getParameter("password")).thenReturn(password);
		when(userModel.loginAmministratore(email, password)).thenReturn(utenteBO);

		servlet.doPost(request, response);

		verify(session).setAttribute("accessoUtenteBO", false);
		verify(session).setAttribute("accessoUtente", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/homePageStore.jsp");
	}
}
