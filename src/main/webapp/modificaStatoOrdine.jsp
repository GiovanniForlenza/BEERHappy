<%@ page import="entity.UtenteBO" %>
<%@ page import="entity.Ordine" %>
<%@ page import="entity.Stato" %><%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 10/02/2023
  Time: 03:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		if(ruolo==1)
			response.sendRedirect("selezioneRuolo.jsp");
	}
%>
<html>
<head>
	<title>Modifica stato ordine</title>
</head>
<body>
<%
	String
			idOrdine = request.getParameter("idOrdine"),
			prezzo = request.getParameter("prezzo"),
			data = request.getParameter("data"),
			citta=request.getParameter("citta"),
			via=request.getParameter("via"),
			civico=request.getParameter("civico"),
			telefono=request.getParameter("telefono"),
			stato=request.getParameter("stato");
	Ordine ordine = new Ordine();
	if(idOrdine != null && prezzo != null && data != null && citta != null && via != null && civico != null && telefono != null && stato != null) {
		ordine.setIdOrdine(Integer.parseInt(idOrdine));
		ordine.setDataOrdine(data);
		ordine.setPrezzo(Float.parseFloat(prezzo));
		ordine.setCitta(citta);
		ordine.setVia(via);
		ordine.setCivico(Integer.parseInt(civico));
		ordine.setTelefono(telefono);
		ordine.setStato(Stato.valueOf(stato));
		request.getSession().setAttribute("ordine", ordine);
	}else{
		ordine = (Ordine) request.getSession().getAttribute("ordine");
	}
%>

<%@include file="navBarBO.jsp"%>
<div class="container">
		<div class="border p-3 rounded">
			<form method="post" action="ModificaStatoServlet">
				<p>Ordine id: <label><%=ordine.getIdOrdine()%></label></p>
				<input type="hidden" name="nome" value="<%=ordine.getIdOrdine()%>">
				<p>Data: <label><%=ordine.getDataOrdine()%></label></p>
				<p>Prezzo: <label><%=ordine.getPrezzo()%></label></p>
				<p>Stato: <label><%=ordine.getStato()%></label></p>
				<p>Seleziona stato: In consegna<input type="radio" name="newState" value="inConsegna"> Consegnato<input type="radio" name="newState" value="consegnato"></p>
				<a class="btn btn-secondary" href="gestioneOrdini.jsp">Annulla</a>
				<input class="btn btn-primary" type="submit" value="Salva"> <br>
			</form>
		</div>
</div>


</body>
</html>