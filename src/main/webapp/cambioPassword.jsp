<%@ page import="entity.Utente" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 07/02/23
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("accesso.jsp");
		return;
	}
%>
<html>
<head>
	<title>CambioPassword</title>
</head>
<body>
	<form action="CambioPasswordServlet" method="post">
		<label>Enter password
			<input type="password" name="old" id="old">
		</label>
		<%
			if(request.getSession().getAttribute("notChack") != null){
				request.getSession().removeAttribute("notChack");
		%>
				<p>La password ineserita non valida</p>
		<%
			}
		%>
		<br>

		<label>Enter new password
			<input type="password" name="password" id="password" onkeyup="check()">
		</label><br>
		<label>Confirm new password
			<input type="password" name="confirm_password" id="confirm_password" onkeyup="check()">
		</label>
		<span id="message"></span><br>
		<input type="submit" id="submit" disabled="disabled">
	</form>

	<script>
		var check = function() {
            if(document.getElementById('password').value == "" ||
                document.getElementById('confirm_password').value == ""){
                document.getElementById('submit').disabled = true;
			}
			else if (document.getElementById('password').value ==
				document.getElementById('confirm_password').value) {
				document.getElementById('message').style.color = 'green';
				document.getElementById('message').innerHTML = 'matching';
                document.getElementById('submit').disabled = false;
			} else {
				document.getElementById('message').style.color = 'red';
				document.getElementById('message').innerHTML = 'not matching';
                document.getElementById('submit').disabled = true;
			}
		}
	</script>

</body>
</html>
