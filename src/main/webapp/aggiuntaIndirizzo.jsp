<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 19/01/23
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Indirizzo</title>
</head>
<body>

	<form method="post" action="AggiuntaIndirizzoServlet">
		<p>citta </p><input name="citta" type="text">
		<p>via </p><input name="via" type="text">
		<p>civico </p><input name="civico" type="text">
		<p>cap </p><input name="cap" type="text">
		<p>telefono </p><input name="telefono" type="text">

		<input type="reset" value="reset">
		<input type="submit" value="salva">
	</form>

</body>
</html>
