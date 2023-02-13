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
        response.sendRedirect("accesso.jsp");
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
<%
  for(int i=0; i<utenti.size(); i++){
    UtenteBO utenteBO=utenti.get(i);

%>
    <form method="post" action="modificaRuoliUtenteBO.jsp">
      <p>E-mail: <label><%=utenteBO.getEmail()%></label></p>
      <input type="hidden" name="email" value="<%=utenteBO.getEmail()%>">
      <%if(utenteBO.getRuolo()==1){%>
        <p>Ruoli: <label>Gestore catalogo</label></p>
      <%}else if(utenteBO.getRuolo()==2){%>
        <p>Ruoli: <label>Gestore ordini</label></p>
      <%}else if(utenteBO.getRuolo()==3){%>
      <p>Ruoli: <label>Gestore catalogo, Gestore ordini</label></p>
      <%}%>
      <input type="hidden" name="ruolo" value="<%=utenteBO.getRuolo()%>">
      <input type="submit" value="Modifica ruoli"> <br>
    </form>
<%
  }
%>

<a href="aggiuntaUtente.jsp">Aggiungi utente</a>
</body>
</html>
