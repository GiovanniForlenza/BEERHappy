<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.*" %>
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
		response.sendRedirect("accesso.jsp");
		return;
	}

	Utente utente = (Utente) session.getAttribute("utente");
	Carrello carrello = (Carrello) session.getAttribute("carrello");

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
		ArrayList<Prodotto> prodotti = carrello.getProdotti();
		if(prodotti.size() > 0){
	%>
			<h2>DATI Utente</h2>
			<h3>nome: <%=utente.getNome()%></h3>
			<h3>cognome: <%=utente.getCognome()%></h3>
			<h3>e-mail: <%=utente.getEmail()%></h3>

			<button id="buttonDialogAddress">seleziona indirizzo</button>
			<dialog id="dialogAddress">
				<%
					ArrayList<Indirizzo> indirizzi =  utente.getIndirizzi();
					if(indirizzi != null){
						for(int i=0; i < indirizzi.size(); i++){
				%>
							<form action="SelezionaIndirizzoServlet" method="post">
								<input type="hidden" name="selezionato" value="<%=indirizzi.get(i).getID()%>">
								<p><%=indirizzi.get(i).getCitta()%></p>
								<p><%=indirizzi.get(i).getVia()%></p>
								<p><%=indirizzi.get(i).getCivico()%></p>
								<p><%=indirizzi.get(i).getTelefono()%></p>
								<input type="submit" value="select">
							</form>
				<%
						}

					}else {
				%>
						<p>non sono presenti indirizzi</p>
						<p>aggiungi un indirizzo per poterlo selezionare</p>
				<%
					}
				%>

				<button id="closeAddress">x</button>
			</dialog>

			<button onclick="selectAddress()">aggiungi indirizzo</button> <br>

			<%
				Indirizzo addressView = (Indirizzo) request.getSession().getAttribute("visualizza");
				if(addressView != null){
			%>
					<p>INDIRIZZO SELEZIONATO</p>
					<p><%=addressView.getCitta()%></p>
					<p><%=addressView.getVia()%></p>
					<p><%=addressView.getCivico()%></p>
					<p><%=addressView.getTelefono()%></p>
			<%
				}
			%>

			<button id="buttonDialogCard">seleziona carta</button>
			<dialog id="dialogCard">
				<%
					ArrayList<Carta> card =  utente.getCarte();
					if(card != null){
						for(int i=0; i < card.size(); i++){
				%>
							<form action="ScelezionaCartaServlet" method="post">
								<input type="hidden" name="selezionato" value="<%=card.get(i).getId()%>">
								<p><%=card.get(i).getIntestatario()%></p>
								<p><%=card.get(i).getnCata()%></p>
								<p><%=card.get(i).getDataScadenza()%></p>
								<input type="submit" value="select">
							</form>
				<%
						}
					}else {
				%>
						<p>non sono presenti carte</p>
						<p>aggiungi una carta per poterla selezionare</p>
				<%
					}
				%>
				<button id="closeCard">x</button>
			</dialog>

			<button onclick="selectCard()">aggiungi carta</button> <br>

			<%
				Carta carta = (Carta) request.getSession().getAttribute("cardView");
				if(carta != null){
			%>
					<p>CARTA SELEZIONATA</p>
					<p><%=carta.getIntestatario()%></p>
					<p><%=carta.getnCata()%></p>
					<p><%=carta.getDataScadenza()%></p>
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

			<h2>Prodotti</h2>
	<%
			double tot = 0;
			for(int i = 0; i < prodotti.size(); i++)
			{
				tot = tot + (prodotti.get(i).getPrezzo() * prodotti.get(i).getQuantita());
	%>
				<p><%=prodotti.get(i).getNome()%></p>
				<p><%=prodotti.get(i).getQuantita()%></p>
				<p><%=prodotti.get(i).getPrezzo()%></p>
	<%
			}
			request.getSession().setAttribute("prezzo",tot);
	%>
			<h3>totale ordine <%=tot%>€</h3>
			<form action="GestioneOrdineUtenteServlet"  method="post">
				<input type="submit" name="effettua ordine" value="Effettua ordine">
			</form>
			<a href="carrello.jsp">annulla</a>
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
