<%@ page import="java.util.Collection" %>
<%@ page import="entity.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: for_g
  Date: 02/01/2023
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	// Check user credentials
	Boolean flag = (Boolean) session.getAttribute("accesso");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("accesso.jsp");
		return;
	}

	Utente utente = (Utente) session.getAttribute("utente");
%>
<html>
<head>
	<title>Home Page</title>
</head>
<body>
	<h1>HomePage</h1>	<br>
	<p>Ciao <%= utente.getNome()%></p>

	<ul>
		<li><a href="profilo.jsp">Profilo</a></li>
		<li><a href="catalogo.jsp">catalogo</a></li>
		<li><a href="catalogoNovita.jsp">novita</a></li>
		<li><a>chi siamo</a></li>
	</ul>

	<%@ include file="beerCollection.jsp" %>

</body>
</html>
