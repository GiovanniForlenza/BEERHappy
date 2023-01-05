<%@ page import="java.util.Collection" %>
<%@ page import="entity.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: for_g
  Date: 02/01/2023
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Utente utente = (Utente) session.getAttribute("utente");

%>

<html>
<head>
	<title>Home Page</title>
</head>
<body>
	<h1>HomePage</h1>	<br>
	<p>Ciao <%= utente.getNome()%></p>
	<a href="profilo.jsp">Profilo</a>	<br>

	<ul>
		<li><a href="catalogo.jsp">catalogo</a></li>
		<li><a href="catalogoNovita.jsp">novita</a></li>
		<li><a>chi siamo</a></li>
	</ul>

</body>
</html>
