<%@ page import="model.bean.Utente" %>
<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 05/01/2023
  Time: 12:19
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
				<label for="name">Nome</label>
				<input  type="text" class="form-control" id="name" name="nome" disabled="true" value="<%= utente.getNome() %>">
			</div>
			<div class="form-group">
				<label for="cognome">Cognome</label>
				<input type="text" class="form-control" id="cognome" name="cognome" disabled="true" value="<%= utente.getCognome()%>">
			</div>
			<div>
				<span id="messageLetter">
				</span>
			</div>
			<div class="form-group">
				<label for="email">E-mail</label>
				<input type="text" class="form-control" id="email" name="e-mail" disabled="true" value="<%= utente.getEmail()%>">
			</div>

			<input class="btn btn-success" id="save" hidden="true" type="submit" value="salva">

		</form>

		<button class="btn btn-primary" id="button" onclick="enable()">modifica</button>

		<a class="btn btn-primary" href="cambioPassword.jsp">modifica password</a>

		<h3 class="mt-5">Indirizzi</h3>

		<form>
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
					<td>
						<a data-toggle="modal" data-target="#exampleModalAddress" style="color: steelblue">rimuovi</a>
					</td>

					<div class="modal fade" id="exampleModalAddress" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabelAddress">Eliminazione indirizzo</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p class="popup-content">Stai per eliminare l'idirizzo. Ne sei sicuro ?</p>
								</div>
								<div class="modal-footer">
									<a class="btn btn-danger" href="<%= response.encodeURL("RimozioneIndirizzoServlet?indirizzoID=" + utente.getIndirizzi().get(i).getID() + "")%>">Conferma</a>
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
								</div>
							</div>
						</div>
					</div>
				</tr>
				</tbody>
				<%
							}
						}
					}
				%>
			</table>
		</form>

		<a class="btn btn-primary float-right" href="aggiuntaIndirizzo.jsp">Aggiungi indirizzo</a>

		<h3 class="mt-5">Carte</h3>
		<form>
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
					<td>
						<a data-toggle="modal" data-target="#exampleModalCard" style="color: steelblue">rimuovi</a>
					</td>

					<div class="modal fade" id="exampleModalCard" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabelCard">Eliminazione carta</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p class="popup-content">Stai per eliminare la carta. Ne sei sicuro ?</p>
								</div>
								<div class="modal-footer">
									<a class="btn btn-danger" href="<%= response.encodeURL("RimozioneCartaServlet?cartaID="+ utente.getCarte().get(i).getId())%>">Conferma</a>
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
								</div>
							</div>
						</div>
					</div>
				</tr>
				</tbody>

				<%
							}
						}
					}
				%>
			</table>
		</form>
		<a class="btn btn-primary float-right" href="aggiuntaCarta.jsp">Aggiungi carta</a>

		<button type="button" class="btn btn-danger mt-5" data-toggle="modal" data-target="#exampleModal">
			Elimina account
		</button>
		<br>

		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Eliminazione account</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p class="popup-content">Stai per eliminare il tuo account in modo permanente. Ne sei sicuro ?</p>
					</div>
					<div class="modal-footer">
						<form action="EliminazioneServlet" method="POST">
							<input type="submit" class="btn btn-danger" value="Elimina account">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="javaScript/Changes.js"></script>

</body>
</html>
