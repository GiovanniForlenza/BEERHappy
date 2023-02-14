<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 19/01/23
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	// Check user credentials
	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("accesso.jsp");
		return;
	}

%>
<html>
<head>
	<title>Indirizzo</title>
</head>
<body>

<%@ include file="navBarStore.jsp"%>
<div class="container my-5">
	<h1 class="mb-4">Aggiungi Indirizzo</h1>
	<form method="post" action="AggiuntaIndirizzoServlet">
		<div class="form-group">
			<label for="inputAddress">Indirizzo</label>
			<input
					type="text"
					class="form-control"
					id="inputAddress"
					placeholder="Inserisci il tuo indirizzo"
					name="via"
					required
			/>
		</div>
		<div class="form-group">
			<label for="inputState">Civico</label>
			<input
					type="number"
					class="form-control"
					id="inputState"
					placeholder="Inserisci il numerocivico"
					name="civico"
					required
			/>
		</div>
		<div class="form-group">
			<label for="inputCity">Città</label>
			<input
					type="text"
					class="form-control"
					id="inputCity"
					placeholder="Inserisci la tua città"
					name="citta"
					required
			/>
		</div>
		<div class="form-group">
			<label for="inputZip">CAP</label>
			<input
					type="text"
					class="form-control"
					id="inputZip"
					placeholder="Inserisci il tuo CAP"
					name="cap"
					required
			/>
		</div>
		<div class="form-group">
			<label for="inputZip">Telefono</label>
			<input
					type="text"
					class="form-control"
					id="inputTelefono"
					placeholder="Inserisci il tuo numero di telefono"
					name="telefono"
					required
			/>
		</div>
		<input type="submit" class="btn btn-primary" value="Aggiungi Indirizzo">
	</form>
</div>

</body>
</html>
