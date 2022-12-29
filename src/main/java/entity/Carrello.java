package entity;

import java.util.List;

public class Carrello {
    List<Prodotto> prodotti;

    public Carrello(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }


}
