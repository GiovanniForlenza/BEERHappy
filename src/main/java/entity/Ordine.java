package entity;

import java.util.ArrayList;

public class Ordine {
	int idOrdine;
	String email;
	String dataOrdine;
	double prezzoOrdine;
	String via;
	int	civico;
	String citta;
	String telefono;
	String stato;
	ArrayList <ProdottoOrdinato> prodotti;

 	public Ordine(){
	}

	public Ordine(int idOrdine, String email, String dataOrdine, double prezzo, String via, int civico, String citta, String telefono) {
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

	public double getPrezzo() {
		return prezzoOrdine;
	}

	public void setPrezzo(double prezzo) {
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

	public ArrayList<ProdottoOrdinato> getProdotti() {
		return prodotti;
	}

	public void setProdotti(ArrayList<ProdottoOrdinato> prodotti) {
		this.prodotti = prodotti;
	}

	public void addProdotto(ProdottoOrdinato prodottoOrdinato){
		 if(this.prodotti == null)
			 this.prodotti = new ArrayList<>();

		 this.prodotti.add(prodottoOrdinato);
	}

}
