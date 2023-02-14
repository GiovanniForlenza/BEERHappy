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

<div class="container my-5">
	<div class="row">
<%
	if(birra != null && birra.size() > 0){
		int i = 0;

		for(int j = 0; j < birra.size(); j++){
			Prodotto prodotto = birra.get(i);
%>
		<!-- Prodotti -->
		<div class="col-md-4">
			<div class="card mb-4">
				<img class="card-img-top" src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
				<div class="card-body">
					<h4 class="card-title"><%=prodotto.getNome()%></h4>
					<p class="card-text"><%=prodotto.getDescrizione()%></p>
					<a href="<%= response.encodeURL("DettagliProdottoServlet?nome=" + prodotto.getNome() +
				"&birrificio=" + prodotto.getBirrificio() + "&formato=" + prodotto.getFormato())%>" class="btn btn-primary">Dettagli</a>
				</div>
			</div>
		</div>
<%
		i++;
		}
	}else { %>
			<h3>Al momento non ci sono prodotti inseriti</h3>
<%
	}
%>
	</div>
</div>

	<!-- Footer -->
	<footer class="bg-light py-3">
		<div class="container">
			<p class="text-center">Copyright &copy; Birreria 2023</p>
		</div>
	</footer>
</body>
</html>
