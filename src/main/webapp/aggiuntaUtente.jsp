<%@ page import="model.ModelSecurity" %>
<%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 13/02/2023
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtenteBO");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("login.jsp");
		return;
	}
%>
<html>
<head>
	<title>Aggiunta utente</title>
</head>
<body>
<%@include file="navBarBO.jsp"%>

<div class="container">
	<div class="border p-3 rounded" style="width: 35%;">
		<form method="post" action="AggiuntaUtenteBOServlet">
			<p>E-mail</p>
			<input type="text" name="email" autocomplete="off" required>  <br>
			<p>Password</p>
			<input type="password" name="password" value=<%=ModelSecurity.generaPassword()%>>
			<p>Ruoli</p>
			<p>Gestore catalogo<input type="checkbox" name="ruolo" value="Gestore catalogo"></p>
			<p>Gestore ordini<input type="checkbox" name="ruolo" value="Gestore ordini"></p>
			<input class="btn btn-primary" type="submit" value="Salva"> <br>
			<a class="btn btn-secondary" href="gestioneUtenti.jsp">Annulla</a>
		</form>
	</div>
</div>

</body>
</html>
