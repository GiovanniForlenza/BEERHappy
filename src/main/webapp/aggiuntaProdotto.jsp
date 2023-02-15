<%@ page import="entity.UtenteBO" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 07/02/2023
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
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

        if(ruolo==2)
            response.sendRedirect("selezioneRuolo.jsp");
    }
%>
<html>
<head>
    <title>Aggiunta prodotto</title>
</head>
<body>

<%@include file="navBarBO.jsp"%>

    <div class="container">
        <h1 class="text-center mt-5">Modifica prodotto</h1>
        <form class="mt-5" method="post" action="AggiuntaProdottoServlet">
            <div class="form-group">
                <label for="nome">Nome</label>
                <input  type="text" class="form-control" id="nome" name="nome">
            </div>
            <div class="form-group">
                <label for="birrificio">Birrificio</label>
                <input  type="text" class="form-control" id="birrificio" name="birrificio">
            </div>
            <div class="form-group">
                <label for="formato">Formato</label>
                <input  type="text" class="form-control" id="formato" name="formato">
            </div>
            <div class="form-group">
                <label for="descrizione">Descrizione</label>
                <textarea class="form-control" id="descrizione" name="descrizione" rows="4" cols="50">
                </textarea>
            </div>
            <div class="form-group">
                <label for="quantita">Quantità</label>
                <input  type="number" class="form-control" id="quantita" name="quantita">
            </div>
            <div class="form-group">
                <label for="prezzo">Prezzo</label>
                <input  type="text" class="form-control" id="prezzo" name="prezzo">
            </div>
            <div class="form-group">
                <label for="image">URL Image</label>
                <input  type="url" class="form-control" id="image" name="image">
            </div>
            <div class="form-group">
                <input class="btn btn-primary float-right" type="submit" value="Salva">
                <a class="btn btn-secondary" href="gestioneCatalogo.jsp">Annulla</a>
            </div>
        </form>
    </div>
</body>
</html>
