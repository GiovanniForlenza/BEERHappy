<%@ page import="entity.UtenteBO" %>
<%@ page import="entity.Indirizzo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="control.RimozioneIndirizzoServlet" %>
<%@ page import="entity.UtenteBO" %>
<%@ page import="control.GestioneCatalogoServlet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Check user credentials
    Boolean flag = (Boolean) session.getAttribute("accessoUtenteBO");
    if ((flag == null) || (!flag.booleanValue()))
    {
        response.sendRedirect("login.jsp");
        return;
    }
    UtenteBO utenteBO = (UtenteBO) session.getAttribute("utenteBO");
    int ruolo= utenteBO.getRuolo();
%>
<html>
<head>
    <title>Selezione ruolo</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="border p-3 rounded" style="width: 35%;">
<%@ include file="navBarBO.jsp"%>
<%
    if(ruolo==1){
%>
        <a class="btn btn-primary" href="<%= response.encodeURL("GestioneCatalogoServlet")%>">Gestione catalogo</a>
<%
    } else if (ruolo==2) {
%>
        <a class="btn btn-primary" href="<%= response.encodeURL("GestioneOrdiniServlet")%>">Gestione Ordini</a>
<%
    } else if (ruolo==3) {
%>
        <a class="btn btn-primary" href="<%= response.encodeURL("GestioneCatalogoServlet")%>">Gestione Catalogo</a>
        <a class="btn btn-primary" href="<%= response.encodeURL("GestioneOrdiniServlet")%>">Gestione Ordini</a>
<%
    } else {
%>
        <a class="btn btn-primary" href="<%= response.encodeURL("GestioneCatalogoServlet")%>">Gestione Catalogo</a>
        <a class="btn btn-primary" href="<%= response.encodeURL("GestioneOrdiniServlet")%>">Gestione Ordini</a>
        <a class="btn btn-primary" href="<%= response.encodeURL("GestioneUtentiServlet")%>">Gestione Utenti</a>
<%
    }
%>
    </div>
</div>
</body>
</html>
