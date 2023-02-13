<%@ page import="entity.UtenteBO" %><%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 13/02/2023
  Time: 17:42
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

    if(ruolo!=4)
      response.sendRedirect("selezioneRuolo.jsp");
  }

%>
<html>
<head>
  <title>Modifica ruoli</title>
</head>
<body>
<%
  String email = request.getParameter("email");
  String ruolo = request.getParameter("ruolo");


  UtenteBO utenteBO=new UtenteBO();
  if(email != null && ruolo != null){
    utenteBO.setEmail(email);
    utenteBO.setRuolo(Integer.parseInt(ruolo));

    request.getSession().setAttribute("utente", utenteBO);
  }else{
    utenteBO = (UtenteBO) request.getSession().getAttribute("oldUser");
  }
%>

  <form method="post" action="ModificaRuoliUtenteBOServlet">
    <p>E-mail: <label><%=utenteBO.getEmail()%></label></p>
    <input type="hidden" name="email" value="<%=utenteBO.getEmail()%>">
    <%if(utenteBO.getRuolo()==1){%>
      <p>Ruoli: <label>Gestore catalogo</label></p>
    <%}else if(utenteBO.getRuolo()==2){%>
      <p>Ruoli: <label>Gestore ordini</label></p>
    <%}else if(utenteBO.getRuolo()==3){%>
      <p>Ruoli: <label>Gestore catalogo, Gestore ordini</label></p>
    <%}%>
    <p>Seleziona ruoli: Gestore catalogo<input type="checkbox" name="ruolo" value="Gestore catalogo">Gestore ordini<input type="checkbox" name="ruolo" value="Gestore ordini">
    <input type="submit" value="Salva"> <br>
  </form>

  <form action="EliminazioneUtenteBOServlet" method="post">
    <input name="delete" type="submit" value="delete">
</form>
</body>
</html>
