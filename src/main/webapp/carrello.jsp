<%@ page import="entity.Carrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 25/01/23
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Carrello carrello = (Carrello) session.getAttribute("carrello");
%>
<html>
<head>
	<title>Carrello</title>
</head>
<body>

	<%
		if(carrello != null){
			ArrayList<Prodotto> prodottiNelCarrello = carrello.getProdotti();
			//stampa prodotti
			for (Prodotto prodotto : prodottiNelCarrello){
	%>
				<p><%=prodotto.getNome()%></p>
				<p><%=prodotto.getQuantita()%></p>
				<p><%=prodotto.getFormato()%></p>
				<p><%=prodotto.getPrezzo()%></p>
				<a href="<%=response.encodeURL("GestioneCarrelloServlet?action=rimuoviProdotto&nome=" + prodotto.getNome()
				+"&birrificio=" + prodotto.getBirrificio() + "&formato=" + prodotto.getFormato()+ "&quantita=" + prodotto.getQuantita())%>">rimuovi</a>
	<%
			}
			if (prodottiNelCarrello.size() > 0) {
	%>
				<a href="<%=response.encodeURL("GestioneCarrelloServlet?action=checkout")%>">Continue to checkout</a>
	<%		}
			else{
				%>
			<p>il carrello è vuoto</p>
	<%
			}
		}
		else{
	%>
			<p>il carrello è vuoto</p>
	<%
		}
	%>

</body>
</html>
