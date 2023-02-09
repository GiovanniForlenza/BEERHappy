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
    <title>Aggiunta prodotto</title>
</head>
<body>
    <form method="post" action="AggiuntaProdottoServlet">
        <p>Nome: <input type="text" name="nome"> <br></p>
        <p>Birrificio: <input type="text" name="birrificio"><br></p>
        <p>Formato: <input type="text" name="formato"><br></p>
        <p>Descrizione: <input type="text" name="descrizione"><br></p>
        <p>Quantità: <input type="number" name="quantita"><br></p>
        <p>Prezzo: <input type="text" name="prezzo"><br></p>
        <p>Image: <input type="image" name="pathImage"><br></p>
        <input type="submit" value="Salva"> <br>
    </form>
</body>
</html>
