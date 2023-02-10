<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 05/01/2023
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>

<%
	ArrayList<Prodotto> birra = (ArrayList<Prodotto>) request.getAttribute("birre");
	String error = (String)request.getAttribute("error");
	if(birra == null && error == null) {
		response.sendRedirect(response.encodeRedirectURL("./RichiestaBirreServlet"));
	}
%>

<html>
<head>
	<title>BEER Collection</title>
</head>
<body>

<%
	if(birra != null && birra.size() > 0){
		int i = 0;

		for(int j = 0; j < birra.size(); j++){
			Prodotto prodotto = birra.get(i);
%>

			<p><%=prodotto.getNome()%></p>
			<p>Birrificio: <%=prodotto.getBirrificio()%></p>
			<p>Descrizione prodotto: <%=prodotto.getDescrizione()%></p>
			<p>Formato: <%=prodotto.getFormato()%></p>
			<p><%=prodotto.getPrezzo()%> &euro;</p>
			<a href="<%= response.encodeURL("DettagliProdottoServlet?nome=" + prodotto.getNome() +
			"&birrificio=" + prodotto.getBirrificio() + "&formato=" + prodotto.getFormato())%>">dettagli</a>

<%
		i++;
		}
	}else { %>
			<h3>Al momento non ci sono prodotti inseriti</h3>
<%
	}
%>

</body>
</html>
