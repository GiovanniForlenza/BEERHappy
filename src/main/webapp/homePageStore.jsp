<%@ page import="java.util.Collection" %>
<%@ page import="entity.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 02/01/2023
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	// Check user credentials
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("accesso.jsp");
		return;
	}
%>
<html>
<head>
	<title>Home Page</title>
</head>
<body>
	<%@ include file="navBarStore.jsp"%>

	<!-- Header -->
	<header class="jumbotron jumbotron-fluid text-center bg-primary text-white">
		<div class="container">
			<h1 class="display-4">Birreria</h1>
			<p class="lead">La migliore selezione di birre artigianali del mondo</p>
		</div>
	</header>

	<!-- Sezione prodotto -->
	<div class="container my-5">
		<h2 class="text-center">Birre in vendita</h2>
		<div class="row">
			<!-- Prodotto 1 -->
			<div class="col-md-4">
				<div class="card mb-4">
					<img class="card-img-top" src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
					<div class="card-body">
						<h4 class="card-title">Birra 1</h4>
						<p class="card-text">Descrizione birra 1</p>
						<a href="#" class="btn btn-primary">Acquista ora</a>
					</div>
				</div>
			</div>
			<!-- Prodotto 2 -->
			<div class="col-md-4">
				<div class="card mb-4">
					<img class="card-img-top" src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
					<div class="card-body">
						<h4 class="card-title">Birra 2</h4>
						<p class="card-text">Descrizione birra 2</p>
						<a href="#" class="btn btn-primary">Acquista ora</a>
					</div>
				</div>
			</div>
			<!-- Prodotto 3 -->
			<div class="col-md-4">
				<div class="card mb-4">
					<img class="card-img-top" src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
					<div class="card-body">
						<h4 class="card-title">Birra 3</h4>
						<p class="card-text">Descrizione birra 3</p>
						<a href="#" class="btn btn-primary">Acquista ora</a>
					</div>
				</div>
			</div>
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
