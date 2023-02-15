package entity;

import java.util.ArrayList;

public class Prodotto{
    String nome;
    float prezzo;
    String birrificio;
    int quantitaDisp;
    String formato; // 33cl
    String descrizione;
    String pathImage;
    int quantita;

    public Prodotto(){}

    public Prodotto(String nome, float prezzo, String birrificio, int quantitaDisp, String formato, String descrizione) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.birrificio = birrificio;
        this.quantitaDisp = quantitaDisp;
        this.formato = formato;
        this.descrizione = descrizione;
        this.quantita = 0;
    }
    public String getPathImage() {
        return pathImage;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getBirrificio() {
        return birrificio;
    }

    public void setBirrificio(String birrificio) {
        this.birrificio = birrificio;
    }

    public int getQuantitaDisp() {
        return quantitaDisp;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setQuantitaDisp(int quantitaDisp) {
        this.quantitaDisp = quantitaDisp;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int aggiornaQuantita(int quantita){
        if(this.quantitaDisp >= quantita)
            quantitaDisp = quantitaDisp - quantita;

        return quantitaDisp;
    }

    public void aggiungiQuantita(int quantita){
        quantitaDisp = quantitaDisp + quantita;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", prezzo='" + prezzo + '\'' +
                ", birrificio='" + birrificio + '\'' +
                ", quantitaDisp=" + quantitaDisp +
                ", formato=" + formato +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prodotto)) return false;
        Prodotto prodotto = (Prodotto) o;
        return nome.equals(prodotto.nome) && prezzo==(prodotto.prezzo) && birrificio.equals(birrificio) && formato.equals(formato) && descrizione.equals(prodotto.descrizione);
    }

    public boolean compareKeys(Prodotto prodotto){
        if(nome.equals(prodotto.nome) && birrificio.equals(prodotto.birrificio))
            return true;
        else return false;
    }

    public static ArrayList<Prodotto> remove(ArrayList<Prodotto> prodotti, Prodotto prodotto){
        for(int i=0; i< prodotti.size(); i++){
            if(prodotti.get(i).compareKeys(prodotto)) {
                prodotti.remove(i);
                return prodotti;
            }
        }
            return prodotti;
    }
}
