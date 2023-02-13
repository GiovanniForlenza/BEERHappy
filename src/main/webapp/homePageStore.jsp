<%@ page import="java.util.Collection" %>
<%@ page import="entity.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 02/01/2023
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	// Check user credentials
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("accesso.jsp");
		return;
	}

	Utente utente = (Utente) session.getAttribute("utente");

	if(request.getSession().getAttribute("carrello")!=null){
		response.sendRedirect("");
	}
%>
<html>
<head>
	<title>Home Page</title>
</head>
<body>
	<%@ include file="navBarStore.jsp"%>
	<p>Ciao <%= utente.getNome()%></p>
</body>
</html>
