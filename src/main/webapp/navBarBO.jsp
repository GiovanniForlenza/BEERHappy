<%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 13/02/2023
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>NavBAR</title>
</head>

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

<body>
<ul class="horizontal">
	<li><a href="selezioneRuolo.jsp">Cambia ruolo</a></li>
</ul>
<br>
</body>
</html>