package entity;

public class Indirizzo {
    String citta;
    int civico;
    String via;
    String cap;
    String telefono;
    int id;

    public Indirizzo(){
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public Indirizzo(String citta, String via, int civico, String cap, String telefono, int id) {
        this.citta = citta;
        this.via = via;
        this.cap = cap;
        this.civico=civico;
        this.telefono = telefono;

        this.id=id;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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
                "citta='" + citta + '\'' +
                ", via='" + via + '\'' +
                ", cap='" + cap + '\'' +
                ", telefono='" + telefono + '\'' +
                ", id=" + id +
                '}';
    }


}
