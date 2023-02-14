<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


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
            <h1 class="display-4">Birreria</h1>
            <p class="lead">La migliore selezione di birre artigianali del mondo</p>
        </div>
    </header>

    <!-- Sezione prodotto -->
    <div class="container my-5">
        <h2 class="text-center">Birre in vendita</h2>
        <div class="row">
            <!-- Prodotto 1 -->
            <div class="col-md-4">
                <div class="card mb-4">
                    <img class="card-img-top" src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
                    <div class="card-body">
                        <h4 class="card-title">Birra 1</h4>
                        <p class="card-text">Descrizione birra 1</p>
                        <a href="#" class="btn btn-primary">Acquista ora</a>
                    </div>
                </div>
            </div>
            <!-- Prodotto 2 -->
            <div class="col-md-4">
                <div class="card mb-4">
                    <img class="card-img-top" src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
                    <div class="card-body">
                        <h4 class="card-title">Birra 2</h4>
                        <p class="card-text">Descrizione birra 2</p>
                        <a href="#" class="btn btn-primary">Acquista ora</a>
                    </div>
                </div>
            </div>
            <!-- Prodotto 3 -->
            <div class="col-md-4">
                <div class="card mb-4">
                    <img class="card-img-top" src="https://via.placeholder.com/500x325" alt="Immagine prodotto">
                    <div class="card-body">
                        <h4 class="card-title">Birra 3</h4>
                        <p class="card-text">Descrizione birra 3</p>
                        <a href="#" class="btn btn-primary">Acquista ora</a>
                    </div>
                </div>
            </div>
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