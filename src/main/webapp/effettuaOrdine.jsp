<%@ page import="java.util.ArrayList" %>
<%@ page import="model.bean.*" %>
<%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 28/01/23
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

	Boolean flag = (Boolean) session.getAttribute("accessoUtente");
	if ((flag == null) || (!flag.booleanValue()))
	{
		response.sendRedirect("login.jsp");
		return;
	}

	Utente utente = (Utente) session.getAttribute("utente");
	Carrello carrello = (Carrello) session.getAttribute("carrello");
	Indirizzo addressView = (Indirizzo) request.getSession().getAttribute("visualizza");
	Carta carta = (Carta) request.getSession().getAttribute("cardView");
%>
<html>
<head>
	<title>Ordine</title>
</head>
<body>

	<%
		if (flag == null){
	%>
			<%@ include file="navBarGuest.jsp"%>
	<%
		}
		else{
	%>
			<%@ include file="navBarStore.jsp"%>
	<%
		}
	%>

	<%
		if(carrello == null){
	%>
			<p>non sono presenti prodotti nel carrello</p>
			<p>per effettuare un ordine aggiungi delle birre nel carrello</p>
	<%
		}


		else if(carrello.getProdotti().size() > 0){
			ArrayList<Prodotto> prodotti = carrello.getProdotti();
	%>
	<div class="container">
		<h1 class="text-center mt-5">Pagina di checkout</h1>
		<form class="mt-5">
			<div class="form-group">
				<label for="username">Nome utente</label>
				<input type="text" class="form-control" id="username" value="<%=utente.getNome() + " " + utente.getCognome()%>" disabled />
			</div>
			<div class="form-group">
				<label for="email">Email</label>
				<input type="email" class="form-control" id="email" value="<%=utente.getEmail()%>" disabled />
			</div>
			<h3 class="mt-5">Prodotti nel carrello</h3>
			<table class="table mt-3">
				<thead>
				<tr>
					<th>Prodotto</th>
					<th>Quantità</th>
					<th>Prezzo</th>
				</tr>
				</thead>
	<%
			float tot = 0;
			for(int i = 0; i < prodotti.size(); i++)
			{
				tot = tot +
						Math.round(prodotti.get(i).getPrezzo() * prodotti.get(i).getQuantita() * 100) / 100;
	%>
			<tbody>
			<tr>
				<td><%=prodotti.get(i).getNome()%></td>
				<td><%=prodotti.get(i).getQuantita()%></td>
				<td><%=prodotti.get(i).getPrezzo()%></td>
			</tr>
			</tbody>

	<%
			}
			request.getSession().setAttribute("prezzo",tot);
	%>
			</table>
			<h3>totale ordine <%=tot%>€</h3>
		</form>
	</div>
<br>


	<div class="container">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#dialogAddress">
			seleziona o aggiungi indirizzo
		</button>
	</div><br>

	<div class="modal fade" id="dialogAddress" tabindex="-1" role="dialog" aria-labelledby="dialogAddressLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="dialogAddressLabel">Seleziona indirizzo</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

				<%
					ArrayList<Indirizzo> indirizzi =  utente.getIndirizzi();
					if(indirizzi != null){
				%>
					<ul class="list-group">
				<%
						for(int i=0; i < indirizzi.size(); i++){
				%>
						<li class="list-group-item">
							<form action="SelezionaIndirizzoServlet" method="post">
								<input type="hidden" name="selezionato" value="<%=indirizzi.get(i).getID()%>">
								<%=indirizzi.get(i).getCitta() + ", " + indirizzi.get(i).getVia()%>
								<input type="submit" class="btn btn-primary float-right" value="select">
							</form>
						</li>
				<%
						}
				%>
					</ul>
					<button type="button" class="btn btn-primary mt-3" onclick="selectAddress()">Aggiungi nuovo indirizzo</button>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
			<%
				if(addressView != null){
			%>
					<div class="container">
						<div class="modal-header form-group">
							<h5 class="modal-title">Indirizzo selezionato</h5>
						</div>
						<div class="modal-body">
							<h6>Città: <%=addressView.getCitta()%></h6>
							<h6>Via: <%=addressView.getVia()%></h6>
							<h6>Civico: <%=addressView.getCivico()%></h6>
							<h6>Telefono: <%=addressView.getTelefono()%></h6>
						</div>
					</div><br>
			<%
				}
			%>
	<div class="container">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#dialogCard">
			seleziona o aggiungi carta
		</button>
	</div><br>

	<div class="modal fade" id="dialogCard" tabindex="-1" role="dialog" aria-labelledby="dialogAddressLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="dialogCardLabel">Seleziona carta</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

				<%
					ArrayList<Carta> card =  utente.getCarte();
					if(card != null){
				%>
					<ul class="list-group">
				<%
						for(int i=0; i < card.size(); i++){
				%>
						<li class="list-group-item">
							<form action="ScelezionaCartaServlet" method="post">
								<input type="hidden" name="selezionato" value="<%=card.get(i).getId()%>">
								<p><%=card.get(i).getIntestatario() + ", " + card.get(i).getnCata() + ", " +  card.get(i).getDataScadenza()%></p>
								<input type="submit" class="btn btn-primary float-right" value="select">
							</form>
						</li>
				<%
						}
				%>
					</ul>
					<button type="button" class="btn btn-primary mt-3" onclick="selectCard()">Aggiungi nuovo carta</button>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>

			<%
				if(carta != null){
			%>
				<div class="container">
					<div class="modal-header">
						<h5 class="modal-title">Carta selezionata</h5>
					</div>
					<div class="modal-body">
						<h6>Intestatario: <%=carta.getIntestatario()%></h6>
						<h6>Numero Carta: <%=carta.getnCata()%></h6>
						<h6>Data scadenza: <%=carta.getDataScadenza()%></h6>
					</div>
				</div><br>
			<%
				}
			%>

			<script>
				function selectAddress(){
					<%request.getSession().setAttribute("selectAddress", true);%>
					document.location.href = "aggiuntaIndirizzo.jsp";
				}

				function selectCard(){
					<%request.getSession().setAttribute("selectCard", true);%>
					document.location.href = "aggiuntaCarta.jsp";
				}
			</script>

			<script>
				const
					dialogAddress  = document.getElementById('dialogAddress'),
					closeAddress   = document.getElementById('closeAddress'),
					buttonAddress  = document.getElementById('buttonDialogAddress'),

					dialogCard = document.getElementById('dialogCard'),
					closeCard = document.getElementById('closeCard'),
					buttonCard = document.getElementById('buttonDialogCard');

				buttonAddress.addEventListener('click', event => {
					dialogAddress.showModal()
				})

				buttonCard.addEventListener('click', event => {
                    dialogCard.showModal()
                })

				closeAddress.addEventListener('click', event => {
					dialogAddress.close()
				})

                closeCard.addEventListener('click', event => {
                    dialogCard.close()
                })

				let observer = new MutationObserver(function()  {
					if (event[0].attributeName == 'open') console.log('updated')
				});

				observer.observe(dialogAddress, { attributes: true });
				observer.observe(dialogCard, { attributes: true });
			</script>

			<div class="container">
					<form action="OrdineUtenteServlet"  method="post">
						<div>
							<input class="btn btn-primary float-right" type="submit" name="effettua ordine" value="Effettua ordine">
						</div>
						<div>
							<a class="btn btn-secondary" href="carrello.jsp">annulla</a>
						</div>
					</form>
			</div>

	<%
		}else {
	%>
			<p>non sono presenti prodotti nel carrello</p>
			<p>per effettuare un ordine aggiungi delle birre nel carrello</p>
	<%
		}
	%>

</body>
</html>
