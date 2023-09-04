<%@ page import="model.bean.UtenteBO" %>
<%--
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

    <script type="text/javascript">
        function controllo () {

            let nome, birrificio, formato, descrizione, quantita, prezzo, url;
            nome = document.getElementById("name").value;
            birrificio = document.getElementById("birrificio").value;
            formato = document.getElementById("formato").value;
            descrizione = document.getElementById("descrizione").value;
            quantita = document.getElementById("quantita").value;
            prezzo = document.getElementById("prezzo").value;

            if(controlLetteralName(nome) && controlLetteralBirrificio(birrificio) && controlFormato(formato)
                    && controlDescrizione(descrizione) && controlQuantita(quantita) && controlPrice(prezzo)){
                document.modulo.submit();
            }

        }
    </script>
</head>
<body>

<%@include file="navBarBO.jsp"%>

    <div class="container">
        <h1 class="text-center mt-5">Aggiunta prodotto</h1>
        <form class="mt-5" method="post" action="AggiuntaProdottoServlet" name="modulo">
            <div class="form-group">
                <label for="name">Nome</label>
                <input  type="text" class="form-control" id="name" name="nome" required>
            </div>
            <div class="form-group">
                <span id="messageLetter"></span>
            </div>
            <div class="form-group">
                <label for="birrificio">Birrificio</label>
                <input  type="text" class="form-control" id="birrificio" name="birrificio" required>
            </div>
            <div class="form-group">
                <span id="messageBirrificio"></span>
            </div>
            <div class="form-group">
                <label for="formato">Formato</label>
                <input  type="text" class="form-control" id="formato" name="formato" required>
            </div>
            <div class="form-group">
                <label for="descrizione">Descrizione</label>
                <textarea class="form-control" id="descrizione" name="descrizione" rows="4" cols="50" required>
                </textarea>
            </div>
            <div class="form-group">
                <span id="messageDescrizione"></span>
            </div>
            <div class="form-group">
                <label for="quantita">Quantità</label>
                <input  type="number" class="form-control" id="quantita" name="quantita" min="0" max="100" required>
            </div>
            <div class="form-group">
                <span id="messageQuantita"></span>
            </div>
            <div class="form-group">
                <label for="prezzo">Prezzo</label>
                <input  type="text" class="form-control" id="prezzo" name="prezzo" required>
            </div>
            <div class="form-group">
                <span id="messagePrice"></span>
            </div>
            <div class="form-group">
                <label for="image">URL Image</label>
                <input  type="url" class="form-control" id="image" name="image">
            </div>
            <div class="form-group">
                <span id="messageURL"></span>
            </div>
            <div class="form-group">
                <input type="button" class="btn btn-primary float-right"  value="Salva" onclick="controllo()">
                <a class="btn btn-secondary" href="gestioneCatalogo.jsp">Annulla</a>
            </div>
        </form>
    </div>
    <script type="text/javascript" src="javaScript/ControlFormatoProdotto.js"></script>

</body>
</html>

