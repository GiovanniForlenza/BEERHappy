<%@ page import="model.bean.Prodotto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    ArrayList<Prodotto> birra = (ArrayList<Prodotto>) request.getAttribute("birre");
    String error = (String)request.getAttribute("error");

    if(birra == null && error == null) {
        request.getSession().setAttribute("home", true);
        response.sendRedirect(response.encodeRedirectURL("./RichiestaBirreServlet"));
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>BEerHAPPY</title>
    </head>
    <body>


    <%@ include file="navBarGuest.jsp"%>
    <!-- Header -->
    <header class="jumbotron jumbotron-fluid text-center bg-primary text-white">
        <div class="container">
            <h1 class="display-4">BEerHAPPY</h1>
            <p class="lead">La migliore selezione di birre artigianali del mondo</p>
        </div>
    </header>

    <!-- Sezione prodotto -->
    <div class="container my-5">
        <h2 class="text-center">Birre in vendita</h2>
        <h4>Novità</h4>
        <div class="row">
            <%
                if(birra != null){
                int i = birra.size() - 3;
                while (i < birra.size()){
            %>
            <!-- Prodotto 1 -->
            <div class="col-md-4">
                <div class="card mb-4">
                    <img class="card-img-top" src="<%=birra.get(i).getPathImage()%>" alt="Immagine prodotto">
                    <div class="card-body">
                        <h4 class="card-title"><%=birra.get(i).getNome()%></h4>
                        <p class="card-text"
                            style="  overflow: hidden;
                            display: -webkit-box;
                            -webkit-line-clamp: 3;
                            -webkit-box-orient: vertical;"><%=birra.get(i).getDescrizione()%></p>
                        <a href="<%= response.encodeURL("DettagliProdottoServlet?nome=" + birra.get(i).getNome() +
				"&birrificio=" + birra.get(i).getBirrificio() + "&formato=" + birra.get(i).getFormato())%>" class="btn btn-primary">Dettagli</a>
                    </div>
                </div>
            </div>

            <%
                    i++;
                }
                }
            %>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-light py-3">
        <div class="container">
            <p class="text-center">Copyright &copy; Birreria 2023</p>
        </div>
    </footer>

    </body>
</html>