<%@ page import="entity.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Ordine" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="entity.ProdottoOrdinato" %><%--
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
	ArrayList<ProdottoOrdinato> prod = utente.getProdotti();
%>

<html>
<head>
	<title>OrderPAGE</title>
</head>
<body>

	<h2>Ordini</h2>
	<%
		for(int i = 0; i < ordini.size(); i++){
			for(int j = 0; j < ordini.get(i).getProdotti().size(); j++){
				ArrayList<ProdottoOrdinato> prodotti = ordini.get(i).getProdotti();
	%>
				<p><%=prodotti.get(j).getNome()%></p>
				<p><%=prodotti.get(j).getQuantitaSelezionata()%></p>
				<p><%=prodotti.get(j).getPrezzoProdotto()%></p>
	<%		}
	%>
			<p><%=ordini.get(i).getPrezzo()%></p>
			<p>numero ordine: <%=ordini.get(i).getIdOrdine()%></p>
			<p>---------------------------------</p>
	<%	}
	%>


</body>
</html>
