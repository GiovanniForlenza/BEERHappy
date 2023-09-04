package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Carrello;
import model.bean.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AggiuntaProdottoServletTest {
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@InjectMocks
	private AggiuntaProdottoServlet servlet;

	@BeforeEach
	void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		when(request.getSession()).thenReturn(session);
		servlet = new AggiuntaProdottoServlet();
		servlet.init();
	}

	@Test
	void aggiuntaDiUnProdotto_1() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("440 ml");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("50");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
	}

	@Test
	void aggiuntaDiUnProdotto_2() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("PELER");
		when(request.getParameter("birrificio")).thenReturn("");
		when(request.getParameter("formato")).thenReturn("440 ml");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("50");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
	}

	@Test
	void aggiuntaDiUnProdotto_3() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("PELER");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("50");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
	}

	@Test
	void aggiuntaDiUnProdotto_4() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("PELER");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("440 ml");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!" +
				"La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!" +
				"La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("50");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
	}

	@Test
	void aggiuntaDiUnProdotto_5() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("PELER");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("440 ml");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("0");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
	}

	@Test
	void aggiuntaDiUnProdotto_6() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("PELER");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("440 ml");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("aaa");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
	}

	@Test
	void aggiuntaDiUnProdotto_7() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("PELER");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("440 ml");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("50");
		when(request.getParameter("prezzo")).thenReturn("4,05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", true);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/aggiuntaProdotto.jsp");
	}

	@Test
	void aggiuntaDiUnProdotto_8() throws ServletException, IOException {
		when(request.getParameter("nome")).thenReturn("PELER");
		when(request.getParameter("birrificio")).thenReturn("Impavida");
		when(request.getParameter("formato")).thenReturn("440 ml");
		when(request.getParameter("descrizione")).thenReturn("La nostra Italian Pilsner punta sull’equilibrio tra la parte maltata, le tipiche note di crosta di pane e miele, e una luppolatura fatta di sole varietà mitteleuropee. Come il vento del Garda dal quale prende il nome, le sue note floreali ed erbacee enfatizzate da un leggero dry hopping, ti trasporteranno in una nuova esperienza da bere. Tu spiega le vele, il resto lo farà lei!");
		when(request.getParameter("quantita")).thenReturn("50");
		when(request.getParameter("prezzo")).thenReturn("4.05");
		when(request.getParameter("image")).thenReturn("https://www.birraimpavida.com/wp-content/uploads/2020/11/Peler.webp");

		servlet.doPost(request,response);
		verify(session).setAttribute("errorFormato", false);
		verify(response).sendRedirect("http://localhost:8080/webAppTest_war/gestioneCatalogo.jsp");
	}


}