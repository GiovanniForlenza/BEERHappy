package entity;

public class Indirizzo {
    String stato;
    String citta;
    String via;
    String cap;
    String telefono;

    public Indirizzo(String stato, String citta, String via, String cap, String telefono) {
        this.stato = stato;
        this.citta = citta;
        this.via = via;
        this.cap = cap;
        this.telefono = telefono;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "stato='" + stato + '\'' +
                ", citta='" + citta + '\'' +
                ", via='" + via + '\'' +
                ", cap='" + cap + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
