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
    int i=(Integer.parseInt(request.getParameter("nome")));
    ArrayList<Prodotto> prodotti= (ArrayList<Prodotto>) request.getAttribute("prodotti");
    Prodotto prodotto=prodotti.get(i);
    request.getSession().setAttribute("prodotto", prodotto);
%>
<html>
<head>
    <title>Modifica prodotto</title>
</head>
<body>
    <form method="post" action="ModificaProdottoServlet">
        <p>Nome: <input type="text" name="nome" placeholder="<%=prodotto.getNome()%>"> <br></p>
        <p>Birrificio: <input type="text" name="birrificio" placeholder="<%=prodotto.getBirrificio()%>"><br></p>
        <p>Formato: <input type="text" name="formato" placeholder="<%=prodotto.getFormato()%>"><br></p>
        <p>Descrizione: <input type="text" name="descrizione" placeholder="<%=prodotto.getDescrizione()%>"><br></p>
        <p>Quantità: <input type="number" name="quantita" placeholder="<%=prodotto.getQuantita()%>"><br></p>
        <p>Prezzo: <input type="text" name="prezzo" placeholder="<%=prodotto.getPrezzo()%>"><br></p>
        <p>Image: <input type="image" name="pathImage" placeholder="<%=prodotto.getPathImage()%>"><br></p>
        <input type="submit" value="Salva"> <br>
    </form>

    <button></button>
</body>
</html>
