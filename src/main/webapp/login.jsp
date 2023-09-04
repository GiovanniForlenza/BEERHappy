<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 14/02/23
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


	<script type="text/javascript">
        function controllo(){
            let str = document.getElementById("email").value;

            if(controlMail(str) === true)
                document.modulo.submit();

		}
	</script>

</head>
<body>

<div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
	<form class="border p-3 rounded" style="width: 35%;" method="POST" action="AccedereServlet" name="modulo">
		<h3 class="text-center mb-3">Accedi</h3>
			<div class="mb-3">
				<label for="email" class="form-label">Indirizzo email</label>
				<input type="email" class="form-control" id="email" name="e-mail" placeholder="Inserisci la tua email" required>
				<span id="messageEmail">
				</span>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Inserisci la tua password" required>
			</div>
			<%
				Boolean error = (Boolean) request.getSession().getAttribute("email_password");
				if(error != null){
					request.getSession().removeAttribute("email_password");
			%>
			<span style="color: red">
					E-mail o Password non corrette
				</span>
			<%
				}
			%>
			<div class="mb-3">
				<a href="recuperoPassword.jsp" >Password dimenticata</a>
			</div>
			<div class="mb-3">
				Non sei ancora registrato al sito <a href="signup.jsp" >Registrati</a>
			</div>
			<div class="d-flex justify-content-center">
				<input type="button" class="btn btn-primary"  value="Accedi" onclick="controllo()">
			</div>
	</form>
</div>

<script type="text/javascript" src="javaScript/Controllo.js"></script>
</body>
</html>
