<%@ page import="entity.UtenteBO" %>
<%@ page import="entity.Ordine" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Stato" %><%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 10/02/2023
  Time: 03:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

  Boolean flag = (Boolean) session.getAttribute("accessoUtenteBO");
  if ((flag == null) || (!flag.booleanValue()))
  {
    response.sendRedirect("accesso.jsp");
    return;
  }
  else if(flag.booleanValue() ) {
    UtenteBO utenteBO = (UtenteBO) session.getAttribute("utenteBO");
    int ruolo = utenteBO.getRuolo();

    if(ruolo==1)
      response.sendRedirect("selezioneRuolo.jsp");
  }
  ArrayList<Ordine> ordini=(ArrayList<Ordine>) session.getAttribute("ordini");
%>
<html>
<head>
    <title>Gestione ordini</title>

</head>
<body>
<%@ include file="navBarBO.jsp"%>
<%
  for(int i=0; i<ordini.size(); i++){
    Ordine ordine=ordini.get(i);

%>
    <form method="post" action="modificaStatoOrdine.jsp">
      <p>idOrdine: <label><%=ordine.getIdOrdine()%></label></p>
      <input type="hidden" name="idOrdine" value="<%=ordine.getIdOrdine()%>">
      <p>Prezzo: <label><%=ordine.getPrezzo()%></label></p>
      <input type="hidden" name="prezzo" value="<%=ordine.getPrezzo()%>">
      <p>Data: <label><%=ordine.getDataOrdine()%></label></p>
      <input type="hidden" name="data" value="<%=ordine.getDataOrdine()%>">
      <p>Indirizzo: <label><%=ordine.getCitta()+" "+ordine.getVia()+" "+ordine.getCivico()+" "+ordine.getTelefono()%></label></p>
      <input type="hidden" name="via" value="<%=ordine.getVia()%>">
      <input type="hidden" name="citta" value="<%=ordine.getCitta()%>">
      <input type="hidden" name="civico" value="<%=ordine.getCivico()%>">
      <input type="hidden" name="telefono" value="<%=ordine.getTelefono()%>">
      <p>Stato: <label><%=ordine.getStato()%></label></p>
      <input type="hidden" name="stato" value="<%=ordine.getStato()%>">
      <%if(!(ordine.getStato().equals(Stato.annullato))&&!(ordine.getStato().equals(Stato.consegnato))){%>
          <input type="submit" value="Modifica"> <br>
      <%
        }
      %>

    </form>
<%
  }
%>
</body>
</html>
