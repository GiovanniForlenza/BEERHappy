package entity;

public class ProdottoOrdinato {
	String nome;
	String birrificio;
	String descrizione;
	String formato;
	int quantitaSelezionata;
	Double prezzoProdotto;
	int ordineID;
	String pathImage;

	public ProdottoOrdinato(){}

	public ProdottoOrdinato(String nome, String birrificio, String descrizione, String formato, int quantitaSelezionata, Double prezzoProdotto, int ordineID, String pathImage) {
		this.nome = nome;
		this.birrificio = birrificio;
		this.descrizione = descrizione;
		this.formato = formato;
		this.quantitaSelezionata = quantitaSelezionata;
		this.prezzoProdotto = prezzoProdotto;
		this.ordineID = ordineID;
		this.pathImage = pathImage;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBirrificio() {
		return birrificio;
	}

	public void setBirrificio(String birrificio) {
		this.birrificio = birrificio;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public int getQuantitaSelezionata() {
		return quantitaSelezionata;
	}

	public void setQuantitaSelezionata(int quantitaSelezionata) {
		this.quantitaSelezionata = quantitaSelezionata;
	}

	public Double getPrezzoProdotto() {
		return prezzoProdotto;
	}

	public void setPrezzoProdotto(Double prezzoProdotto) {
		this.prezzoProdotto = prezzoProdotto;
	}

	public int getOrdineID() {
		return ordineID;
	}

	public void setOrdineID(int ordineID) {
		this.ordineID = ordineID;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
}
