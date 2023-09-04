<%@ page import="model.bean.Carrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.bean.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 25/01/23
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	Carrello carrello = (Carrello) session.getAttribute("carrello");
%>
<html>
<head>
	<title>Carrello</title>
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

	<div class="container my-5">
		<h1 class="text-center">Carrello</h1>
		<table class="table table-striped">
			<thead>
			<tr>
				<th>Prodotto</th>
				<th>Prezzo</th>
				<th>Quantità</th>
				<th>Totale</th>
				<th></th>
			</tr>
			</thead>
	<%
			if(carrello == null || carrello.getProdotti() == null) {
	%>
				<p>il carrello è vuoto</p>
	<%
			}
			else{
			float totale = 0;
			ArrayList<Prodotto> prodottiNelCarrello = carrello.getProdotti();
			for (Prodotto prodotto : prodottiNelCarrello){

	%>
			<tbody>
			<tr>
				<td><%=prodotto.getNome()%></td>
				<td>€ <%=prodotto.getPrezzo()%></td>
				<td><%=prodotto.getQuantita()%></td>
				<td>€ <%=Math.round(prodotto.getQuantita() * prodotto.getPrezzo() * 100) / 100%></td>
				<td><a href="<%=response.encodeURL("GestioneCarrelloServlet?action=rimuoviProdotto&nome=" + prodotto.getNome()
				+"&birrificio=" + prodotto.getBirrificio() + "&formato=" + prodotto.getFormato()+ "&quantita=" + prodotto.getQuantita())%>">rimuovi</a></td>
			</tr>
	<%
				totale += (Math.round(prodotto.getQuantita() * prodotto.getPrezzo() * 100) / 100);
			}

	%>


	<%
			if (prodottiNelCarrello.size() > 0) {
	%>
			<tr>
				<td colspan="4" class="text-right">Subtotale</td>
				<td>€ <%=totale%></td>
			</tr>
			</tbody>
		</table>
		<div class="d-flex justify-content-end">
			<a href="catalogo.jsp" class="btn btn-primary mr-3">Continua lo shopping</a>
			<a href="<%=response.encodeURL("GestioneCarrelloServlet?action=checkout")%>" class="btn btn-success">Procedi al checkout</a>
		</div>
	</div>

	<%
				}
			else{
	%>
				<p>il carrello è vuoto</p>
	<%
				}
			}
	%>


</body>
</html>
