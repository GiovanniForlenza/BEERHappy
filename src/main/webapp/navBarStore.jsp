<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 06/02/23
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<style>
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333333;
    }

    li {
        float: left;
    }

    li a {
        display: block;
        color: white;
        text-align: center;
        padding: 16px;
        text-decoration: none;
    }

    li a:hover {
        background-color: #111111;
    }
</style>
<head>
	<title>navBAR</title>
</head>
<body>
	<ul class="horizontal">
		<li><a href="homePageStore.jsp">HOME</a></li>
		<li><a href="profilo.jsp">Profilo</a></li>
		<li><a href="catalogo.jsp">Catalogo</a></li>
		<li><a href="catalogoNovita.jsp">Novita</a></li>
		<li><a href="carrello.jsp">Carrello</a></li>
		<li><a href="ordinePagina.jsp">Ordini</a></li>
		<li><a href="LogoutServlet">Logout</a></li>
	</ul>
</body>
</html>
