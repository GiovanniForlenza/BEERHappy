<%--
  Created by IntelliJ IDEA.
  User: for_g
  Date: 16/12/2022
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profilo</title>
</head>
<body>
    <h1>registrati</h1>
    <form method="post" action="RegistrazioneServlet">
        <input type="text" name="nome" placeholder="nome">  <br>
        <input type="text" name="cognome" placeholder="cognome">    <br>
        <input type="email" name="e-mail" placeholder="e-mail">  <br>
        <input type="password" name="password" placeholder="password"> <br>
        <input type="password" name="rePassword" placeholder="conferma password">   <br>
        <input type="submit">
    </form>

    <h1>accedi</h1>
    <form method="post" action="AccedereServlet">
        <input type="email" name="e-mail" placeholder="Enter e mail"> <br>
        <input type="password" name="password" placeholder="Enter password"> <br>
        <input type="submit">
        <input type="reset">
    </form>
</body>
</html>
