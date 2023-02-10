package entity;

import java.util.ArrayList;

public class Ordine {
	int idOrdine;
	String email;
	String dataOrdine;
	float prezzoOrdine;
	String via;
	int	civico;
	String citta;
	String telefono;
	String stato;
	ArrayList <Prodotto> prodotti;

 	public Ordine(){
	}

	public Ordine(int idOrdine, String email, String dataOrdine, float prezzo, String via, int civico, String citta, String telefono) {
		this.idOrdine = idOrdine;
		this.email = email;
		this.dataOrdine = dataOrdine;
		this.prezzoOrdine = prezzo;
		this.via = via;
		this.civico = civico;
		this.citta = citta;
		this.telefono = telefono;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public float getPrezzo() {
		return prezzoOrdine;
	}

	public void setPrezzo(float prezzo) {
		this.prezzoOrdine = prezzo;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getCivico() {
		return civico;
	}

	public void setCivico(int civico) {
		this.civico = civico;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public ArrayList<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(ArrayList<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public void addProdotto(Prodotto prodottoOrdinato){
		 if(this.prodotti == null)
			 this.prodotti = new ArrayList<>();

		 this.prodotti.add(prodottoOrdinato);
	}

}
