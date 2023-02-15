<%@ page import="entity.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Ordine" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="entity.Stato" %>
<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 02/02/23
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("login.jsp");
		return;
	}

	Utente utente = (Utente) request.getSession().getAttribute("utente");
	ArrayList<Ordine> ordini = utente.getOrdini();
%>

<html>
<head>
	<title>OrderPAGE</title>
</head>
<body>

	<%@ include file="navBarStore.jsp"%>

	<div class="container mt-5">
		<h1>Ordini</h1>
		<table class="table mt-3">
			<thead>
			<tr>
				<th>ID ordine</th>
				<th>Data</th>
				<th>Indirizzo di spedizione</th>
				<th>Totale</th>
				<th>Stato</th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
		<%
			if(ordini == null || ordini.size() < 1){
				%>
			<p>non sono presenti ordini</p>
		<%
			}
			else{
			//stampa ordini
			for(int i = 0; i < ordini.size(); i++){
				Stato stato=ordini.get(i).getStato();
		%>
			<tr>
				<td>#<%=ordini.get(i).getIdOrdine()%></td>
				<td><%=ordini.get(i).getDataOrdine()%></td>
				<td><%="Italy, " + ordini.get(i).getCitta() + ", " + ordini.get(i).getVia() + " "+ ordini.get(i).getCivico()%></td>
				<td>&euro; <%=ordini.get(i).getPrezzo()%></td>
				<td><%=ordini.get(i).getStato().name()%></td>
				<%if(stato.name().equals(Stato.inConsegna.name()) || stato.name().equals(Stato.inoltrato.name())){%>
					<td><a href=<%=response.encodeURL("AnnullaOrdineServlet?ordineID="+ordini.get(i).getIdOrdine())+""%>>Annulla Ordine</a></td>
				<%}%>
			</tr>
		<%
				}
			}
		%>
			</tbody>
		</table>
	</div>

</body>
</html>
