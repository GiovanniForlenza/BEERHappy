<%@ page import="entity.Prodotto" %>
<%@ page import="entity.Carrello" %>
<%@ page import="control.GestioneCarrelloServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 24/01/23
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	Prodotto prodotto = (Prodotto) session.getAttribute("prodotto");
%>

<html>
<head>
	<title><%=prodotto.getNome()%></title>
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

	<!-- Header -->
	<header class="jumbotron my-4">
		<h1 class="display-3"><%= prodotto.getNome()%></h1>
		<p class="lead">Questa birra ha un gusto unico ed è molto apprezzata dai veri intenditori di birra.</p>
	</header>

	<!-- Prodotto -->
	<div class="container">
		<div class="row">
			<!-- Immagine -->
			<div class="col-md-6">
				<img src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
			</div>
			<!-- Descrizione -->
			<div class="col-md-6">
				<h3>Descrizione</h3>
				<p><%= prodotto.getDescrizione()%></p>
				<h3>Dettagli</h3>
				<ul>
					<li>Birrificio: <%= prodotto.getBirrificio()%></li>
					<li>Gradazione alcolica: 6,5%</li>
					<li>Formato disponibile: <%= prodotto.getFormato()%></li>
					<li>Quantita disponibile: <%= prodotto.getQuantitaDisp()%></li>
				</ul>
				<h3>Prezzo</h3>
				<p class="price"><%=prodotto.getPrezzo()%>€</p>
				<!-- Bottone acquista -->
				<form action="AggiuntaProdottoCarrelloServlet" method="post">
					<input type="number" name="quantity" value="1" min="1" max="<%=prodotto.getQuantitaDisp()%>" required>
					<input type="submit" class="btn btn-primary" value="Aggiungi al carrello">
				</form>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer class="py-5 bg-light">
		<div class="container">
			<p class="m-0 text-center">Copyright &copy; Birreria 2023</p>
		</div>
	</footer>

	<%
		request.getSession().setAttribute("nome", prodotto.getNome());
		request.getSession().setAttribute("birrificio", prodotto.getBirrificio());
		request.getSession().setAttribute("formato", prodotto.getFormato());
		request.getSession().setAttribute("action", "addCart");
	%>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
