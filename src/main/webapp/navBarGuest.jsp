<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 06/02/23
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
	<title>NavBarGUEST</title>
	<!-- Include CSS e JavaScript di Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="collapse navbar-collapse d-flex content-center" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<p class="navbar-brand">BEerHAPPY</p>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link" href="homePage.jsp">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="catalogo.jsp">Catalogo</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="carrello.jsp">Carrello</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">chi siamo</a>
				</li>
				<li class="nav-item">
					<%@ include file="ricerca.jsp"%>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link" href="login.jsp">Accedi</a>
				</li>
			</ul>
		</div>
	</nav>
</body>
</html>
