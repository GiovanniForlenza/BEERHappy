<%--
  Created by IntelliJ IDEA.
  User: for_g
  Date: 05/01/2023
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Profile PAGE</title>
</head>
<body>
  <form>
    <p>nome: <input readonly="readonly" value="pinko"> </p>
    <p>cognome: <input readonly="readonly" value="pallino"></p>
    <p>email: <input readonly="readonly" value="pinko_pallino@gmail.com"></p>
    <button onclick="alert()">modifica dati</button>  <br> <br>

    <button onclick="alert()">modifica password</button> <br> <br>

    <button onclick="alert()">aggiungi indirizzo</button> <br>  <br>
    <input readonly="readonly" value="lista indirizzi">
    <p>indirizzo 1 <button>x</button></p>

    <button onclick="alert()">aggiungi carta</button> <br> <br>
    <input readonly="readonly" value="lista carte">
    <p>carta 1 <button>x</button> </p>

    <button>Elimina account</button>
  </form>
</body>
</html>
