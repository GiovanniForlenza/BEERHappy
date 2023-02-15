<%@ page import="entity.UtenteBO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 13/02/2023
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Gestione utenti</title>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%
    Boolean flag = (Boolean) session.getAttribute("accessoUtenteBO");
    if ((flag == null) || (!flag.booleanValue()))
    {
        response.sendRedirect("login.jsp");
        return;
    }
    else if(flag.booleanValue() ) {
        UtenteBO utenteBO = (UtenteBO) session.getAttribute("utenteBO");
        int ruolo = utenteBO.getRuolo();
        if(ruolo!=4)
            response.sendRedirect("selezioneRuolo.jsp");
    }
    ArrayList<UtenteBO> utenti=(ArrayList<UtenteBO>) session.getAttribute("utenti");
%>
<html>

<head>
	<title>Gestione utenti</title>
</head>

<body>
<%@ include file="navBarBO.jsp"%>



<div class="container">
	<h1 class="text-center mt-5">Gestione ordini</h1>
	<a class="btn btn-success" href="aggiuntaUtente.jsp">Aggiungi utente</a>
	<table class="table mt-3">
		<thead>
		<tr>
			<th>E-mail</th>
			<th>Ruoli</th>
			<th></th>
		</tr>
		</thead>
		<%
			for(int i=0; i<utenti.size(); i++){
				UtenteBO utenteBO=utenti.get(i);
		%>
		<form method="post" action="modificaRuoliUtenteBO.jsp">
			<tbody>
			<tr>
				<input type="hidden" name="email" value="<%=utenteBO.getEmail()%>">
				<td><%=utenteBO.getEmail()%></td>

				<%if(utenteBO.getRuolo()==1){%>
				<td>Gestore catalogo</td>
				<%}else if(utenteBO.getRuolo()==2){%>
				<td>Gestore ordini</td>
				<%}else if(utenteBO.getRuolo()==3){%>
				<td>Gestore catalogo, Gestore ordini</td>
				<%}%>
				<td>
					<input type="hidden" name="ruolo" value="<%=utenteBO.getRuolo()%>">
					<input class="btn btn-primary" type="submit" value="Modifica ruoli"> <br>
				</td>

			</tr>
			</tbody>
		</form>

		<%
			}
		%>
	</table>

</div>


</body>
</html>