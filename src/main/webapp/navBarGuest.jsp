<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 06/02/23
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
	<title>NavBAR</title>
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

</head>
<body>
	<ul class="horizontal">
		<li><a href="accesso.jsp">Accedi / Registrati</a></li>
		<li><a href="catalogo.jsp">catalogo</a></li>
		<li><a href="catalogoNovita.jsp">novita</a></li>
		<li><a href="carrello.jsp">carrello</a></li>
		<li><a>chi siamo</a></li>
	</ul>
	<br>
</body>
</html>
