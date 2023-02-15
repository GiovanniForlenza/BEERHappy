<%@ page import="entity.Utente" %>
<%@ page import="entity.Indirizzo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="control.RimozioneIndirizzoServlet" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 05/01/2023
  Time: 12:19
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

  Utente utente = (Utente) session.getAttribute("utente");


%>
<html>
<head>
	<title>Profile PAGE</title>
</head>
<body>

	<%@ include file="navBarStore.jsp"%>

	<div class="container">
		<h1 class="text-center mt-5">Profilo</h1>
		<form class="mt-5" name="nomeCognome" action="ModificaDatiServlet" method="post">
			<div class="form-group">
				<label for="nome">Nome</label>
				<input  type="text" class="form-control" id="nome" name="nome" disabled="true" value="<%= utente.getNome() %>">
			</div>
			<div class="form-group">
				<label for="cognome">Cognome</label>
				<input type="text" class="form-control" id="cognome" name="cognome" disabled="true" value="<%= utente.getCognome()%>">
			</div>
			<div class="form-group">
				<label for="email">E-mail</label>
				<input type="text" class="form-control" id="email" name="e-mail" disabled="true" value="<%= utente.getCognome()%>">
			</div>
			<!--
			TODO modifica profilo non funziona da sistemare
			<button class="btn btn-success" id="save" hidden="true">salva</button>
			<button class="btn btn-primary" id="button" >modifica</button>
			-->
			<a class="btn btn-primary float-right" href="cambioPassword.jsp">Cambia password</a>
		</form>
			<h3 class="mt-5">Indirizzi</h3>

		<form>
			<a class="btn btn-primary float-right" href="aggiuntaIndirizzo.jsp">Aggiungi indirizzo</a>
			<table class="table mt-3">
				<thead>
				<tr>
					<th>Città</th>
					<th>CAP</th>
					<th>Via</th>
					<th>Telefono</th>
					<th></th>
				</tr>
				</thead>
				<%
					if(utente.getIndirizzi() != null){
						if(utente.getIndirizzi().size() > 0){
							for(int i = 0; i < utente.getIndirizzi().size(); i++){
				%>
				<tbody>
				<tr>
					<td><%=utente.getIndirizzi().get(i).getCitta()%></td>
					<td><%=utente.getIndirizzi().get(i).getCap()%></td>
					<td><%=utente.getIndirizzi().get(i).getVia()%></td>
					<td><%=utente.getIndirizzi().get(i).getTelefono()%></td>
					<td><a href="<%= response.encodeURL("RimozioneIndirizzoServlet?indirizzoID="+
							utente.getIndirizzi().get(i).getID() + "")%>">rimuovi</a></td>
				</tr>
				</tbody>

				<%
							}
						}
					}
				%>
			</table>
		</form>
		<h3 class="mt-5">Carte</h3>
		<form>
			<a class="btn btn-primary float-right" href="aggiuntaCarta.jsp">Aggiungi carta</a>
			<table class="table mt-3">
				<thead>
				<tr>
					<th>Titolare</th>
					<th>Numero carta</th>
					<th>Scadenza</th>
					<th></th>
				</tr>
				</thead>
				<%
					if(utente.getCarte() != null){
						if(utente.getCarte().size() > 0){
							for(int i = 0; i < utente.getCarte().size(); i++){
				%>
				<tbody>
				<tr>
					<td><%=utente.getCarte().get(i).getIntestatario()%></td>
					<td><%=utente.getCarte().get(i).getnCata()%></td>
					<td><%=utente.getCarte().get(i).getDataScadenza()%></td>
					<td><a href="<%= response.encodeURL("RimozioneCartaServlet?cartaID="+ utente.getCarte().get(i).getId())%>">rimuovi</a></td>
				</tr>
				</tbody>

				<%
							}
						}
					}
				%>
			</table>
		</form>
		<a class="btn btn-danger float-right" href="<%= response.encodeURL("EliminazioneServlet?email="+utente.getEmail())%>">Elimina account</a>
	</div>

	<script type="text/javascript">
        function enable() {

			document.getElementById("nome").disabled = false;
			document.getElementById("cognome").disabled = false;

			let btn = document.getElementById("save").hidden = false;
			document.getElementById("button").hidden = true;

            btn.onclick = () => {
                btn.value = "modifica dati";
                document.nomeCognome.nome.disabled = true;
                document.nomeCognome.cognome.disabled = true;
                //save data end refresh page
				document.location.href = "ModificaDatiServlet";
            };

        }
	</script>



</body>
</html>
