<%@ page import="entity.UtenteBO" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 27/01/2023
  Time: 09:46
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
    ArrayList<Prodotto> prodotti=(ArrayList<Prodotto>) session.getAttribute("prodotti");
%>
<html>

<head>
    <title>Gestione prodotti</title>
</head>

<body>
<%
    for(int i=0; i<prodotti.size(); i++){
        Prodotto prodotto=prodotti.get(i);

%>
        <form method="post" action="modificaProdotto.jsp">
            <input type="hidden" name="nome" value=<%=i%>>
            <p>Nome: <label><%=prodotto.getNome()%></label></p>
            <p>Birrificio: <label><%=prodotto.getBirrificio()%></label></p>
            <p>Formato: <label><%=prodotto.getFormato()%></label></p>
            <p>Descrizione: <label><%=prodotto.getDescrizione()%></label></p>
            <p>Quantità: <label><%=prodotto.getQuantitaDisp()%></label></p>
            <p>Prezzo: <label><%=prodotto.getPrezzo()%></label></p>
            <input type="submit" value="Modifica"> <br>
        </form>
<%
    }
%>

<a href="aggiuntaProdotto.jsp">Aggiungi prodotto</a>
</body>
</html>
