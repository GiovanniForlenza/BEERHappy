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
        response.sendRedirect("login.jsp");
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
<%@ include file="navBarBO.jsp"%>

<div class="container">
    <h1 class="text-center mt-5">Gestione catalogo</h1>
    <a class="btn btn-success float-right" href="aggiuntaProdotto.jsp">Aggiungi prodotto</a>
        <table class="table mt-3">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Birrificio</th>
                <th>Formato</th>
                <th>Descrizione</th>
                <th>Quantità</th>
                <th>Prezzo</th>
                <th></th>
            </tr>
            </thead>
            <%
                for(int i=0; i<prodotti.size(); i++){
                    Prodotto prodotto=prodotti.get(i);
            %>
    <form method="post" action="modificaProdotto.jsp">
            <tbody>
            <tr>
                <input type="hidden" name="nome" value="<%=prodotto.getNome()%>">
                <td><%=prodotto.getNome()%></td>
                <input type="hidden" name="birrificio" value="<%=prodotto.getBirrificio()%>">
                <td><%=prodotto.getBirrificio()%></td>
                <input type="hidden" name="formato" value="<%=prodotto.getFormato()%>">
                <td><%=prodotto.getFormato()%></td>
                <input type="hidden" name="descrizione" value="<%=prodotto.getDescrizione()%>">
                <td><%=prodotto.getDescrizione()%></td>
                <input type="hidden" name="quantita" value="<%=prodotto.getQuantitaDisp()%>">
                <td><%=prodotto.getQuantitaDisp()%></td>
                <input type="hidden" name="prezzo" value="<%=prodotto.getPrezzo()%>">
                <td><%=prodotto.getPrezzo()%> &euro;</td>
                <input type="hidden" name="image" value="<%=prodotto.getPathImage()%>">
                <td><input class="btn btn-primary" type="submit" value="Modifica"></td>
            </tr>
            </tbody>
    </form>

            <%
                }
            %>
        </table>

</div>



</body>
</html>
