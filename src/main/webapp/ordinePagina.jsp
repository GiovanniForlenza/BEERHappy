<%@ page import="entity.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Ordine" %>
<%@ page import="entity.Prodotto" %>
<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 02/02/23
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	//TODO fai i controlli sulla pagina
	Utente utente = (Utente) request.getSession().getAttribute("utente");
	ArrayList<Ordine> ordini = utente.getOrdini();
%>

<html>
<head>
	<title>OrderPAGE</title>
</head>
<body>

	<%@ include file="navBarStore.jsp"%>

	<h2>Ordini</h2>
	<%
		for(int i = 0; i < ordini.size(); i++){
			for(int j = 0; j < ordini.get(i).getProdotti().size(); j++){
				ArrayList<Prodotto> prodotti = ordini.get(i).getProdotti();
	%>
				<p>nome prodotto: <%=prodotti.get(j).getNome()%></p>
				<p>quantita selezionata: <%=prodotti.get(j).getQuantita()%></p>
				<p>prezzo prodotto: <%=prodotti.get(j).getPrezzo()%>€</p>
	<%		}
	%>
			<p>Totale ordine: <%=ordini.get(i).getPrezzo()%>€</p>
			<p>numero ordine: <%=ordini.get(i).getIdOrdine()%></p>
			<p>Stato: <%=ordini.get(i).getStato().name()%>
			<p>---------------------------------</p>
	<%	}
	%>


</body>
</html>
