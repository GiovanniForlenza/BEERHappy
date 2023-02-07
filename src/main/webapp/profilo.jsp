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
    response.sendRedirect("accesso.jsp");
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

	<form method="post" name="nomeCognome">
		<p>nome: <input  name="nome" disabled="true" value="<%= utente.getNome() %>"> </p>
		<p>cognome: <input name="cognome" disabled="true" value="<%= utente.getCognome()%>"></p>
		<p>email: <input disabled value="<%= utente.getEmail()%>"></p>
		<input type="button" id="button" value="modifica dati" onclick="enable();">	<br> <br>
	</form>

	<script type="text/javascript">
        function enable() {
            let btn = document.getElementById("button");

            document.nomeCognome.nome.disabled = false;
            document.nomeCognome.cognome.disabled = false;

            btn.value = "salva";
            btn.onclick = () => {
                btn.value = "modifica dati";
                document.nomeCognome.nome.disabled = true;
                document.nomeCognome.cognome.disabled = true;
                //save data end refresh page
            };
        }
	</script>

	<a href="cambioPassword.jsp">Cambia password</a>

	<form action="aggiuntaIndirizzo.jsp" >
		<h3>Indirizzi</h3>
		<input type="submit" id="addAddress" value="Aggiungi indirizzo">
	</form>

	<%
		if(utente.getIndirizzi() != null){
			if(utente.getIndirizzi().size() > 0){
				for(int i = 0; i < utente.getIndirizzi().size(); i++){
	%>

	<p>citta: <%= utente.getIndirizzi().get(i).getCitta()%></p>
	<p>cap: <%= utente.getIndirizzi().get(i).getCap()%></p>
	<p>via: <%= utente.getIndirizzi().get(i).getVia()%></p>
	<p>telefono: <%= utente.getIndirizzi().get(i).getTelefono()%></p>
	<br>

	<a href="<%= response.encodeURL("RimozioneIndirizzoServlet?indirizzoID="+
		utente.getIndirizzi().get(i).getID() + "")%>">x</a>

	<%
			}
		}
	}
	else {
	%>

	<p>nessun indirizzo presente</p>

	<%
		}
	%>

	<form action="aggiuntaCarta.jsp" >
		<h3>Carte</h3>
		<input type="submit" id="addCarta" value="Aggiungi carta">
	</form>

	<%
		if(utente.getCarte() != null){
			if(utente.getCarte().size() > 0){
				for(int i = 0; i < utente.getCarte().size(); i++){
	%>
					<p>titolare: <%= utente.getCarte().get(i).getIntestatario()%></p>
					<p>numero carta: <%= utente.getCarte().get(i).getnCata()%></p>
					<p>scadenza: <%= utente.getCarte().get(i).getDataScadenza()%></p>
					<br>

					<a href="<%= response.encodeURL("RimozioneCartaServlet?cartaID="+ utente.getCarte().get(i).getId())%>">x</a>
					<br>
	<%
			}
		}
	} else {
	%>

	<p>nessuna carta presente</p>

	<%
		}
	%>

	<a href="<%= response.encodeURL("EliminazioneServlet?email="+utente.getEmail())%>">Elimina account</a>
</body>
</html>
