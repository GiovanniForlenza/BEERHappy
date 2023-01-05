package entity;

public class Prodotto {
    String nome;
    String prezzo;
    String birrificio;
    int quantitaDisp;
    String formato; // 33cl
    String descrizione;

    public Prodotto(){}

    public Prodotto(String nome, String prezzo, String birrificio, int quantitaDisp, String formato, String descrizione) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.birrificio = birrificio;
        this.quantitaDisp = quantitaDisp;
        this.formato = formato;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
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
}
