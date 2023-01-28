<%@ page import="entity.Utente" %>
<%@ page import="entity.Carrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Prodotto" %>
<%@ page import="entity.Carta" %><%--
  Created by IntelliJ IDEA.
  User: jhon
  Date: 28/01/23
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

	Boolean flag = (Boolean) session.getAttribute("accesso");
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
		ArrayList<Prodotto> prodotti = carrello.getProdotti();
		if(prodotti.size()>0){
	%>
			<h2>DATI Utente</h2>
			<h3>nome: <%=utente.getNome()%></h3>
			<h3>cognome: <%=utente.getCognome()%></h3>
			<h3>e-mail: <%=utente.getEmail()%></h3>

			<button id="buttonDialog">seleziona carta</button>
			<button>aggiungi carta</button> <br>
			<!--TODO creare un blocco carta-->
<!--
			<script>
				const
					dialog  = document.getElementById('dialog'),
					close   = document.getElementById('close'),
					button  = document.getElementById('buttonDialog');

				button.addEventListener('click', event => {
					dialog.showModal()
				})

				close.addEventListener('click', event => {
					dialog.close()
				})

				let observer = new MutationObserver(function()  {
					if (event[0].attributeName == 'open') console.log('updated')
				});

				observer.observe(dialog, { attributes: true });


			</script>
-->

			<button>seleziona indirizzo</button>
			<button>aggiungi indirizzo</button> <br>
			<!--TODO creare un blocco indirizzo-->

			<h2>Prodotti</h2>
	<%
			double tot = 0;
			for(int i = 0; i < prodotti.size(); i++)
			{
				tot = tot + (Double.parseDouble(prodotti.get(i).getPrezzo()) * prodotti.get(i).getQuantita());
	%>
				<p><%=prodotti.get(i).getNome()%></p>
				<p><%=prodotti.get(i).getQuantita()%></p>
				<p><%=prodotti.get(i).getPrezzo()%></p>
	<%
			}
	%>
			<h3>totale ordine <%=tot%>€</h3>

			<a>effettua ordine</a> <br>
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
