<%@ page import="model.dao.UserModel" %>
<%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 13/02/2023
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	Boolean flag = (Boolean) session.getAttribute("accessoUtenteBO");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("login.jsp");
		return;
	}
%>
<html>
<head>
	<title>Aggiunta utente</title>

	<script type="text/javascript">
		function controllo (){
			let email = document.getElementById("email").value;
            let mex = document.getElementById("messageRuolo");
			let cataologo = document.getElementById("catalogo");
			let ordini = document.getElementById("ordini");

            if(cataologo.checked || ordini.checked){
                mex.innerText = "";
                if(emailBO(email)){
                    document.metodo.submit();
                }
			}
            else{
				mex.style.color = "red";
                mex.innerText = "Selezionare almeno un ruolo per poter procedere";
			}
		}

        function myFunction() {
            var x = document.getElementById("myInput");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }
	</script>

</head>
<body>
<%@include file="navBarBO.jsp"%>

	<div class="container">
		<div class="border p-3 rounded" style="width: 35%;">
			<form method="post" action="AggiuntaUtenteBOServlet" name="metodo">
				<p>E-mail</p>
				<input type="text" name="email" autocomplete="off" id="email" required>  <br>
				<p id="messageMail"></p>
				<p>Password</p>
				<input type="password" name="password" id="myInput" value=<%=UserModel.generaPassword()%>>
				<input type="checkbox" onclick="myFunction()">Show Password
				<p>Ruoli</p>
				<p>Gestore catalogo<input type="checkbox" name="ruolo" id="catalogo" value="Gestore catalogo" checked="checked"></p>
				<p>Gestore ordini<input type="checkbox" name="ruolo" id="ordini" value="Gestore ordini"></p>
				<span id="messageRuolo">
				</span>
				<input class="btn btn-primary" type="button" value="Salva" onclick="controllo()"> <br>
				<a class="btn btn-secondary" href="gestioneUtenti.jsp">Annulla</a>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="javaScript/Controllo.js"></script>

</body>
</html>
