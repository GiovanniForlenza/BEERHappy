<%@ page import="entity.UtenteBO" %>
<%@ page import="entity.Indirizzo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webapptest.RimozioneIndirizzoServlet" %>
<%@ page import="entity.UtenteBO" %>
<%@ page import="com.example.webapptest.GestioneCatalogoServlet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Check user credentials
    Boolean flag = (Boolean) session.getAttribute("accessoUtenteBO");
    if ((flag == null) || (!flag.booleanValue()))
    {
        response.sendRedirect("accesso.jsp");
        return;
    }
    UtenteBO utenteBO = (UtenteBO) session.getAttribute("utenteBO");
    int ruolo= utenteBO.getRuolo();
%>
<html>
<head>
    <title>Selezione ruolo</title>
</head>
<body>
<%
    if(ruolo==1){
%>
        <a href="<%= response.encodeURL("GestioneCatalogoServlet")%>">Gestione catalogo</a>
<%
    } else if (ruolo==2) {
%>
        <a href="<%= response.encodeURL("GestioneOrdiniServlet")%>">Gestione Ordini</a>
<%
    } else if (ruolo==3) {
%>
        <a href="<%= response.encodeURL("GestioneCatalogoServlet")%>">Gestione Catalogo</a>
        <a href="<%= response.encodeURL("GestioneOrdiniServlet")%>">Gestione Ordini</a>
<%
    } else {
%>
        <a href="<%= response.encodeURL("GestioneCatalogoServlet")%>">Gestione Catalogo</a>
        <a href="<%= response.encodeURL("GestioneOrdiniServlet")%>">Gestione Ordini</a>
        <a href="<%= response.encodeURL("GestioneUtentiServlet")%>">Gestione Utenti</a>
<%
    }
%>

</body>
</html>
