package model.bean;

import java.util.ArrayList;

public class Carrello {
    ArrayList<Prodotto> prodotti;
    int quantita;
    public Carrello(){
    }
    public Carrello(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public void removeProdotti(Prodotto prodotto){
        for(int i = 0; i < this.prodotti.size(); i++){
            if(prodotti.get(i).equals(prodotto)){
                this.prodotti.remove(i);
            }
        }
    }
    public void clearProdotti(){
        this.prodotti.clear();
    }
    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void addProdotti(Prodotto prodotto){
        if(this.prodotti == null)
            prodotti = new ArrayList<Prodotto>();

        this.prodotti.add(prodotto);
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
