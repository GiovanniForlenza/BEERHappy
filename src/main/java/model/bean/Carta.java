package model.bean;
public class Carta {
    String nCata;
    String dataScadenza;
    int cvv; //sono 3 cifre
    String intestatario;
    int cartaID;

    public Carta(){

    }
    public Carta(String nCata, String dataScadenza, int cvv, String intestatario, int cartaID) {
        this.nCata = nCata;
        this.dataScadenza = dataScadenza;
        this.cvv = cvv;
        this.intestatario = intestatario;
        this.cartaID =cartaID;
    }

    public int getId() {
        return cartaID;
    }

    public void setId(int cardID) {
        this.cartaID = cardID;
    }

    public String getnCata() {
        return nCata;
    }

    public void setnCata(String nCata) {
        this.nCata = nCata;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getIntestatario() {
        return intestatario;
    }

    public void setIntestatario(String intestatario) {
        this.intestatario = intestatario;
    }


    @Override
    public String toString() {
        return "Carta{" +
                "nCata='" + nCata + '\'' +
                ", dataScadenza='" + dataScadenza + '\'' +
                ", cvv=" + cvv +
                ", intestatario='" + intestatario + '\'' +
                ", cartaID=" + cartaID +
                '}';
    }
}
