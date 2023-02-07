<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 21/12/2022
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
%>
<html>
<head>
    <title>Catalogo Novita</title>
</head>
<body>
	<%
		if (flag == null){
	%>
			<%@ include file="navBarGuest.jsp"%>
	<%
		}
		else{
	%>
			<%@ include file="navBarStore.jsp"%>
	<%
		}
	%>
	<h2>Novita</h2>	<br>
	<h3>PRODOTTI</h3>

</body>
</html>
