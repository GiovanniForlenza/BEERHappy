<%@ page import="entity.Utente" %>
<%@ page import="entity.UtenteBO" %>
<%@ page import="entity.Prodotto" %>
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
        response.sendRedirect("accesso.jsp");
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
            prezzo = request.getParameter("prezzo");

    Prodotto prodotto = new Prodotto();
    if(nome != null && birrificio != null && formato != null && descrizione != null && quantita != null && prezzo != null) {
        prodotto.setNome(nome);
        prodotto.setBirrificio(birrificio);
        prodotto.setFormato(formato);
        prodotto.setDescrizione(descrizione);
        prodotto.setQuantitaDisp(Integer.parseInt(quantita));
        prodotto.setPrezzo(Double.parseDouble(prezzo));
        prodotto.setPathImage("");

        request.getSession().setAttribute("oldProduct", prodotto);
    }else{
        prodotto = (Prodotto) request.getSession().getAttribute("oldProduct");
    }
%>
<!-- TODO mostra messaggio se il prodotto è già presente -->
    <form method="post" action="ModificaProdottoServlet">
        <p>Nome: <input type="text" name="nome" value="<%=prodotto.getNome()%>"> <br></p>
        <p>Birrificio: <input type="text" name="birrificio" value="<%=prodotto.getBirrificio()%>"><br></p>
        <p>Formato: <input type="text" name="formato" value="<%=prodotto.getFormato()%>"><br></p>
        <p>Descrizione: <input type="text" name="descrizione" value="<%=prodotto.getDescrizione()%>"><br></p>
        <p>Quantità: <input type="number" name="quantita" value="<%=prodotto.getQuantitaDisp()%>"><br></p>
        <p>Prezzo: <input type="text" name="prezzo" value="<%=prodotto.getPrezzo()%>"><br></p>
        <p>Image: <input type="image" name="pathImage" value="<%=prodotto.getPathImage()%>"><br></p>
        <input type="submit" value="Salva"> <br>
    </form>

    <form action="EliminazioneProdottoServlet" method="post">
        <input name="delete" type="submit" value="delete">
    </form>
</body>
</html>
