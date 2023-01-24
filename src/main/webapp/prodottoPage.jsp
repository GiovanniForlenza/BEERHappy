<%@ page import="entity.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 24/01/23
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	Prodotto prodotto = (Prodotto) session.getAttribute("prodotto");
%>
<html>
<head>
	<title>Title</title>
</head>
<body>

	<h2><%= prodotto.getNome()%></h2>
	<h3><%= prodotto.getBirrificio()%></h3>
	<h3><%= prodotto.getFormato()%></h3>
	<p><%= prodotto.getDescrizione()%></p>
	<h3><%= prodotto.getPrezzo()%></h3>



	<button onclick="" >Aggiungi al carrello</button>
</body>
</html>
