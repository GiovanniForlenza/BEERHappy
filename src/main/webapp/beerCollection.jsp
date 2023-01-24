<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: for_g
  Date: 05/01/2023
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>

<%
	Collection<Prodotto> birra = (Collection<Prodotto>) request.getAttribute("birre");
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
		Iterator<?> it = birra.iterator();
		while(it.hasNext() && i < birra.size()){
			Prodotto prodotto = (Prodotto) it.next();
%>

<ol>
	<li><%=prodotto.getNome()%></li>
	<li>Birrificio: <%=prodotto.getBirrificio()%></li>
	<li>Descrizione prodotto: <%=prodotto.getDescrizione()%></li>
	<li>Formato: <%=prodotto.getFormato()%></li>
	<li>$ <%=prodotto.getPrezzo()%></li>
	<a href="<%= response.encodeURL("DettagliProdottoServlet?nome=" + prodotto.getNome() +
	"&birrificio=" + prodotto.getBirrificio() + "&formato=" + prodotto.getFormato())%>">dettagli</a>
</ol>

<%
		i++;
	}
}else { %>
<h3>Al momento non ci sono prodotti inseriti</h3>
<% 	}%>

</body>
</html>
