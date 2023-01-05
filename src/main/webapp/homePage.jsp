<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="entity.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Collection<Prodotto> birra = (Collection<Prodotto>) request.getAttribute("birre");

    //ToDo poi la vedo...
    String error = (String)request.getAttribute("error");

    if(birra == null && error == null) {
        response.sendRedirect(response.encodeRedirectURL("./RichiestaBirreServlet"));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>BEerHAPPY</title>
        <style>
            li{
                list-style-type: none;
            }
        </style>
    </head>
    <body>
        <h1>HomePage</h1>
    <br>
    <a href="profilo.jsp">Profilo</a>
    <br>
    <ul>
        <li><a href="catalogo.jsp">catalogo</a></li>
        <li><a href="catalogoNovita.jsp">novita</a></li>
        <li><a>chi siamo</a></li>
    </ul>
        <%
        //TODO controllo se non ci sono prodotti
        if(birra != null && birra.size() > 0){
            int i = 0;
            Iterator <?> it = birra.iterator();
            while(it.hasNext() && i < birra.size()){
                Prodotto prodotto = (Prodotto) it.next();
        %>

        <ol>
            <li><%=prodotto.getNome()%></li>
            <li>Birrificio: <%=prodotto.getBirrificio()%></li>
            <li>Descrizione prodotto: <%=prodotto.getDescrizione()%></li>
            <li>Formato: <%=prodotto.getFormato()%></li>
            <li>$ <%=prodotto.getPrezzo()%></li>
        </ol>

        <%
                i++;
            }
        }else { %>
        <h3>Al momento non ci sono prodotti inseriti</h3>
        <% 	}%>

    </body>
</html>