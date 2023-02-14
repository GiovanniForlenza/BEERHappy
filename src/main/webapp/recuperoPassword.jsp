<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 14/02/23
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>recupero password</title>
</head>
<body>
  <h1>Recupero password</h1>
  <form method="post" action="RecuperoPasswordServlet">
    <labe>Nome</labe>   <br>
    <input type="text" name="email" autocomplete="off" required>  <br>
    <input type="submit">
    <p class="messageEmailError">email non presente</p>
  </form>
</body>
</html>
