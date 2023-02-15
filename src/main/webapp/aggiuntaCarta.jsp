<%--
  Created by IntelliJ IDEA.
  User: gaeta
  Date: 23/01/2023
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Check user credentials
    Boolean flag = (Boolean) session.getAttribute("accessoUtente");
    if ((flag == null) || (!flag.booleanValue()))
    {
        response.sendRedirect("login.jsp");
        return;
    }

%>
<html>
<head>
    <title>Aggiunta Carta</title>
</head>
<body>
<%@ include file="navBarStore.jsp"%>
<div class="container my-5">
    <h1 class="mb-4">Aggiungi Carta</h1>
    <form method="post" action="AggiuntaCartaServlet">
        <div class="form-group">
            <label for="inputCardNumber">Numero Carta</label>
            <input
                    type="text"
                    class="form-control"
                    id="inputCardNumber"
                    placeholder="Inserisci il numero della tua carta"
                    name="numero"
                    minlength="16"
                    maxlength="16"
                    required
            />
        </div>
        <div class="form-group">
            <label for="inputName">Nome sulle Carta</label>
            <input
                    type="text"
                    class="form-control"
                    id="inputName"
                    placeholder="Inserisci il nome sulle carta"
                    name="titolare"
                    required
            />
        </div>
        <div class="form-group">
            <label for="inputExpiry">Data di Scadenza</label>
            <input
                    type="date"
                    class="form-control"
                    id="inputExpiry"
                    placeholder="YYYY/MM/GG"
                    name="dataScadenza"
                    required
            />
        </div>
        <div class="form-group">
            <label for="inputCVC">CVC</label>
            <input
                    type="text"
                    class="form-control"
                    id="inputCVC"
                    placeholder="Inserisci il codice di sicurezza"
                    name="cvv"
                    required
            />
        </div>
        <input type="submit" class="btn btn-primary" value="Aggiungi Carta">
    </form>
</div>

</body>
</html>
