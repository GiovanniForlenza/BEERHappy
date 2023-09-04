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
		response.sendRedirect("login.jsp");
		return;
	}

%>
<html>
<head>
	<title>Indirizzo</title>
</head>

<script type="text/javascript">
	function controllo(){
        let via, civico, citta, cap, telefono;

        via = document.getElementById("inputAddress").value
        civico = document.getElementById("inputCIVICO").value
        citta = document.getElementById("inputCity").value
        cap = document.getElementById("inputCap").value
        telefono = document.getElementById("inputTelefono").value

		if(controlLetteralVIA(via) && controlCIVICO(civico) && controlLetteralCITTA(citta)
		&& controlCAP(cap) && controlCell(telefono)) {
            document.modulo.submit();
		}
	}
</script>
<body>

<%@ include file="navBarStore.jsp"%>
<div class="container my-5">
	<h1 class="mb-4">Aggiungi Indirizzo</h1>
	<form method="post" action="AggiuntaIndirizzoServlet" name="modulo">
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
				<span id="messageLetterVia">
				</span>
		</div>
		<div class="form-group">
			<label for="inputCIVICO">Civico</label>
			<input
					type="number"
					class="form-control"
					id="inputCIVICO"
					placeholder="Inserisci il numero civico"
					name="civico"
					required
			/>
		</div>
		<div class="form-group">
				<span id="messageCIVICO">
				</span>
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
				<span id="messageLetterCITTA">
				</span>
		</div>
		<div class="form-group">
			<label for="inputCap">CAP</label>
			<input
					type="number"
					class="form-control"
					id="inputCap"
					placeholder="Inserisci il tuo CAP"
					name="cap"
					required
			/>

		</div>
		<div class="form-group">
				<span id="messageCAP">
				</span>
		</div>
		<div class="form-group">
			<label for="inputTelefono">Telefono</label>
			<input
					type="text"
					class="form-control"
					id="inputTelefono"
					placeholder="Inserisci il tuo numero di telefono"
					name="telefono"
					required
			/>
		</div>
		<div class="form-group">
				<span id="messageCell">
				</span>
		</div>
		<input type="button" class="btn btn-primary" value="Aggiungi Indirizzo" onclick="controllo()">
		<!-- <input type="submit" class="btn btn-primary" value="Aggiungi Indirizzo"> -->
	</form>
</div>

	<script type="text/javascript" src="javaScript/Controllo.js"></script>

</body>
</html>
