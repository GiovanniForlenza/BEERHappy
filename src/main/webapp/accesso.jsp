<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 16/12/2022
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login PAGE</title>

    <style>
        .messageEmailError{
            display: none;
        }
    </style>

</head>
<body>
    <h1>registrati</h1>
    <form method="post" action="RegistrazioneServlet">
        <labe>Nome</labe>   <br>
        <input type="text" name="nome" autocomplete="off" required>  <br>
        <p class="messageEmailError">email gia presente</p>

        <labe>Cognome</labe>    <br>
        <input type="text" name="cognome" autocomplete="off" required>    <br>

        <labe>Email</labe>  <br>
        <input type="email" name="e-mail" required>  <br>

        <labe>Password</labe>   <br>
        <input type="password" name="password" required> <br>

        <labe>Conferma Password</labe>  <br>
        <input type="password" name="rePassword" required>   <br>

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
