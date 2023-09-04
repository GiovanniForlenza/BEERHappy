<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 14/02/23
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Registrazione</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


	<script type="text/javascript">
		function controllo(){

            let nome, cognome, indirizzo, password, confermaP;
            nome = document.getElementById("name").value;
            cognome = document.getElementById("cognome").value;
            indirizzo = document.getElementById("email").value;
            password = document.getElementById("password").value;
			confermaP = document.getElementById("confirm_password").value;

			if(controlLetteralName(nome) && controlLetteralCognome(cognome) &&
				controlMail(indirizzo)  && isPassword(password) && checkPassword(confermaP)){
                document.modulo.submit();
			}
		}
	</script>
</head>
	<body>
	<div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
		<form class="border p-3 rounded" style="width: 35%;" method="POST" action="RegistrazioneServlet" name="modulo">
			<h3 class="text-center mb-3">Registrazione</h3>

			<div class="mb-3">
				<label for="name" class="form-label">Nome</label>
				<input type="text" class="form-control" id="name" name="nome" placeholder="Inserisci il tuo nome">
			</div>
			<div class="mb-3">
				<label for="name" class="form-label">Cognome</label>
				<input type="text" class="form-control" id="cognome" name="cognome" placeholder="Inserisci il tuo cognome">
			</div>
			<div class="mb-3">
				<span id="messageLetter">
				</span>
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Indirizzo email</label>
				<input type="email" class="form-control" id="email" name="e-mail" placeholder="Inserisci la tua email">
				<span id="messageEmail">
				</span>
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">Password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Inserisci la tua password" required onkeyup="isPassword()">
				<span id="messagePassword">
				</span>
			</div>
			<div class="mb-3">
				<label for="confirm_password" class="form-label">Conferma Password</label>
				<input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="Conferma la tua password" required onkeyup="check()">
				<span id="message"></span>
			</div>
			<br>
			<%
				Boolean error = (Boolean) request.getSession().getAttribute("error_mail");
				if(error != null){
					request.getSession().removeAttribute("error_mail");
			%>
			<span style="color: red">
				E-mail già presente
			</span>
			<%
				}
			%>
			<div class="d-flex justify-content-center">
				<input type="button" class="btn btn-primary" value="Registrati" onclick="controllo()">
			</div>
		</form>
	</div>

	<script type="text/javascript" src="javaScript/ControlloPassword.js"></script>
	<script type="text/javascript" src="javaScript/Controllo.js"></script>

	</body>
</html>
