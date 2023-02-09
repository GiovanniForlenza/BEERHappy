<%@ page import="entity.Prodotto" %>
<%@ page import="entity.Carrello" %>
<%@ page import="com.example.webapptest.GestioneCarrelloServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 24/01/23
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	Prodotto prodotto = (Prodotto) session.getAttribute("prodotto");
%>

<html>
<head>
	<title><%=prodotto.getNome()%></title>
</head>
<body>

	<%
		if (flag == null){
	%>
			<%@ include file="navBarGuest.jsp"%>
	<%
		}
		else{
	%>
			<%@ include file="navBarStore.jsp"%>
	<%
		}
	%>

	<h2><%= prodotto.getNome()%></h2>
	<h3><%= prodotto.getBirrificio()%></h3>
	<h3><%= prodotto.getFormato()%></h3>
	<p><%= prodotto.getDescrizione()%></p>
	<h3><%= prodotto.getPrezzo()%>€</h3>
	<p>Quantita disponibile: <%=prodotto.getQuantitaDisp()%></p>

	<%
		request.getSession().setAttribute("nome", prodotto.getNome());
		request.getSession().setAttribute("birrificio", prodotto.getBirrificio());
		request.getSession().setAttribute("formato", prodotto.getFormato());
		request.getSession().setAttribute("action", "addCart");
	%>

	<form action="AggiuntaProdottoCarrelloServlet" method="post">
		<input type="number" name="quantity" value="1" min="1" max="<%=prodotto.getQuantitaDisp()%>" required>
		<input type="submit" value="Aggiungi al carrello">
	</form>


</body>
</html>
