<%@ page import="entity.UtenteBO" %><%--
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

        if(ruolo!=1 || ruolo!=3)
            response.sendRedirect("selezioneRuolo.jsp");
    }
%>
<html>
<head>
    <title>Gestione prodotti</title>
</head>
<body>

</body>
</html>
