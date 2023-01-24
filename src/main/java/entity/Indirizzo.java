package entity;

public class Indirizzo {
    String citta;
    String via;
    String cap;
    String telefono;
    int id;

    public Indirizzo (){
    }
    public Indirizzo(String citta, String via, String cap, String telefono, int id) {
        this.citta = citta;
        this.via = via;
        this.cap = cap;
        this.telefono = telefono;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
