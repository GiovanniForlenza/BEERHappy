<%@ page import="model.bean.Utente" %>
<%@ page import="model.bean.UtenteBO" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 07/02/2023
  Time: 11:32
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
    <title>Modifica prodotto</title>
</head>
<body>
<%
    String
            nome = request.getParameter("nome"),
            birrificio = request.getParameter("birrificio"),
            formato = request.getParameter("formato"),
            descrizione = request.getParameter("descrizione"),
            quantita = request.getParameter("quantita"),
            prezzo = request.getParameter("prezzo"),
            image = request.getParameter("image");

    Prodotto prodotto = new Prodotto();
    if(nome != null && birrificio != null && formato != null && descrizione != null && quantita != null && prezzo != null) {
        prodotto.setNome(nome);
        prodotto.setBirrificio(birrificio);
        prodotto.setFormato(formato);
        prodotto.setDescrizione(descrizione);
        prodotto.setQuantitaDisp(Integer.parseInt(quantita));
        prodotto.setPrezzo(Float.parseFloat(prezzo));
        prodotto.setPathImage(image);

        request.getSession().setAttribute("oldProduct", prodotto);
    }else{
        prodotto = (Prodotto) request.getSession().getAttribute("oldProduct");
    }
%>

<%@include file="navBarBO.jsp"%>

<!-- TODO mostra messaggio se il prodotto è già presente -->
<div class="container">
    <h1 class="text-center mt-5">Modifica prodotto</h1>
    <form class="mt-5" method="post" action="ModificaProdottoServlet">
        <div class="form-group">
            <label for="nome">Nome</label>
            <input  type="text" class="form-control" id="nome" name="nome" value="<%=prodotto.getNome()%>">
        </div>
        <div class="form-group">
            <label for="birrificio">Birrificio</label>
            <input  type="text" class="form-control" id="birrificio" name="birrificio" value="<%=prodotto.getBirrificio()%>">
        </div>
        <div class="form-group">
            <label for="formato">Formato</label>
            <input  type="text" class="form-control" id="formato" name="formato" value="<%=prodotto.getFormato()%>">
        </div>
        <div class="form-group">
            <label for="descrizione">Descrizione</label>
            <textarea class="form-control" id="descrizione" name="descrizione" rows="4" cols="50">
                <%=prodotto.getDescrizione()%>
            </textarea>
        </div>
        <div class="form-group">
            <label for="quantita">Quantità</label>
            <input  type="number" class="form-control" id="quantita" name="quantita" value="<%=prodotto.getQuantitaDisp()%>">
        </div>
        <div class="form-group">
            <label for="prezzo">Prezzo</label>
            <input  type="text" class="form-control" id="prezzo" name="prezzo" value="<%=prodotto.getPrezzo()%>">
        </div>
        <div class="form-group">
            <label for="image">URL Image</label>
            <input  type="url" class="form-control" id="image" name="image" value="<%=prodotto.getPathImage()%>">
        </div>
        <div class="form-group">
            <input class="btn btn-primary float-right" type="submit" value="Salva">
            <a class="btn btn-secondary" href="gestioneCatalogo.jsp">Annulla</a>
        </div>
    </form>
    <form class="mt-5" action="EliminazioneProdottoServlet" method="post">
        <input class="btn btn-danger" name="delete" type="submit" value="Elimina prodotto">
    </form>
</div>

</body>
</html>
