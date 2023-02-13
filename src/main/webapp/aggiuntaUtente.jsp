<%@ page import="model.ModelSecurity" %>
<%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 13/02/2023
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aggiunta utente</title>
</head>
<body>
  <form method="post" action="AggiuntaUtenteBOServlet">
    <p><label>E-mail</label> </p>
    <input type="text" name="email" autocomplete="off" required>  <br>
    <p><label>Password</label> </p>
    <input type="password" name="password" value=<%=ModelSecurity.generaPassword()%>>
    <p><label>Ruoli</label> </p>
    Gestore catalogo<input type="checkbox" name="ruolo" value="Gestore catalogo">Gestore ordini<input type="checkbox" name="ruolo" value="Gestore ordini">
    <input type="submit" value="Salva"> <br>
  </form>
</body>
</html>
