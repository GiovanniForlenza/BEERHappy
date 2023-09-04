<%@ page import="model.bean.Utente" %>
<%--
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
		response.sendRedirect("login.jsp");
		return;
	}
%>
<html>
<head>
	<title>CambioPassword</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<script type="text/javascript">
        function controllo(){
            if(isPassword()){
                document.modulo.submit();
			}
        }
	</script>

</head>
<body>
	<%@ include file="navBarStore.jsp"%>

	<div class="container my-5">
		<h1 class="mb-4">Modifica Password</h1>
		<form action="CambioPasswordServlet" method="POST" name="modulo">
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
					<h5 style="color: red">Password inserita non valida</h5>
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
						required
						onkeyup="check()"
				/>
			</div>
			<span id="messagePassword"></span><br>
			<div class="form-group">
				<label for="confirm_password">Conferma Password</label>
				<input
						type="password"
						class="form-control"
						id="confirm_password"
						placeholder="Conferma la tua nuova password"
						name="confirm_password"
						required
						onkeyup="check()"
				/>
			</div>
			<span id="message"></span><br>
			<input type="button" class="btn btn-primary" value="Modifica Password" onclick="controllo()">
		</form>
	</div>

	<script type="text/javascript" src="javaScript/ControlloPassword.js"></script>
	<script type="text/javascript" src="javaScript/Controllo.js"></script>

</body>
</html>
