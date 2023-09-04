<%@ page import="model.bean.UtenteBO" %>
<%@ page import="model.bean.Ordine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.bean.Stato" %><%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 10/02/2023
  Time: 03:08
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
	else if(flag.booleanValue() ) {
		UtenteBO utenteBO = (UtenteBO) session.getAttribute("utenteBO");
		int ruolo = utenteBO.getRuolo();
		if(ruolo==1)
			response.sendRedirect("selezioneRuolo.jsp");
	}
	ArrayList<Ordine> ordini=(ArrayList<Ordine>) session.getAttribute("ordini");
%>
<html>
<head>
	<title>Gestione ordini</title>

</head>
<body>
<%@ include file="navBarBO.jsp"%>

<div class="container">
	<h1 class="text-center mt-5">Gestione ordini</h1>
	<table class="table mt-3">
		<thead>
		<tr>
			<th>Ordine id</th>
			<th>Prezzo</th>
			<th>Data</th>
			<th>Indirizzo</th>
			<th>Stato</th>
			<th></th>
		</tr>
		</thead>
		<%
			for(int i=0; i < ordini.size(); i++){
				Ordine ordine = ordini.get(i);
		%>
		<form method="post" action="modificaStatoOrdine.jsp">
			<tbody>
			<tr>
				<input type="hidden" name="idOrdine" value="<%=ordine.getIdOrdine()%>">
				<td><%=ordine.getIdOrdine()%></td>
				<input type="hidden" name="prezzo" value="<%=ordine.getPrezzo()%>">
				<td><%=ordine.getPrezzo()%></td>
				<input type="hidden" name="data" value="<%=ordine.getDataOrdine()%>">
				<td><%=ordine.getDataOrdine()%></td>
				<input type="hidden" name="via" value="<%=ordine.getVia()%>">
				<input type="hidden" name="citta" value="<%=ordine.getCitta()%>">
				<input type="hidden" name="civico" value="<%=ordine.getCivico()%>">
				<input type="hidden" name="telefono" value="<%=ordine.getTelefono()%>">
				<td><%=ordine.getCitta()+" "+ordine.getVia()+" "+ordine.getCivico()+" "+ordine.getTelefono()%></td>
				<input type="hidden" name="stato" value="<%=ordine.getStato()%>">
				<td><%=ordine.getStato()%></td>

				<%if(!(ordine.getStato().equals(Stato.annullato))&&!(ordine.getStato().equals(Stato.consegnato))){%>
				<td><input class="btn btn-primary" type="submit" value="Modifica"></td>
				<%
					}
				%>

			</tr>
			</tbody>
		</form>

		<%
			}
		%>
	</table>

</div>
</body>
</html>