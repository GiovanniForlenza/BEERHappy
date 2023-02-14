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
	<%@ include file="navBarStore.jsp"%>

	<div class="container my-5">
		<h1 class="mb-4">Modifica Password</h1>
		<form action="CambioPasswordServlet" method="post">
			<div class="form-group">
				<label for="old">Password Attuale</label>
				<input
						type="password"
						class="form-control"
						id="old"
						placeholder="Inserisci la tua password attuale"
						name="old"
				/>
			</div>
			<%
				if(request.getSession().getAttribute("notChack") != null){
					request.getSession().removeAttribute("notChack");
			%>
			<h5 style="color: red">Password ineserita non valida</h5>
			<%
				}
			%>
			<br>
			<div class="form-group">
				<label for="password">Nuova Password</label>
				<input
						type="password"
						class="form-control"
						id="password"
						placeholder="Inserisci la tua nuova password"
						name="password"
						onkeyup="check()"
				/>
			</div>
			<div class="form-group">
				<label for="confirm_password">Conferma Password</label>
				<input
						type="password"
						class="form-control"
						id="confirm_password"
						placeholder="Conferma la tua nuova password"
						name="confirm_password"
						onkeyup="check()"
				/>
			</div>
			<span id="message"></span><br>
			<input type="submit" class="btn btn-primary" id="submit" disabled="disabled" value="Modifica Password">
		</form>
	</div>




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
